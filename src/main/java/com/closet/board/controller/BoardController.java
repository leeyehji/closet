package com.closet.board.controller;

import com.closet.board.bean.ReviewDTO;
import com.closet.board.bean.SellBoardDTO;
import com.closet.board.entity.FavoriteEntity;
import com.closet.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    
    // 판매글 조회 - 여러 글 보여줌
    //2025-09-25T11:08:47.928+09:00  WARN 28756 --- [closet] [nio-8080-exec-5] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
    //	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
    //	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
    //PageImpl을 그대로 JSON으로 직렬화하면 구조가 불안정할 수 있음 → 페이지 정보가 클라이언트에서 깨질 수 있음
//    @GetMapping("/sells")
//    public ResponseEntity<Page<SellBoardDTO>> getAllSells(Pageable pageable) {
//        return ResponseEntity.ok(boardService.getAllSells(pageable));
//    }
    @GetMapping("/sells")
    public ResponseEntity<Map<String, Object>> getSells(Pageable pageable) {
        Page<SellBoardDTO> page = boardService.getAllSells(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent());
        response.put("totalElements", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        response.put("pageNumber", page.getNumber());
        return ResponseEntity.ok(response);
    }

    // 판매글 상세 조회 - 글 하나 보여줌
    @GetMapping("/sell/{id}")
    public ResponseEntity<SellBoardDTO> getSell(@PathVariable("id") Long id){
        // 현재 페이지기준 +-하여 몇 페이지 보여줄 지.
        SellBoardDTO dto = boardService.getSell(id);
        return ResponseEntity.ok(dto);
    }

    // 판매글 작성
    @PostMapping("/sell")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SellBoardDTO> createSell(@RequestBody SellBoardDTO  dto){
        // TODO: authentication에서 꺼내쓰기
        dto.setSellerId(1234L);
        return ResponseEntity.ok(boardService.createSell(dto));
    }

    // 판매글 수정 (권한: 작성자만 가능)
    @PutMapping("/sell/{id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SellBoardDTO> updateSell(@PathVariable("id") Long id, @RequestBody SellBoardDTO  dto){
        // TODO: authentication에서 꺼내쓰기
        // 수정 완료 시 수정된 글 페이지로 이동
        return ResponseEntity.ok(boardService.updateSell(id, dto, 1234L));
    }

    // 판매글 삭제 (Role: USER 이상, Owner 체크는 서비스에서)
    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> sell_delete(@PathVariable Long id){
        // TODO: authentication에서 꺼내쓰기
        boardService.deleteSell(id, 1234L);
        return ResponseEntity.noContent().build();
    }

    // 찜 토글
    @PostMapping("/sell/{id}/favorite")
    //    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<String, Object>> toggleFavorite(@PathVariable("id") Long productId) {
        // TODO: authentication에서 꺼내쓰기
        Long userId = 1234L;
        boolean added = boardService.toggleFavorite(userId, productId);

        Map<String, Object> response = new HashMap<>();
        response.put("favorited", added);
        response.put("count", boardService.countFavorites(productId));

        return ResponseEntity.ok(response);
    }

    // 내 찜 목록 조회
    @PostMapping("/favorites")
    public ResponseEntity<List<FavoriteEntity>> getMyFavorites() {
        // TODO: authentication에서 꺼내쓰기
        Long userId = 1234L;
        return ResponseEntity.ok(boardService.getMyFavorites(userId));
    }

    // 판매글 찜 개수
    @GetMapping("/sell/{id}/favorites/count")
    public ResponseEntity<Long> getFavoriteCount(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(boardService.countFavorites(productId));
    }

    // 리뷰 조회
    @GetMapping("/reviews/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long productId) {
        // 현재 페이지기준 +-하여 몇 페이지 보여줄 지.
        return ResponseEntity.ok(boardService.getReviews(productId));
    }

    // 리뷰 작성 (권한: USER 이상)
    @PostMapping("/review")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO dto ){
        // 작성 후 작성된 글 페이지로 이동
// TODO: authentication에서 꺼내쓰기
        dto.setReviewerId(1234L);
        return ResponseEntity.ok(boardService.createReview(dto));
    }
    // 리뷰 수정 - 불가능하게 만들까?

    // 리뷰 삭제 (권한: 작성자만 가능)
    @DeleteMapping("/review/{id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        // TODO: authentication에서 꺼내쓰기
        // 현재 페이지기준 +-하여 몇 페이지 보여줄 지.
        boardService.deleteReview(id, 1234L);
        return ResponseEntity.noContent().build();
    }
}
