package com.stickerprinting.business.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCompanyEmail(String companyEmail);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByCompanyEmailOrPhoneNumber(
            String companyEmail,
            String phoneNumber
    );

    boolean existsByCompanyEmail(String companyEmail);

    boolean existsByPhoneNumber(String phoneNumber);
}