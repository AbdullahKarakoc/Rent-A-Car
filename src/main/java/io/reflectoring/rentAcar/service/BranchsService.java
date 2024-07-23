package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.BranchAddress;
import io.reflectoring.rentAcar.domain.model.Branchs;
import io.reflectoring.rentAcar.domain.request.BranchsRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.BranchAddressRepository;
import io.reflectoring.rentAcar.repository.BranchsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BranchsService {

    private final BranchsRepository branchRepository;
    private final ModelMapper modelMapper;

    public List<BranchsResponseDto> getAllBranches() {
        List<Branchs> branches = branchRepository.findAll();
        return branches.stream()
                .map(branch -> modelMapper.map(branch, BranchsResponseDto.class))
                .collect(Collectors.toList());
    }

    public BranchsResponseDto getBranchById(UUID id) {
        Branchs branch = findById(id);
        return modelMapper.map(branch, BranchsResponseDto.class);
    }

    public BranchsResponseDto saveBranch(BranchsRequestDto branchRequestDto) {
        Branchs branch = modelMapper.map(branchRequestDto, Branchs.class);
        branch.setAddress(modelMapper.map(branchRequestDto.getBranchAddress(), BranchAddress.class));
        Branchs savedBranch = save(branch);
        return modelMapper.map(savedBranch, BranchsResponseDto.class);
    }

    public BranchsResponseDto updateBranch(UUID id, BranchsRequestDto branchRequestDto) {
        Branchs existingBranch = findById(id);

        modelMapper.map(branchRequestDto, existingBranch);
        existingBranch.setAddress(modelMapper.map(branchRequestDto.getBranchAddress(), BranchAddress.class));

        Branchs updatedBranch = save(existingBranch);
        return modelMapper.map(updatedBranch, BranchsResponseDto.class);
    }

    public void deleteBranch(UUID id) {
        Branchs branch = findById(id);
        branch.setDeleted(true);
        save(branch);
    }

    public Branchs save(Branchs branch) {
        return branchRepository.saveAndFlush(branch);
    }

    public Branchs findById(UUID id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Branch not found"));
    }
}


