package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.BranchsRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchsResponseDto;
import io.reflectoring.rentAcar.service.BranchsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/branches")
public class BranchsController {
    @Autowired
    private BranchsService branchService;

    @GetMapping
    public List<BranchsResponseDto> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public BranchsResponseDto getBranchById(@PathVariable UUID id) {
        return branchService.getBranchById(id);
    }

    @PostMapping
    public BranchsResponseDto saveBranch(@RequestBody BranchsRequestDto branchRequestDto) {
        return branchService.saveBranch(branchRequestDto);
    }

    @PutMapping("/{id}")
    public BranchsResponseDto updateBranch(@PathVariable UUID id, @RequestBody BranchsRequestDto branchRequestDto) {
        return branchService.updateBranch(id, branchRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable UUID id) {
        branchService.deleteBranch(id);
    }
}
