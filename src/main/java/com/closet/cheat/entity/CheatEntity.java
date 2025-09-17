package com.closet.cheat.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cheat")
public class CheatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;
    private String phone;
    //사기 종류
    private String type;
    //사기 건 수
    private int cnt;
}
