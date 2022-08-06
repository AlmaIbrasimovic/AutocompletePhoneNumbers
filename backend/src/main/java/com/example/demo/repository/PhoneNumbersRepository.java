package com.example.demo.repository;

import com.example.demo.model.PhoneNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumbersRepository extends JpaRepository<PhoneNumbers, Long> {

    @Query(value = "SELECT * FROM public.phone_numbers a WHERE (REPLACE(REPLACE(a.phone_number,'-',''), ' ', '') LIKE :phoneNumber%) " +
                   "ORDER BY LENGTH(a.phone_number) ASC " +
                   "LIMIT 10 ", nativeQuery = true)
    List<PhoneNumbers> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
