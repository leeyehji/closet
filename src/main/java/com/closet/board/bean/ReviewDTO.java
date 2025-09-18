package com.closet.board.bean;

import com.closet.board.entity.ReviewEntity;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;


@Data
@Builder
public class ReviewDTO {
    // 리뷰 글 번호
    private Long id;

    // 리뷰작성자.
    private Long reviewer_id;
    // 📢리뷰작성대상자 생략함. 필요없을듯.
    // 리뷰대상글.
    private Long product_id;

    // 리뷰 내용
    private String content;

    // 생성일시
    private Date created_at;
    // 수정일시
    private  Date updated_at;

    // ✅ Entity → DTO
    public static ReviewDTO fromEntity(ReviewEntity entity) {
        return ReviewDTO.builder()
                .id(entity.getId())
                .reviewer_id(entity.getReviewer_id())
                .product_id(entity.getProduct_id())
                .content(entity.getContent())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .build();
    }

    // ✅ DTO → Entity
    public ReviewEntity toEntity() {
        ReviewEntity entity = new ReviewEntity();
        entity.setId(this.id);
        entity.setReviewer_id(this.reviewer_id);
        entity.setProduct_id(this.product_id);
        entity.setContent(this.content);
        entity.setCreated_at(this.created_at);
        entity.setUpdated_at(this.updated_at);
        return entity;
    }
}
