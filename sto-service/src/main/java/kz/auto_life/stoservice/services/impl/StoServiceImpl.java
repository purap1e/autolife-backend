package kz.auto_life.stoservice.services.impl;

import kz.auto_life.stoservice.exceptions.InvalidCredentialsException;
import kz.auto_life.stoservice.mappers.StoMapper;
import kz.auto_life.stoservice.models.Image;
import kz.auto_life.stoservice.models.Sto;
import kz.auto_life.stoservice.payload.ResponseMessage;
import kz.auto_life.stoservice.payload.StoDTO;
import kz.auto_life.stoservice.repositories.StoRepository;
import kz.auto_life.stoservice.services.StoService;
import kz.auto_life.stoservice.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoServiceImpl implements StoService {

    private final StoRepository stoRepository;
    private final StoMapper stoMapper;

    public List<Image> getList(List<MultipartFile> images) {
        List<Image> files = new ArrayList<>();
        images.forEach(file -> {
            try {
                Image image = new Image();
                image.setName(file.getOriginalFilename());
                image.setType(file.getContentType());
                image.setData(ImageUtils.compressImage(file.getBytes()));
                files.add(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return files;
    }

    @Override
    public UUID save(String title, String phone, String description, String location, List<MultipartFile> images) {
        log.info("Saving sto with images");
        Sto sto = new Sto();

        sto.setTitle(title);
        sto.setPhone(phone);
        sto.setDescription(description);
        sto.setLocation(location);
        sto.setImages(getList(images));
        stoRepository.saveAndFlush(sto);
        return sto.getId();
    }

    @Override
    @Transactional
    public StoDTO get(UUID uuid) {
        log.info("fetching sto with id {}", uuid);
        return stoRepository.findById(uuid).map(stoMapper).orElseThrow(() -> new InvalidCredentialsException(new ResponseMessage("Sto not found")));
    }

    @Override
    @Transactional
    public List<StoDTO> getAll() {
        log.info("Fetching all sto");
        return stoRepository.findAll()
                .stream()
                .map(stoMapper)
                .toList();
    }
}
