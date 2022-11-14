package com.mustache.springbootmustache1111.repository;
import com.mustache.springbootmustache1111.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
