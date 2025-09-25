package com.closet.board.bean;

import com.closet.board.entity.SellEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Date;

@Data
@Builder
public class SellBoardDTO {
    // 🧷게시글 보여주는 형식에 따라 제목과 썸네일 주소, 글 번호만 가지는 DTO를 추가로 만들어도 좋을 듯(로딩 속도 개선 목적).
    // 글 번호
    private Long id;
    private Long sellerId;
    private Long buyerId;
    private Long categoryId;

    // 옷사이즈 테이블fk.
    private Long sizeId;
    // 옷색상테이블 fk
    private Long colorId;

    // 보증금
    private int deposit;
    // 일일대여료
    private int dailyFee;
    // 일일연체료
    private int lateFee;

    // 글제목
    private String title;
    // 상세 내용
    private String description;
    // 썸네일이미지주소
    private String thumbnailImage;
    // 지역
    private String location;
    // 물품의 상태
    private String productState;

    // 조회수
    private int view;
    // 생성일시
    private Date createdAt;
    // 수정일시
    private Date updatedAt;

    // 찜여부
    private boolean favoriteProduct;

    // ✅ Entity → DTO 변환
    public static SellBoardDTO fromEntity(SellEntity entity) {
        return SellBoardDTO.builder()
                .id(entity.getId())
                .sellerId(entity.getSellerId())
                .buyerId(entity.getBuyerId())
                .categoryId(entity.getCategoryId())
                .deposit(entity.getDeposit())
                .dailyFee(entity.getDailyFee())
                .lateFee(entity.getLateFee())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .thumbnailImage(entity.getThumbnailImage())
                .location(entity.getLocation())
                .productState(entity.getProductState())
                .view(entity.getView())
//                .created_at(entity.getCreated_at())
//                .updated_at(entity.getUpdated_at())
                .favoriteProduct(entity.isFavoriteProduct())
                .build();
    }

    // ✅ DTO → Entity 변환
    public SellEntity toEntity() {
        SellEntity entity = new SellEntity();
        entity.setId(this.id);
        entity.setSellerId(this.sellerId);
        entity.setBuyerId(this.buyerId);
        entity.setCategoryId(this.categoryId);
        entity.setDeposit(this.deposit);
        entity.setDailyFee(this.dailyFee);
        entity.setLateFee(this.lateFee);
        entity.setTitle(this.title);
        entity.setDescription(this.description);
        entity.setThumbnailImage(this.thumbnailImage);
        entity.setLocation(this.location);
        entity.setProductState(this.productState);
        entity.setView(this.view);
//        entity.setCreatedAt(new Date());  // ✅ 현재 시간 세팅
//        entity.setUpdatedAt(new Date());  // ✅ 현재 시간 세팅
        entity.setFavoriteProduct(this.favoriteProduct);
        return entity;
    }

}

