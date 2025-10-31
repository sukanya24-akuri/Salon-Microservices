package com.spring.repository;

import com.spring.entity.ServiceOffers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ServiceOffersRepository extends JpaRepository<ServiceOffers,Long>
{
    Set<ServiceOffers> findBySalonId(Long id);
}
