package com.closet.cheat.bean;

import lombok.Data;

@Data
public class CheatResponse {
    private boolean cheat;   // 사기 여부
    private String type;     // 사기 종류
    private int cnt;         // 사기 건수

    public CheatResponse(boolean cheat, String type, int cnt){
        this.cheat = cheat;
        this.type = type;
        this.cnt = cnt;
    }
}
