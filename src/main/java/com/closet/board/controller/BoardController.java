package com.closet.board.controller;

import com.closet.board.bean.ReviewDTO;
import com.closet.board.bean.SellBoardDTO;
import com.closet.board.entity.ReviewEntity;
import com.closet.board.entity.SellEntity;
import com.closet.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    
    // 판매글 조회 - 여러 글 보여줌
    @RequestMapping("/sells")
    public ResponseEntity<Page<SellBoardDTO>> getAllSells(Pageable pageable) {
        return ResponseEntity.ok(boardService.getAllSells(pageable));
    }
    // 판매글 상세 조회 - 글 하나 보여줌
    @RequestMapping("/sell/{id}}")
    public ResponseEntity<SellBoardDTO> getSell(@PathVariable Long id){
        // 현재 페이지기준 +-하여 몇 페이지 보여줄 지.
        SellBoardDTO dto = boardService.getSell(id);
        return ResponseEntity.ok(dto);
    }

    //판매글 작성
    @RequestMapping("/sell")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SellBoardDTO> createSell(@RequestBody SellBoardDTO  dto, Authentication authentication){
        // 글 작성
        Long userId = Long.parseLong(authentication.getName());
        dto.setSeller_id(userId);
        return ResponseEntity.ok(boardService.createSell(dto));
    }

    // 판매글 수정 (권한: 작성자만 가능)
    @RequestMapping("/sell/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SellBoardDTO> updateSell(@PathVariable Long id, @RequestBody SellBoardDTO  dto,Authentication authentication){
        // 어떤 글 수정할 지
        Long userId = Long.parseLong(authentication.getName());

        // 수정 완료 시 수정된 글 페이지로 이동
        return ResponseEntity.ok(boardService.updateSell(id, dto, userId));
    }

    // 판매글 삭제 (Role: USER 이상, Owner 체크는 서비스에서)
    @RequestMapping("/sell/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> sell_delete(@PathVariable Long id, Authentication authentication){
        // 어떤 글 삭제할 지.
        Long userId = Long.parseLong(authentication.getName());
        boardService.deleteSell(id, userId);
        return ResponseEntity.noContent().build();
    }

    // 리뷰 조회
    @RequestMapping("/review/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long productId) {
        // 현재 페이지기준 +-하여 몇 페이지 보여줄 지.
        return ResponseEntity.ok(boardService.getReviews(productId));
    }

    // 리뷰 작성 (권한: USER 이상)
    @RequestMapping("/review")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO dto, Authentication authentication){
        // 작성 후 작성된 글 페이지로 이동
        Long userId = Long.parseLong(authentication.getName());
        dto.setReviewer_id(userId);
        return ResponseEntity.ok(boardService.createReview(dto));
    }
    // 리뷰 수정 - 불가능하게 만들까?

    // 리뷰 삭제 (권한: 작성자만 가능)
    @RequestMapping("/review/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> deleteReview(@PathVariable Long id, Authentication authentication){
        // 현재 페이지기준 +-하여 몇 페이지 보여줄 지.
        Long userId = Long.parseLong(authentication.getName());
        boardService.deleteReview(id, userId);
        return ResponseEntity.noContent().build();
    }
}
