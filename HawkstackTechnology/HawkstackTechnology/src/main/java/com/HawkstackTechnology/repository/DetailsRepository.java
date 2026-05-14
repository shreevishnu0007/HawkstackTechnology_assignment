package com.HawkstackTechnology.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.HawkstackTechnology.entity.Details;

public interface DetailsRepository extends JpaRepository<Details, Long> {
}
