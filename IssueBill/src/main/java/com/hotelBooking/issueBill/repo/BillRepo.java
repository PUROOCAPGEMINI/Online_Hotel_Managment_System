package com.hotelBooking.issueBill.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBooking.issueBill.models.IssueBill;

@Repository
public interface BillRepo extends JpaRepository<IssueBill, String>{

}
