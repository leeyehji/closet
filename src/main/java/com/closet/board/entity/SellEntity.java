package com.closet.board.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;


@Data
@Entity
@Table(name = "sell")
public class SellEntity {
    // 글 번호. pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // user 테이블의 fk
    private Long sellerId;
    // user 테이블의 fk
    @Nullable
    private Long buyerId;
    // category테이블의 fk. 0
    private Long categoryId = 0L;
    // 옷사이즈 테이블fk. free
    private Long sizeId =0L;
    // 옷색상테이블 fk. mix
    private Long colorId =0L;

    // 보증금
    private int deposit;
    // 일일대여료
    private int dailyFee;
    // 일일연체료
    private int lateFee;

    // 글제목
    private String title;
    // 상세 내용
    @Nullable
    private String description;
    // 썸네일이미지주소
    @Nullable
    private String thumbnailImage;
    // 지역
    @Nullable
    private String location;
    // 물품의 상태 . possible
    private String productState = "possible";

    // 조회수
    private int view;
    // 생성일시
    @CreationTimestamp // INSERT 시 자동 세팅
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    // 수정일시
    @UpdateTimestamp   // UPDATE 시 자동 세팅
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // 찜여부
    private boolean favoriteProduct = false;
}

