package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.BranchAddress;
import io.reflectoring.rentAcar.domain.model.Branchs;
import io.reflectoring.rentAcar.domain.request.BranchAddressRequestDto;
import io.reflectoring.rentAcar.domain.request.BranchsRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchAddressResponseDto;
import io.reflectoring.rentAcar.domain.response.BranchsResponseDto;
import io.reflectoring.rentAcar.enums.CountryType;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.BranchsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BranchsServiceTest {

    @Mock
    private BranchsRepository branchRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BranchsService branchsService;

    private Branchs branch;
    private BranchsRequestDto branchRequestDto;
    private BranchsResponseDto branchResponseDto;
    private BranchAddressRequestDto branchAddressRequestDto;

    @BeforeEach
    void setUp() {
        // UUID ve BranchName ayarla
        branch = new Branchs();
        branch.setBranchUUID(UUID.randomUUID());
        branch.setBranchName("Test Branch");

        // BranchAddress nesnesini oluştur ve gerekli alanları ayarla
        BranchAddress branchAddress = new BranchAddress();
        branchAddress.setAddressUUID(UUID.randomUUID()); // Oluşturulan bir UUID
        branchAddress.setStreet("Test Street");
        branchAddress.setCity("Test City");
        branchAddress.setCountry(CountryType.TURKEY); // Veya uygun bir değer seçin
        branchAddress.setZipCode("12345");
        branchAddress.setDeleted(false); // Varsayılan değer

        branch.setAddress(branchAddress);

        // BranchAddressRequestDto oluştur ve gerekli alanları ayarla
        branchAddressRequestDto = new BranchAddressRequestDto();
        branchAddressRequestDto.setStreet("Test Street");
        branchAddressRequestDto.setCity("Test City");
        branchAddressRequestDto.setCountry(CountryType.TURKEY); // Veya uygun bir değer seçin
        branchAddressRequestDto.setZipCode("12345");

        // BranchsRequestDto oluştur ve gerekli alanları ayarla
        branchRequestDto = new BranchsRequestDto();
        branchRequestDto.setBranchName("Test Branch");
        branchRequestDto.setBranchAddress(branchAddressRequestDto);

        // BranchsResponseDto oluştur ve gerekli alanları ayarla
        branchResponseDto = new BranchsResponseDto();
        branchResponseDto.setBranchUUID(branch.getBranchUUID());
        branchResponseDto.setBranchName("Test Branch");
        // branchResponseDto için diğer gerekli alanları da ayarlayın
    }

    @Test
    void getAllBranches() {
        List<Branchs> branches = new ArrayList<>();
        branches.add(branch);
        when(branchRepository.findAll()).thenReturn(branches);
        when(modelMapper.map(branch, BranchsResponseDto.class)).thenReturn(branchResponseDto);

        List<BranchsResponseDto> result = branchsService.getAllBranches();
        assertEquals(1, result.size());
        assertEquals(branchResponseDto, result.get(0));
    }

    @Test
    void getBranchById() {
        when(branchRepository.findById(branch.getBranchUUID())).thenReturn(Optional.of(branch));
        when(modelMapper.map(branch, BranchsResponseDto.class)).thenReturn(branchResponseDto);

        BranchsResponseDto result = branchsService.getBranchById(branch.getBranchUUID());
        assertEquals(branchResponseDto, result);
    }

    @Test
    void getBranchById_notFound() {
        when(branchRepository.findById(branch.getBranchUUID())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> branchsService.getBranchById(branch.getBranchUUID()));
    }

    @Test
    void saveBranch() {
        when(modelMapper.map(branchRequestDto, Branchs.class)).thenReturn(branch);
        when(modelMapper.map(branchRequestDto.getBranchAddress(), BranchAddress.class)).thenReturn(branch.getAddress());
        when(branchRepository.saveAndFlush(branch)).thenReturn(branch);
        when(modelMapper.map(branch, BranchsResponseDto.class)).thenReturn(branchResponseDto);

        BranchsResponseDto result = branchsService.saveBranch(branchRequestDto);
        assertEquals(branchResponseDto, result);
    }

//    @Test
//    void updateBranch() {
//        UUID branchId = UUID.randomUUID();
//
//
//        // Test için uygun bir branch nesnesi oluştur
//        Branchs existingBranch = new Branchs();
//        existingBranch.setBranchUUID(branchId);
//        existingBranch.setBranchName("Test Branch");
//
//        BranchAddress branchAddress = new BranchAddress();
//        branchAddress.setAddressUUID(UUID.randomUUID());
//        branchAddress.setStreet("Test Street");
//        branchAddress.setCity("Test City");
//        branchAddress.setCountry(CountryType.TURKEY);
//        branchAddress.setZipCode("12345");
//
//        existingBranch.setAddress(branchAddress);
//
//        // Beklenen BranchsResponseDto nesnesini oluştur
//        BranchAddressResponseDto addressResponseDto = new BranchAddressResponseDto();
//        addressResponseDto.setAddressUUID(branchAddress.getAddressUUID());
//        addressResponseDto.setStreet(branchAddress.getStreet());
//        addressResponseDto.setCity(branchAddress.getCity());
//        addressResponseDto.setCountry(branchAddress.getCountry().toString());
//        addressResponseDto.setZipCode(branchAddress.getZipCode());
//
//        BranchsResponseDto expectedResponseDto = new BranchsResponseDto();
//        expectedResponseDto.setBranchUUID(branchId);
//        expectedResponseDto.setBranchName("Test Branch");
//        expectedResponseDto.setAddress(addressResponseDto);
//
//        // Stubbing işlemleri
//        when(branchRepository.findById(branchId)).thenReturn(Optional.of(existingBranch));
//        when(modelMapper.map(branchRequestDto, Branchs.class)).thenReturn(existingBranch);
//        when(modelMapper.map(branchRequestDto.getBranchAddress(), BranchAddress.class)).thenReturn(branchAddress);
//        when(branchRepository.save(existingBranch)).thenReturn(existingBranch);
//        when(modelMapper.map(existingBranch, BranchsResponseDto.class)).thenReturn(expectedResponseDto);
//
//        // Metodu çağır ve sonucu doğrula
//        BranchsResponseDto result = branchsService.updateBranch(branchId, branchRequestDto);
//        assertEquals(expectedResponseDto, result);
//
//        // Doğru argümanlarla metotların çağrıldığını doğrula
//        verify(branchRepository).findById(branchId);
//        verify(modelMapper).map(branchRequestDto, Branchs.class);
//        verify(modelMapper).map(branchRequestDto.getBranchAddress(), BranchAddress.class);
//        verify(branchRepository).save(existingBranch);
//        verify(modelMapper).map(existingBranch, BranchsResponseDto.class);
//    }




    @Test
    void updateBranch_notFound() {
        when(branchRepository.findById(branch.getBranchUUID())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> branchsService.updateBranch(branch.getBranchUUID(), branchRequestDto));
    }

    @Test
    void deleteBranch() {
        when(branchRepository.findById(branch.getBranchUUID())).thenReturn(Optional.of(branch));

        branchsService.deleteBranch(branch.getBranchUUID());

        assertTrue(branch.isDeleted());
        verify(branchRepository).saveAndFlush(branch);
    }

    @Test
    void deleteBranch_notFound() {
        when(branchRepository.findById(branch.getBranchUUID())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> branchsService.deleteBranch(branch.getBranchUUID()));
    }
}