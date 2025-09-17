package com.closet.cheat.service;

import com.closet.cheat.bean.CheatRequest;
import com.closet.cheat.bean.CheatResponse;
import com.closet.cheat.entity.CheatEntity;
import com.closet.cheat.repository.CheatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CheatCheckService {
    @Autowired
    private CheatRepository cheatRepository;

    public CheatResponse  checkFraud(CheatRequest request) {
        boolean cheat = false;
        List<CheatEntity> cheats = cheatRepository.findAllByAccountOrPhone(request.getAccount(), request.getPhone());
        // 사기 이력이 없다면
        if (cheats.isEmpty()) {
            log.info("No fraud record found for account={} or phone={}", request.getAccount(), request.getPhone());
            return new CheatResponse(false, null, 0);
        }

        // 전체 건수 합산
        int totalCnt = cheats.stream().mapToInt(CheatEntity::getCnt).sum();

        // 대표 type 선택 (예: 첫 번째 유형)
        String type = cheats.get(0).getType() + (cheats.size()>1 ?" 등" : "");
        // 여러 유형을 모두 표시하고 싶으면
        // String types = cheats.stream().map(CheatEntity::getType).distinct().collect(Collectors.joining(", "));

        // 💥 이거 한 계좌에 여러 종류의 유형이 있을 경우 계산법 바꿔야함.
        log.info("Fraud detected! Total count={}, Type(s)={}", totalCnt, type);
        log.debug("All records: {}", cheats);

        return new CheatResponse(true, type, totalCnt);

    }
}
