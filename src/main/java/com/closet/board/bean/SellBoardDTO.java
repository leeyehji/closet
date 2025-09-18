package com.closet.board.bean;

import com.closet.board.entity.SellEntity;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class SellBoardDTO {
    // 🧷게시글 보여주는 형식에 따라 제목과 썸네일 주소, 글 번호만 가지는 DTO를 추가로 만들어도 좋을 듯(로딩 속도 개선 목적).
    // 글 번호
    private Long id;
    private Long seller_id;
    private Long buyer_id;
    private Long category_id;

    // 보증금
    private int deposit;
    // 일일대여료
    private int daily_fee;
    // 일일연체료
    private int late_fee;

    // 글제목
    private String title;
    // 상세 내용
    private String description;
    // 썸네일이미지주소
    private String thumbnail_image;
    // 지역
    private String location;
    // 물품의 상태
    private String product_state;

    // 조회수
    private int view;
    // 생성일시
    private Date created_at;
    // 수정일시
    private Date updated_at;

    // 찜여부
    private boolean favorite_product;

    // ✅ Entity → DTO 변환
    public static SellBoardDTO fromEntity(SellEntity entity) {
        return SellBoardDTO.builder()
                .id(entity.getId())
                .seller_id(entity.getSeller_id())
                .buyer_id(entity.getBuyer_id())
                .category_id(entity.getCategory_id())
                .deposit(entity.getDeposit())
                .daily_fee(entity.getDaily_fee())
                .late_fee(entity.getLate_fee())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .thumbnail_image(entity.getThumbnail_image())
                .location(entity.getLocation())
                .product_state(entity.getProduct_state())
                .view(entity.getView())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .favorite_product(entity.isFavorite_product())
                .build();
    }

    // ✅ DTO → Entity 변환
    public SellEntity toEntity() {
        SellEntity entity = new SellEntity();
        entity.setId(this.id);
        entity.setSeller_id(this.seller_id);
        entity.setBuyer_id(this.buyer_id);
        entity.setCategory_id(this.category_id);
        entity.setDeposit(this.deposit);
        entity.setDaily_fee(this.daily_fee);
        entity.setLate_fee(this.late_fee);
        entity.setTitle(this.title);
        entity.setDescription(this.description);
        entity.setThumbnail_image(this.thumbnail_image);
        entity.setLocation(this.location);
        entity.setProduct_state(this.product_state);
        entity.setView(this.view);
        entity.setCreated_at(this.created_at);
        entity.setUpdated_at(this.updated_at);
        entity.setFavorite_product(this.favorite_product);
        return entity;
    }
}
