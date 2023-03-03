package kz.auto_life.taxservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.payload.TaxResponse;
import kz.auto_life.taxservice.services.CheckTypeService;
import kz.auto_life.taxservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kz.auto_life.taxservice.payload.VehicleRequest;
import kz.auto_life.taxservice.payload.VehicleResponse;

import java.util.List;


@Tag(name = "Auth API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle/")
public class TestController {

    private final VehicleService vehicleService;
    private final CheckTypeService checkTypeService;

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = (Vehicle) checkTypeService.createObject(vehicleRequest);
        VehicleResponse vehicleResponse = VehicleResponse.builder()
                .userIin(vehicle.getUserIin())
                .grnz(vehicle.getGrnz())
                .vehicleType(vehicle.getType())
                .attributes(vehicleRequest.getAttributes()).build();
        return ResponseEntity.status(201).body(vehicleResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaxResponse>> getAll() {
        return ResponseEntity.ok().body(vehicleService.getAll());
    }
}
