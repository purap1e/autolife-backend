package kz.auto_life.taxservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.taxservice.exceptions.UnauthorizedException;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kz.auto_life.taxservice.payload.VehicleRequest;
import kz.auto_life.taxservice.payload.VehicleResponse;

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
    public ResponseEntity<VehicleResponse> createVehicle(@RequestHeader("Authorization") String token,
                                                         @RequestBody VehicleRequest vehicleRequest) {
        if (token == null || !token.equals("Basic YWRtaW46cGFzc3dvcmQ=")) {
            throw new UnauthorizedException("Invalid credentials");
        }

        Vehicle vehicle = vehicleService.save(vehicleRequest);
        VehicleResponse vehicleResponse = VehicleResponse.builder()
                .userIin(vehicle.getUserIin())
                .grnz(vehicle.getGrnz())
                .vehicleType(vehicle.getType())
                .attributes(vehicleRequest.getAttributes()).build();
        return ResponseEntity.status(201).body(vehicleResponse);
    }
}
