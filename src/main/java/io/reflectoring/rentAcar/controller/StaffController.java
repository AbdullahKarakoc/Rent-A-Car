package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.StaffsRequestDto;
import io.reflectoring.rentAcar.domain.response.StaffsResponseDto;
import io.reflectoring.rentAcar.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping
    public List<StaffsResponseDto> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @GetMapping("/{id}")
    public StaffsResponseDto getStaffById(@PathVariable UUID id) {
        return staffService.getStaffById(id);
    }

    @PostMapping
    public StaffsResponseDto saveStaff(@RequestBody StaffsRequestDto staffRequestDto) {
        return staffService.saveStaff(staffRequestDto);
    }

    @PutMapping("/{id}")
    public StaffsResponseDto updateStaff(@PathVariable UUID id, @RequestBody StaffsRequestDto staffRequestDto) {
        return staffService.updateStaff(id, staffRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable UUID id) {
        staffService.deleteStaff(id);
    }
}
