package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Staffs;
import io.reflectoring.rentAcar.domain.request.StaffsRequestDto;
import io.reflectoring.rentAcar.domain.response.StaffsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.StaffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StaffsResponseDto> getAllStaffs() {
        List<Staffs> staffs = staffRepository.findAll();
        return staffs.stream()
                .map(staff -> modelMapper.map(staff, StaffsResponseDto.class))
                .collect(Collectors.toList());
    }

    public StaffsResponseDto getStaffById(UUID id) {
        Staffs staff = staffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Staff not found"));
        return modelMapper.map(staff, StaffsResponseDto.class);
    }

    public StaffsResponseDto saveStaff(StaffsRequestDto staffRequestDto) {
        Staffs staff = modelMapper.map(staffRequestDto, Staffs.class);
        staff = staffRepository.save(staff);
        return modelMapper.map(staff, StaffsResponseDto.class);
    }

    public StaffsResponseDto updateStaff(UUID id, StaffsRequestDto staffRequestDto) {
        Staffs existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Staff not found"));
        modelMapper.map(staffRequestDto, existingStaff);
        Staffs updatedStaff = staffRepository.save(existingStaff);
        return modelMapper.map(updatedStaff, StaffsResponseDto.class);
    }

    public void deleteStaff(UUID id) {
        Staffs staff = staffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Staff not found"));
        staffRepository.delete(staff);
    }
}