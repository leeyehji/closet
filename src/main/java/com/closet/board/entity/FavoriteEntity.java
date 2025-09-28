package com.closet.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name ="favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 찜한 사용자
    private Long userId;
    //찜 대상 물품
    private Long productId;
    //찜 생성 일시
    private Date cratedAt;
}
