package com.mustache.springbootmustache1111.repository;

import com.mustache.springbootmustache1111.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessType);
    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressLike(List<String> businessType, String nameAddress);

    List<Hospital> findByRoadNameAddressContaining(String keyword); // 포함
    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작
    List<Hospital> findByHospitalNameEndsWith(String keyword); // 끝남

    List<Hospital> findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(int var1, int var2);
    List<Hospital> findByPatientRoomCountBetween(int var1, int var2);

    List<Hospital> findAllByRoadNameAddressContaining(String keyword);

    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(int a, int b);

    Page<Hospital> findByRoadNameAddressContaining(String keyword, Pageable pageable);
}
