package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.BranchAddress;
import io.reflectoring.rentAcar.domain.model.Branchs;
import io.reflectoring.rentAcar.domain.request.BranchsRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchsResponseDto;
import io.reflectoring.rentAcar.repository.BranchAddressRepository;
import io.reflectoring.rentAcar.repository.BranchsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchsService {
    @Autowired
    private BranchsRepository branchRepository;

    @Autowired
    private BranchAddressRepository branchAddressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BranchsResponseDto> getAllBranches() {
        List<Branchs> branches = branchRepository.findAll();
        return branches.stream()
                .map(branch -> modelMapper.map(branch, BranchsResponseDto.class))
                .collect(Collectors.toList());
    }

    public BranchsResponseDto getBranchById(UUID id) {
        Branchs branch = branchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Branch not found"));
        return modelMapper.map(branch, BranchsResponseDto.class);
    }

    public BranchsResponseDto saveBranch(BranchsRequestDto branchRequestDto) {
        BranchAddress branchAddress = branchAddressRepository.findById(branchRequestDto.getBranchAddressUUID())
                .orElseThrow(() -> new DataNotFoundException("Branch address not found"));

        Branchs branch = modelMapper.map(branchRequestDto, Branchs.class);
        branch.setBranchAddress(branchAddress);
        branch = branchRepository.save(branch);
        return modelMapper.map(branch, BranchsResponseDto.class);
    }

    public BranchsResponseDto updateBranch(UUID id, BranchsRequestDto branchRequestDto) {
        Branchs existingBranch = branchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Branch not found"));

        BranchAddress branchAddress = branchAddressRepository.findById(branchRequestDto.getBranchAddressUUID())
                .orElseThrow(() -> new DataNotFoundException("Branch address not found"));

        modelMapper.map(branchRequestDto, existingBranch);
        existingBranch.setBranchAddress(branchAddress);
        Branchs updatedBranch = branchRepository.save(existingBranch);
        return modelMapper.map(updatedBranch, BranchsResponseDto.class);
    }

    public void deleteBranch(UUID id) {
        Branchs branch = branchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Branch not found"));
        branchRepository.delete(branch);
    }
}
