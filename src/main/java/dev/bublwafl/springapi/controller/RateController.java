package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddRateDTO;
import dev.bublwafl.springapi.DTO.RateDTO;
import dev.bublwafl.springapi.DTO.UpdateRateDTO;
import dev.bublwafl.springapi.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
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

    @GetMapping("/all")
    public List<RateDTO> readAll() {
        return rateService.readAll();
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