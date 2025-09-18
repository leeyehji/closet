package com.closet.cheat.bean;

import lombok.Data;

@Data
public class CheatResponse {
    // 사기 여부
    private boolean cheat;
    // 사기 종류
    private String type;
    // 사기 건수
    private int cnt;

    public CheatResponse(boolean cheat, String type, int cnt){
        this.cheat = cheat;
        this.type = type;
        this.cnt = cnt;
    }
}
