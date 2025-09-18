package com.closet.board.service;

import com.closet.board.bean.ReviewDTO;
import com.closet.board.bean.SellBoardDTO;
import com.closet.board.entity.ReviewEntity;
import com.closet.board.entity.SellEntity;
import com.closet.board.exception.NotFoundException;
import com.closet.board.exception.UnauthorizedException;
import com.closet.board.repository.ReviewRepository;
import com.closet.board.repository.SellBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final SellBoardRepository sellRepository;
    private final ReviewRepository reviewRepository;

    // 판매글 전체 조회 (페이징)
    public Page<SellBoardDTO> getAllSells(Pageable pageable) {
        return sellRepository.findAll(pageable)
                .map(SellBoardDTO::fromEntity);
    }

    // 판매글 상세 조회 (조회수 증가 포함)
    @Transactional
    public SellBoardDTO  getSell(Long id) {
        SellEntity entity = sellRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("글을 찾을 수 없습니다."));

        // 조회수 증가
        entity.setView(entity.getView() + 1);

        return SellBoardDTO.fromEntity(entity);
    }

    // 판매글 작성
    public SellBoardDTO createSell(SellBoardDTO dto) {
        SellEntity entity = dto.toEntity();
        SellEntity saved = sellRepository.save(entity);
        return SellBoardDTO.fromEntity(saved);
    }

    // 판매글 수정 (작성자만 가능)
    @Transactional
    public SellBoardDTO updateSell(Long id, SellBoardDTO dto, Long currentUserId) {
        SellEntity entity = sellRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("글을 찾을 수 없습니다."));

        if (!entity.getSeller_id().equals(currentUserId)) {
            throw new UnauthorizedException("수정 권한이 없습니다.");
        }

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setThumbnail_image(dto.getThumbnail_image());
        entity.setDaily_fee(dto.getDaily_fee());
        entity.setLate_fee(dto.getLate_fee());
        entity.setDeposit(dto.getDeposit());
        entity.setLocation(dto.getLocation());
        entity.setProduct_state(dto.getProduct_state());
        entity.setUpdated_at(new Date());

        return SellBoardDTO.fromEntity(entity);
    }

    // 판매글 삭제 (작성자만 가능)
    public void deleteSell(Long id, Long currentUserId) {
        SellEntity entity = sellRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("글을 찾을 수 없습니다."));

        if (!entity.getSeller_id().equals(currentUserId)) {
            throw new UnauthorizedException("삭제 권한이 없습니다.");
        }

        sellRepository.delete(entity);
    }

    // 리뷰 조회
    public List<ReviewDTO> getReviews(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(ReviewDTO::fromEntity)
                .toList();
    }


    // 리뷰 작성
    public ReviewDTO createReview(ReviewDTO dto) {
        ReviewEntity entity = dto.toEntity();
        ReviewEntity saved = reviewRepository.save(entity);
        return ReviewDTO.fromEntity(saved);
    }

    // 리뷰 삭제 (작성자만 가능)
    public void deleteReview(Long id, Long currentUserId) {
        ReviewEntity entity = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("리뷰를 찾을 수 없습니다."));

        if (!entity.getReviewer_id().equals(currentUserId)) {
            throw new UnauthorizedException("삭제 권한이 없습니다.");
        }

        reviewRepository.delete(entity);
    }


}
