package kz.auto_life.finecarservice.services;

import kz.auto_life.finecarservice.models.Fine;
import kz.auto_life.finecarservice.payload.FineRequest;
import kz.auto_life.finecarservice.payload.FineResponse;
import kz.auto_life.finecarservice.payload.WithdrawRequest;

import java.util.List;

public interface FineService {
    List<FineResponse> getAll(String userIin, Boolean paid);

    List<FineResponse> update(WithdrawRequest request);
    Fine create(FineRequest request);
}
