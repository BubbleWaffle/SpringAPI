package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddRateDTO;
import dev.bublwafl.springapi.DTO.RateDTO;
import dev.bublwafl.springapi.DTO.UpdateRateDTO;
import dev.bublwafl.springapi.service.RateService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
@Validated
public class RateController {
    private final RateService rateService;
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping
    public RateDTO create(@RequestBody AddRateDTO rate) {
        return rateService.create(rate);
    }

    @GetMapping("/{id}")
    public RateDTO read(@PathVariable Long id) {
        return rateService.read(id);
    }

    @GetMapping("/chunk")
    public Page<RateDTO> readChunk(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "3") @Min(1) @Max(3) int size) {
        return rateService.readChunk(page, size);
    }

    @PutMapping("/{id}")
    public RateDTO update(@RequestBody UpdateRateDTO rate, @PathVariable Long id) {
        return rateService.update(rate, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rateService.delete(id);
    }
}