package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.BranchAddress;
import io.reflectoring.rentAcar.domain.request.BranchAddressRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchAddressResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.BranchAddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchAddressService {
    @Autowired
    private BranchAddressRepository branchAddressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BranchAddressResponseDto> getAllBranchAddresses() {
        List<BranchAddress> branchAddresses = branchAddressRepository.findAll();
        return branchAddresses.stream()
                .map(address -> modelMapper.map(address, BranchAddressResponseDto.class))
                .collect(Collectors.toList());
    }

    public BranchAddressResponseDto getBranchAddressById(UUID id) {
        BranchAddress branchAddress = branchAddressRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Branch address not found"));
        return modelMapper.map(branchAddress, BranchAddressResponseDto.class);
    }

    public BranchAddressResponseDto saveBranchAddress(BranchAddressRequestDto branchAddressRequestDto) {
        BranchAddress branchAddress = modelMapper.map(branchAddressRequestDto, BranchAddress.class);
        branchAddress = branchAddressRepository.save(branchAddress);
        return modelMapper.map(branchAddress, BranchAddressResponseDto.class);
    }

    public BranchAddressResponseDto updateBranchAddress(UUID id, BranchAddressRequestDto branchAddressRequestDto) {
        BranchAddress existingBranchAddress = branchAddressRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Branch address not found"));
        modelMapper.map(branchAddressRequestDto, existingBranchAddress);
        BranchAddress updatedBranchAddress = branchAddressRepository.save(existingBranchAddress);
        return modelMapper.map(updatedBranchAddress, BranchAddressResponseDto.class);
    }

    public void deleteBranchAddress(UUID id) {
        BranchAddress branchAddress = branchAddressRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Branch address not found"));
        branchAddressRepository.delete(branchAddress);
    }
}