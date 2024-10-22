package com.example.Staff.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Staff.Model.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {

}
