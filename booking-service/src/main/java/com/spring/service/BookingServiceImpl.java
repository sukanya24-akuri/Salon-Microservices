package com.spring.service;

import com.spring.domain.BookingStatus;
import com.spring.dto.BookingRequest;
import com.spring.dto.SalonDTO;
import com.spring.dto.ServiceDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Booking;
import com.spring.entity.SalonReport;
import com.spring.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;

    @Override
    public Booking createBooking(SalonDTO salonDTO, UserDTO userDTO, Set<ServiceDTO> serviceDTO, BookingRequest booking) throws Exception {
        int totalDuration = serviceDTO.stream().mapToInt(ServiceDTO::getDuration).sum();
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);


        Double totalPrice = serviceDTO.stream().mapToDouble(ServiceDTO::getPrice).sum();

        Set<Long> listIds = serviceDTO.stream().map(ServiceDTO::getId).collect(Collectors.toSet());

       isSlotTimeAvailable(bookingStartTime, bookingEndTime, salonDTO);

        Booking createBooking = new Booking();
        createBooking.setCustomerId(userDTO.getUserId());
        createBooking.setSalonId(salonDTO.getId());
        createBooking.setBookingStatus(BookingStatus.PENDING);
       createBooking.setServiceIds(listIds);
        createBooking.setStartTime(bookingStartTime);
        createBooking.setEndTime(bookingEndTime);

        createBooking.setTotalPrices(totalPrice);

        return repository.save(createBooking);
    }

    public Boolean isSlotTimeAvailable(LocalDateTime bookingStartTime, LocalDateTime bookingEndTime, SalonDTO salonDTO) throws Exception {
        LocalDateTime salonOpenTime = salonDTO.getOpenAt().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonEndTime = salonDTO.getCloseAt().atDate(bookingEndTime.toLocalDate());
        List<Booking> existingBookings = getBookingsBySalonId(salonDTO.getId());
        if (bookingStartTime.equals(salonEndTime) && bookingStartTime.isBefore(salonOpenTime)) {
            throw new Exception("slot your service within salon's timings");
        }
        if (bookingStartTime.equals(salonEndTime)) {
            throw new Exception("salon is closed..book your slot tomorrow");
        }
        for (Booking existingBooking : existingBookings) {
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();
            if (bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("slot is not available..please choose different time");
            }
            if (bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTime.isEqual(existingBookingEndTime)) {
                throw new Exception("slot is not available..please choose different time");
            }
        }
        return true;

    }

    @Override
    public List<Booking> getBookingsByCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public List<Booking> getBookingsBySalonId(Long id) {
        return repository.findBySalonId(id);
    }

    @Override
    public Booking getBookingById(Long id) {
        Booking bookingId = repository.findById(id).orElse(null);
        return bookingId;
    }

    @Override
    public Booking updateBooking(Long id, BookingStatus status) {
        Booking opt = repository.findById(id).orElseThrow(() -> new RuntimeException("Booking id not found for updation"));
        opt.setBookingStatus(status);
        return repository.save(opt);
    }

    @Override
    public List<Booking> getBookingsByDate(Long id, LocalDate date) {
        List<Booking> allBookings = getBookingsBySalonId(id);
        if (date == null) {
            return allBookings;
        }
        return allBookings.stream().filter(booking -> todayBookings(booking.getStartTime(), date) ||
                        todayBookings(booking.getEndTime(), date))
                .collect(Collectors.toList());
    }

    public boolean todayBookings(LocalDateTime time, LocalDate date) {
        return time.toLocalDate().equals(date);
    }

    @Override
    public SalonReport getReportsBySalonId(Long id) {
        List<Booking> booking = repository.findBySalonId(id);
        Double totalEarning = booking.stream()
                        .filter(confirm-> BookingStatus.CONFIRMED.equals(confirm.getBookingStatus()))
                .mapToDouble(Booking::getTotalPrices).sum();

        Integer totalBookings = booking.size();

        List<Booking> cancelledBookings = booking.stream().filter(cancel -> cancel.getBookingStatus().equals(BookingStatus.CANCELLED))
                .toList();

        Double totalRefund = cancelledBookings.stream().mapToDouble(Booking::getTotalPrices).sum();
        SalonReport report = new SalonReport();
        report.setSalonId(id);
        report.setTotalBookings(totalBookings);
        report.setCancelledBookings(cancelledBookings.size());
        report.setTotalEarning(totalEarning);
        report.setTotalRefund(totalRefund);
        return report;
    }
}
