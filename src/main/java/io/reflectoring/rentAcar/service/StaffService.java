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
@Data
public class StaffService {

    @Autowired
    private final StaffRepository staffRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final BranchsRepository branchRepository;

    public StaffsResponseDto saveStaff(StaffsRequestDto staffRequestDto) {
        Branchs branch = branchRepository.findById(staffRequestDto.getBranchUUID())
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.BRANCH_NOT_FOUND.getValue()));

        Staffs staff = modelMapper.map(staffRequestDto, Staffs.class);
        staff.setBranch(branch);

        Staffs savedStaff = staffRepository.save(staff);
        return modelMapper.map(savedStaff, StaffsResponseDto.class);
    }

    public List<StaffsResponseDto> getAllStaffs() {
        List<Staffs> staffs = staffRepository.findAll();
        return staffs.stream()
                .map(staff -> modelMapper.map(staff, StaffsResponseDto.class))
                .collect(Collectors.toList());
    }

    public StaffsResponseDto getStaffById(UUID id) {
        Staffs staff = staffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.STAFF_NOT_FOUND.getValue()));
        return modelMapper.map(staff, StaffsResponseDto.class);
    }

    public StaffsResponseDto updateStaff(UUID id, StaffsRequestDto staffRequestDto) {
        Staffs existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.STAFF_NOT_FOUND.getValue()));

        Branchs branch = branchRepository.findById(staffRequestDto.getBranchUUID())
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.BRANCH_NOT_FOUND.getValue()));

        modelMapper.map(staffRequestDto, existingStaff);
        existingStaff.setBranch(branch);

        Staffs updatedStaff = staffRepository.save(existingStaff);
        return modelMapper.map(updatedStaff, StaffsResponseDto.class);
    }

    public void deleteStaff(UUID id) {
        Staffs staff = staffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.STAFF_NOT_FOUND.getValue()));
        staffRepository.delete(staff);
    }
}
