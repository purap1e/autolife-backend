package kz.auto_life.stoservice.mappers;

import kz.auto_life.stoservice.models.Sto;
import kz.auto_life.stoservice.payload.StoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

@Service
public class StoMapper implements Function<Sto, StoDTO> {
    @Override
    public StoDTO apply(Sto sto) {
        return new StoDTO(
                sto.getId(),
                sto.getTitle(),
                sto.getPhone(),
                sto.getDescription(),
                sto.getLocation(),
                sto.getImages()
                        .stream()
                        .map(image -> ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/api/sto/images/" + image.getId())
                        .toList()
        );
    }
}
