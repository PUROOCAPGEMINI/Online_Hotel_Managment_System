package com.example.Staff.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Staff.Model.Staff;
import com.example.Staff.Services.StaffService;

import jakarta.validation.Valid;


@RestController
@Validated
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/allstaff")
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @PostMapping("/addstaff")
    public Staff addStaff(@Valid @RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

    @PutMapping("/update/{id}")
    public Staff updateStaff(@PathVariable String id, @Valid @RequestBody Staff staffDetails) {
        return staffService.updateStaff(id, staffDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}