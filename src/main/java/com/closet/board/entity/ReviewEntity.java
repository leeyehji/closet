package com.closet.board.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리뷰작성자. user테이블 fk
    private Long reviewer_id;
    // 리뷰대상글. product테이블 fk
    private Long product_id;

    // 리뷰 내용
    private String content;

    // 생성일시
    private Date created_at;
    // 수정일시
    private  Date updated_at;
}
