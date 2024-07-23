package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Branchs;
import io.reflectoring.rentAcar.domain.model.Staffs;
import io.reflectoring.rentAcar.domain.request.StaffsRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchsResponseDto;
import io.reflectoring.rentAcar.domain.response.StaffsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.BranchsRepository;
import io.reflectoring.rentAcar.repository.StaffRepository;
import io.reflectoring.rentAcar.util.ErrorMessages;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final BranchsService branchService;
    private final ModelMapper modelMapper;

    public List<StaffsResponseDto> getAllStaffs() {
        List<Staffs> staffs = staffRepository.findAll();
        return staffs.stream()
                .map(staff -> modelMapper.map(staff, StaffsResponseDto.class))
                .collect(Collectors.toList());
    }

    public StaffsResponseDto getStaffById(UUID id) {
        Staffs staff = findById(id);
        return modelMapper.map(staff, StaffsResponseDto.class);
    }

    public StaffsResponseDto saveStaff(StaffsRequestDto staffRequestDto) {
        Branchs branch = branchService.findById(staffRequestDto.getBranchUUID());

        Staffs staff = modelMapper.map(staffRequestDto, Staffs.class);
        staff.setBranch(branch); // Set the branch as Branchs model

        Staffs savedStaff = save(staff);
        return modelMapper.map(savedStaff, StaffsResponseDto.class);
    }

    public StaffsResponseDto updateStaff(UUID id, StaffsRequestDto staffRequestDto) {
        Staffs existingStaff = findById(id);

        Branchs branch = branchService.findById(staffRequestDto.getBranchUUID());

        modelMapper.map(staffRequestDto, existingStaff);
        existingStaff.setBranch(branch); // Set the branch as Branchs model

        Staffs updatedStaff = save(existingStaff);
        return modelMapper.map(updatedStaff, StaffsResponseDto.class);
    }

    public void deleteStaff(UUID id) {
        Staffs staff = findById(id);
        staffRepository.delete(staff);
    }

    public Staffs save(Staffs staff) {
        return staffRepository.saveAndFlush(staff);
    }

    public Staffs findById(UUID id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Staff not found"));
    }
}


