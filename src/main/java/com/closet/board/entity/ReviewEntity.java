package com.closet.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리뷰작성자. user테이블 fk
    private Long reviewerId;
    // 리뷰대상글. product테이블 fk
    private Long productId;

    // 리뷰 내용
    private String content;

    // 생성일시
    @CreationTimestamp
    private Date createdAt;
    // 수정일시
    @UpdateTimestamp
    private  Date updatedAt;
}
