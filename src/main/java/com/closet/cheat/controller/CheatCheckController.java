package com.closet.cheat.controller;

import com.closet.cheat.bean.CheatRequest;
import com.closet.cheat.bean.CheatResponse;
import com.closet.cheat.service.CheatCheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cheat")
public class CheatCheckController {
    private final CheatCheckService cheatCheckService;

    public CheatCheckController(CheatCheckService cheatCheckService){
        this.cheatCheckService = cheatCheckService;
    }

    // 전화번호나 계좌번호 입력 시, 사기 기록이 있는지 횟수와 종류를 판별하여 전송.
    @PostMapping("/check")
    public ResponseEntity<CheatResponse> checkFraud(@RequestBody CheatRequest request) {
        // request: {"account": "123-456-789","phone": "010-1234-5678"}
        CheatResponse response = cheatCheckService.checkFraud(request);
        // response: {"cheat": true,"type": "보이스피싱","cnt": 3}
        return ResponseEntity.ok(response);
    }

}
