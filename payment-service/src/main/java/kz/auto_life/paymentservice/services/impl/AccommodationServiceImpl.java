package kz.auto_life.paymentservice.services.impl;

import kz.auto_life.paymentservice.mappers.AccommodationMapper;
import kz.auto_life.paymentservice.models.Accommodation;
import kz.auto_life.paymentservice.payload.AccommodationRequest;
import kz.auto_life.paymentservice.payload.AccommodationResponse;
import kz.auto_life.paymentservice.repositories.ServiceRepository;
import kz.auto_life.paymentservice.services.AccommodationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final ServiceRepository serviceRepository;
    private final AccommodationMapper accommodationMapper;

    @Override
    public Accommodation save(AccommodationRequest request) {
        Accommodation accommodation = new Accommodation();
        accommodation.setName(request.getName());
        log.info("Saving new accommodation to the database");
        return serviceRepository.save(accommodation);
    }

    @Override
    public List<AccommodationResponse> getAll() {
        log.info("Fetching all accommodations");
        return serviceRepository
                .findAll()
                .stream()
                .map(accommodationMapper).toList();
    }
}
