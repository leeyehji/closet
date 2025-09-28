package com.closet.cheat.service;

import com.closet.cheat.bean.CheatRequest;
import com.closet.cheat.bean.CheatResponse;
import com.closet.cheat.entity.CheatEntity;
import com.closet.cheat.repository.CheatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CheatCheckService {
    @Autowired
    private CheatRepository cheatRepository;

    // 사기 이력 검색 메서드
    public CheatResponse checkFraud(CheatRequest request) {
        List<CheatEntity> cheats = cheatRepository.findAllByAccountOrPhone(request.getAccount(), request.getPhone());

        // 사기 이력이 없다면
        if (cheats.isEmpty()) {
            log.info("사기 이력을 찾을 수 없습니다. account={} or phone={}", request.getAccount(), request.getPhone());
            return new CheatResponse(false, null, 0);
        }

        // 전체 건수 합산
        int totalCnt = cheats.stream().mapToInt(CheatEntity::getCnt).sum();

        // 대표 type 선택 (예: 첫 번째 유형)
        String type = cheats.getFirst().getType() + (cheats.size()>1 ?" 등" : "");
        // 여러 유형을 모두 표시하고 싶으면
        // String types = cheats.stream().map(CheatEntity::getType).distinct().collect(Collectors.joining(", "));

        log.info("사기 이력을 발견했습니다. Total count={}, Type(s)={}", totalCnt, type);
        log.debug("All records: {}", cheats);

        return new CheatResponse(true, type, totalCnt);

    }
}
