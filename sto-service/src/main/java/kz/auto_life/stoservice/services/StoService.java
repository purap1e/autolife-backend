package kz.auto_life.stoservice.services;

import kz.auto_life.stoservice.payload.StoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface StoService {
    UUID save(String title, String phone, String description, String location, List<MultipartFile> images);

    void updateGrade(double grade, UUID stoId);

    StoDTO get(UUID uuid);

    List<StoDTO> getAll();
}
