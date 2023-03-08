package kz.auto_life.vehicleservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.vehicleservice.models.Vehicle;
import kz.auto_life.vehicleservice.services.CheckTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kz.auto_life.vehicleservice.payload.VehicleRequest;
import kz.auto_life.vehicleservice.payload.VehicleResponse;


@Tag(name = "Auth API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle/")
public class TestController {
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
}
