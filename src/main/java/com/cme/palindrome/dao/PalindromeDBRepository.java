package com.cme.palindrome.dao;

import com.cme.palindrome.common.PalindromeObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromeDBRepository extends JpaRepository<PalindromeObject, String> {

}
