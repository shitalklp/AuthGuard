//package com.example.authguard.repository;
//
//import com.example.authguard.entity.OtpVerification;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface OtpRepository extends JpaRepository<OtpVerification, Long> {
//
//    Optional<OtpVerification> findByEmail(String email);
//
//    void deleteByEmail(String email);
//}

package com.example.authguard.repository;

import com.example.authguard.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM OtpVerification o WHERE o.email = :email")
    void deleteByEmail(@Param("email") String email);
}