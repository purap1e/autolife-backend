package kz.auto_life.taxservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.payload.VehicleRequest;
import kz.auto_life.taxservice.payload.VehicleResponse;
import kz.auto_life.taxservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Vehicle API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(summary = "Создание автомобиля(доступ есть только у админов)",
            description = "создает автомобиль 3 видов")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleService.save(vehicleRequest);
        VehicleResponse vehicleResponse = VehicleResponse.builder()
                .userIin(vehicle.getUserIin())
                .grnz(vehicle.getGrnz())
                .vehicleType(vehicle.getType())
                .attributes(vehicleRequest.getAttributes()).build();
        return ResponseEntity.status(201).body(vehicleResponse);
    }

    @Operation(summary = "Вывод 1 автомобиля(доступ есть только у админов)",
            description = "Вывод 1 автомобиля по гос. номеру")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public Vehicle get(@RequestParam String grnz) {
        return vehicleService.get(grnz);
    }
}
