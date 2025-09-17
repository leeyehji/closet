package com.closet.cheat.service;

import com.closet.cheat.bean.CheatRequest;
import com.closet.cheat.bean.CheatResponse;
import com.closet.cheat.entity.CheatEntity;
import com.closet.cheat.repository.CheatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CheatCheckService {
    @Autowired
    private CheatRepository cheatRepository;

    public CheatResponse  checkFraud(CheatRequest request) {
        boolean cheat = false;
        CheatEntity cheatEntity = cheatRepository.findByAccountOrPhone(request.getAccount(), request.getPhone());
        // 사기 이력이 있다면
        if(cheatEntity != null){
            // 사기 발견
            // 💥 이거 한 계좌에 여러 종류의 유형이 있을 경우 계산법 바꿔야함.
            return new CheatResponse(true, cheatEntity.getType(), cheatEntity.getCnt());
        }


        return new CheatResponse(false, null, 0);
    }
}
