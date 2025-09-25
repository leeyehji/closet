package com.closet.board.bean;

import com.closet.board.entity.ReviewEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class ReviewDTO {
    // 리뷰 글 번호
    private Long id;

    // 리뷰작성자.
    private Long reviewerId;
    // 📢리뷰작성대상자 생략함. 필요없을듯.
    // 리뷰대상글.
    private Long productId;

    // 리뷰 내용
    private String content;

    // 생성일시
    private Date createdAt;
    // 수정일시
    private Date updatedAt;

    // ✅ Entity → DTO
    public static ReviewDTO fromEntity(ReviewEntity entity) {
        return ReviewDTO.builder()
                .id(entity.getId())
                .reviewerId(entity.getReviewerId())
                .productId(entity.getProductId())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // ✅ DTO → Entity
    public ReviewEntity toEntity() {
        ReviewEntity entity = new ReviewEntity();
        entity.setId(this.id);
        entity.setReviewerId(this.reviewerId);
        entity.setProductId(this.productId);
        entity.setContent(this.content);
        entity.setCreatedAt(this.createdAt);
        entity.setUpdatedAt(this.updatedAt);
        return entity;
    }
}
