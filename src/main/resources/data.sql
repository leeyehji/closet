--cheat
INSERT INTO cheat (account, phone, type, cnt)
VALUES ('123-456-789', '010-1234-5678', '보이스피싱', 2);

INSERT INTO cheat (account, phone, type, cnt)
VALUES ('123-456-789', '010-1234-5678', '물품사기', 3);

INSERT INTO cheat (account, phone, type, cnt)
VALUES ('123-456-789', '010-1234-5678', '금품갈취', 5);

INSERT INTO cheat (account, phone, type, cnt)
VALUES ('987-654-321', '010-8765-4321', '물품사기', 1);

--sell
INSERT INTO sell (
    seller_id,
    buyer_id,
    category_id,
    size_id,
    color_id,
    deposit,
    daily_fee,
    late_fee,
    title,
    description,
    thumbnail_image,
    location,
    product_state,
    view,
    created_at,
    updated_at,
    favorite_product
) VALUES
      (1234, NULL, 1, 2, 3, 10000, 3000, 500,
       '구찌 자켓 대여합니다',
       '한 번 착용한 구찌 자켓입니다. 상태 최상.',
       'https://example.com/image/jacket.png',
       '서울 강남구',
       '최상',
       0,
       NOW(),
       NOW(),
       FALSE
      ),
      (1235, NULL, 2, 1, 4, 5000, 2000, 300,
       '나이키 운동화 대여합니다',
       '새 운동화입니다. 편안하고 가벼움.',
       'https://example.com/image/shoes.png',
       '서울 송파구',
       '좋음',
       0,
       NOW(),
       NOW(),
       FALSE
      ),
      (1236, NULL, 3, 3, 2, 15000, 5000, 1000,
       '버버리 코트 대여합니다',
       '겨울용 코트, 상태 깨끗함.',
       'https://example.com/image/coat.png',
       '서울 강북구',
       '최상',
       0,
       NOW(),
       NOW(),
       FALSE
      );


--review
INSERT INTO review (reviewer_id, product_id, content, created_at, updated_at)
VALUES
    (1234, 1, '상품 상태가 정말 좋습니다. 추천합니다!', NOW(), NOW()),
    (1235, 1, '배송도 빠르고 상품 만족!', NOW(), NOW()),
    (1236, 2, '사진과 똑같아요. 착용감도 좋습니다.', NOW(), NOW()),
    (1237, 2, '친절한 판매자였습니다. 감사합니다!', NOW(), NOW()),
    (1238, 3, '가격 대비 만족도 높아요.', NOW(), NOW());
