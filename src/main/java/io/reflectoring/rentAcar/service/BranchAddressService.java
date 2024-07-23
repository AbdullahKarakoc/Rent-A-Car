package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.BranchAddress;
import io.reflectoring.rentAcar.domain.request.BranchAddressRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchAddressResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.BranchAddressRepository;
import io.reflectoring.rentAcar.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchAddressService {

    private final BranchAddressRepository branchAddressRepository;
    private final ModelMapper modelMapper;

    public List<BranchAddressResponseDto> getAllBranchAddresses() {
        List<BranchAddress> branchAddresses = branchAddressRepository.findAll();
        return branchAddresses.stream()
                .map(address -> modelMapper.map(address, BranchAddressResponseDto.class))
                .collect(Collectors.toList());
    }

    public BranchAddressResponseDto getBranchAddressById(UUID id) {
        BranchAddress branchAddress = findById(id);
        return modelMapper.map(branchAddress, BranchAddressResponseDto.class);
    }

    public BranchAddressResponseDto saveBranchAddress(BranchAddressRequestDto branchAddressRequestDto) {
        BranchAddress branchAddress = modelMapper.map(branchAddressRequestDto, BranchAddress.class);
        BranchAddress savedBranchAddress = save(branchAddress);
        return modelMapper.map(savedBranchAddress, BranchAddressResponseDto.class);
    }

    public BranchAddressResponseDto updateBranchAddress(UUID id, BranchAddressRequestDto branchAddressRequestDto) {
        BranchAddress existingBranchAddress = findById(id);
        modelMapper.map(branchAddressRequestDto, existingBranchAddress);
        BranchAddress updatedBranchAddress = save(existingBranchAddress);
        return modelMapper.map(updatedBranchAddress, BranchAddressResponseDto.class);
    }

    public void deleteBranchAddress(UUID id) {
        BranchAddress branchAddress = findById(id);
        branchAddressRepository.delete(branchAddress);
    }

    public BranchAddress save(BranchAddress branchAddress) {
        return branchAddressRepository.saveAndFlush(branchAddress);
    }

    public BranchAddress findById(UUID id) {
        return branchAddressRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.BRANCH_ADDRESS_NOT_FOUND.getValue()));
    }
}