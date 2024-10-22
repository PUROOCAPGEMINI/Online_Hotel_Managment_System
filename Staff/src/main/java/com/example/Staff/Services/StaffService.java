package com.example.Staff.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Staff.Exception.StaffNotFoundException;
import com.example.Staff.Model.Staff;
import com.example.Staff.Repository.StaffRepository;


@Service
public class StaffService {

	 @Autowired
	    private StaffRepository staffRepository;

	    public List<Staff> getAllStaff() {
	        return staffRepository.findAll();
	    }

	    public Staff addStaff(Staff staff) {
	        return staffRepository.save(staff);
	    }

	    public Staff updateStaff(String id, Staff staffDetails) {
	        Staff staff = staffRepository.findById(id).orElseThrow(() -> new RuntimeException("Staff not found"));
	        staff.setEmpName(staffDetails.getEmpName());
	        staff.setOccupation(staffDetails.getOccupation());
	        return staffRepository.save(staff);
	    }

	    public void deleteStaff(String id) {
	       
	    	 if (!staffRepository.existsById(id)) {
	             throw new StaffNotFoundException(id);
	         }
	         staffRepository.deleteById(id);
	     
	    }
}