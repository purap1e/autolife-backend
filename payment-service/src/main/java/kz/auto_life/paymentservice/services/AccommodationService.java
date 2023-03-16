package kz.auto_life.paymentservice.services;

import kz.auto_life.paymentservice.models.Accommodation;
import kz.auto_life.paymentservice.payload.AccommodationRequest;
import kz.auto_life.paymentservice.payload.AccommodationResponse;

import java.util.List;

public interface AccommodationService {
    Accommodation save(AccommodationRequest request);
    List<AccommodationResponse> getAll();
}
