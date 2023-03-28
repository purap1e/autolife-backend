package kz.auto_life.finecarservice.services.impl;

import kz.auto_life.finecarservice.mappers.FineResponseMapper;
import kz.auto_life.finecarservice.models.Fine;
import kz.auto_life.finecarservice.payload.FineRequest;
import kz.auto_life.finecarservice.payload.FineResponse;
import kz.auto_life.finecarservice.payload.WithdrawRequest;
import kz.auto_life.finecarservice.repositories.FineRepository;
import kz.auto_life.finecarservice.services.FineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FineServiceImpl implements FineService {
    private final FineRepository fineRepository;
    private final FineResponseMapper fineResponseMapper;

    @Override
    public List<FineResponse> getAll(String userIin, Boolean paid) {
        log.info("Fetching all fines by iin");
        return fineRepository.findALlByIInAndPaid(userIin, paid)
                .stream()
                .map(fineResponseMapper)
                .toList();
    }

    public Fine getById(UUID id) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new RuntimeException("Tax not found"));
        fine.setPaid(true);
        return fineRepository.save(fine);
    }

    @Override
    public List<FineResponse> update(WithdrawRequest request) {
        List<Fine> fines = new ArrayList<>();
        request.getAttributes()
                .forEach(x -> fines.add(getById(x.getId())));
        return fines.stream().map(fineResponseMapper).toList();
    }

    @Override
    public Fine create(FineRequest request) {
        Fine fine = new Fine();
        fine.setVehicleId(request.getVehicleId());
        fine.setDescription(request.getDescription());
        fine.setAmountMci(request.getAmountOfMci());

        return fineRepository.save(fine);
    }
}
