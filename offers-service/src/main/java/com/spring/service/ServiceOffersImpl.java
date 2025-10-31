package com.spring.service;

import com.spring.dto.CategoryDTO;
import com.spring.dto.SalonDTO;
import com.spring.dto.ServiceDTO;
import com.spring.repository.ServiceOffersRepository;
import com.spring.entity.ServiceOffers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ServiceOffersImpl implements IServiceOffers
{
private final ServiceOffersRepository repository;
    @Override
    public ServiceOffers createService(SalonDTO salonDTO, CategoryDTO categoryDTO, ServiceDTO serviceDTO)
    {
        ServiceOffers service=new ServiceOffers();
        service.setId(serviceDTO.getId());;
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setImage(serviceDTO.getImage());
        service.setCategoryId(categoryDTO.getId());
        service.setSalonId(salonDTO.getId());
        service.setDuration(serviceDTO.getDuration());
        return repository.save(service);
    }

    @Override
    public ServiceOffers updateService(Long id, ServiceOffers serviceOffers)
    {
        ServiceOffers offers=repository.findById(id).orElse(null);
        offers.setName(serviceOffers.getName());
        offers.setDescription(serviceOffers.getDescription());
        offers.setPrice(serviceOffers.getPrice());
        offers.setImage(serviceOffers.getImage());
        offers.setDuration(serviceOffers.getDuration());
        return repository.save(offers);
    }

    @Override
    public ServiceOffers getServiceById(Long id)
    {
        return  repository.findById(id).orElseThrow();
    }

    @Override
    public Set<ServiceOffers> getAllServicesBySalonId(Long id)
    {
        Set<ServiceOffers> set=repository.findBySalonId(id);
        return set;
    }


    @Override
    public void deleteServiceById(Long id,Long salonDTO)
    {
       ServiceOffers offer=getServiceById(id);
       if(offer.getSalonId().equals(salonDTO))
       {
           repository.deleteById(id);
       }
       else {
           throw new RuntimeException("service offer if is not found for deletion");
       }
    }
}
