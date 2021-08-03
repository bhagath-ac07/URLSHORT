package com.url.shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Date;

public interface URLRepository extends JpaRepository<Url, String> {
    @Modifying
    public void deleteByCreatedBefore(Date expiryDate);
    public Url findFirstByOrderByCreatedDesc();
}