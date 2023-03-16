package kz.auto_life.paymentservice.mappers;

import kz.auto_life.paymentservice.models.Accommodation;
import kz.auto_life.paymentservice.payload.AccommodationResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccommodationMapper implements Function<Accommodation, AccommodationResponse> {
    @Override
    public AccommodationResponse apply(Accommodation accommodation) {
        return new AccommodationResponse(
                accommodation.getId(),
                accommodation.getName());
    }
}
