package com.closet.board.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Date;

@Data
@Entity
@Table(name = "sell")
public class SellEntity {
    // 글 번호. pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // user 테이블의 fk
    private Long seller_id;
    // user 테이블의 fk
    @Nullable
    private Long buyer_id;
    // category테이블의 fk
    @DefaultValue(기타)
    private Long category_id;
    // 옷사이즈 테이블fk
    @DefaultValue(free)
    private Long size_id;
    // 옷색상테이블 fk
    @DefaultValue(mix)
    private Long color_id;

    // 보증금
    private int deposit;
    // 일일대여료
    private int daily_fee;
    // 일일연체료
    private int late_fee;

    // 글제목
    private String title;
    // 상세 내용
    @Nullable
    private String description;
    // 썸네일이미지주소
    @Nullable
    private String thumbnail_image;
    // 지역
    @Nullable
    private String location;
    // 물품의 상태
    @DefaultValue(possible)
    private String product_state;

    // 조회수
    private int view;
    // 생성일시
    private Date created_at;
    // 수정일시
    private Date updated_at;

    // 찜여부
    @DefaultValue(false)
    private boolean favorite_product;
}

