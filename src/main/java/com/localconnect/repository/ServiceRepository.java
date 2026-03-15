package com.localconnect.repository;

import com.localconnect.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByCity(String city);
    List<Service> findByCityAndCategoryId(String city, Integer categoryId);
    List<Service> findByProviderId(Integer providerId);
}