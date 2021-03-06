-- MEMBER dummy
INSERT INTO `MEMBER`
    (`member_no`, `create_time`, `modified_time`, `member_name`)
VALUES
    ('1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'already'),
    ('2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'two'),
    ('3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'three'),
    ('4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'four'),
    ('5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'five'),
    ('6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '김봉진');

-- MENU dummy
INSERT INTO `MENU`
    (`menu_no`, `create_time`, `modified_time`, `menu_name`, `menu_price`)
VALUES
    ('1', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '간장치킨', '18000'),
    ('2', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '마늘치킨', '18000'),
    ('3', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '양념치킨', '18000'),
    ('4', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '치즈볼(4개)', '4000'),
    ('5', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '삭제치킨', '10000');

INSERT INTO `COUPON_TYPE`
    (`coupon_type_no`, `create_time`, `modified_time`, `coupon_type_name`, `coupon_type_has_code`, `coupon_type_count`, `coupon_type_discount`, `coupon_usable_start_time`, `coupon_usable_end_time`)
VALUES
    ('1', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '이달의 카피라이터', true, '100', '18000', '2020-01-20 15:13:20', '2021-01-19 15:13:20'),
    ('2', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '민철이가 쏜다', true,  '50', '10000', '2020-01-18 15:13:20', '2020-01-30 15:13:20'),
    ('3', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '배달의 민족 첫 주문할인', true,  '1000', '3000', '2020-01-20 15:13:20', '2021-01-19 15:13:20'),
    ('4', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '치킨 복날 이벤트', false,  '10000', '3000', '2020-01-20 15:13:20', '2021-01-19 15:13:20'),
    ('5', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '긴급 깜짝 할인', false,  '100', '20000', '2020-01-10 15:13:20', '2020-01-11 15:13:20'),
    ('6', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '봉대표가 쏜다', true,  '10', '200000', '2020-01-10 15:13:20', '2030-01-11 15:13:20'),
    ('7', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '쿠폰팀이 몰래 만든 쿠폰', false, '10', '20000', '2020-01-10 15:13:20', '2020-01-11 15:13:20');

INSERT INTO `COUPON`
    (`coupon_no`, `create_time`, `modified_time`, `coupon_code`, `coupon_status`, `coupon_type_no`)
VALUES
    ('1', '2020-01-14 21:25:49', '2020-01-14 21:25:49', 'ABCDEF123456', 'USABLE', '6'),
    ('2', '2020-01-14 21:25:49', '2020-01-14 21:25:49', 'GHIJKLM78901', 'USABLE', '6'),
    ('3', '2020-01-14 21:25:49', '2020-01-14 21:25:49', 'NOPQRS123456', 'USED', '6'),
    ('4', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '123456ABCDEF', 'USED', '7'),
    ('5', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '789012GHIJKL', 'USED', '7'),
    ('6', '2020-01-14 21:25:49', '2020-01-14 21:25:49', 'Q1W2E3R4T5Y6', 'USABLE', '7');

INSERT INTO `COUPON`
    (`coupon_no`, `create_time`, `modified_time`, `coupon_code`, `coupon_status`, `coupon_type_no`, `coupon_member_no`)
VALUES
    ('7', '2020-01-14 21:25:49', '2020-01-14 21:25:49', 'ABCDEF789012', 'USABLE', '1', 2),
    ('8', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '1Q2W3E4R5T6Y', 'USABLE', '1', 1),
    ('9', '2020-01-14 21:25:49', '2020-01-14 21:25:49', 'A1S2D3F4G5H6', 'USABLE', '6', 6);

INSERT INTO `ORDER`
    (`order_no`, `create_time`, `modified_time`, `order_price`, `order_status`, `order_quantity`, `order_menu_no`, `order_member_no`)
VALUES
    ('1', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '54000', 'CREATE', '3', '1', '1'),
    ('2', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '18000', 'CREATE', '1', '1', '2'),
    ('3', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '18000', 'CREATE', '1', '1', '2'),

    ('4', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '18000', 'IN_PROGRESS', '1', '2', '1'),
    ('5', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '18000', 'COMPLETE', '1', '1', '3'),
    ('6', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '40000', 'CANCELED', '10', '4', '4'),
    ('7', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '18000', 'IN_PROGRESS', '1', '3', '5'),
    ('8', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '54000', 'COMPLETE', '3', '3', '5'),
    ('9', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '4000', 'CANCELED', '1', '4', '5'),
    ('10', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '12000', 'IN_PROGRESS', '3', '4', '3'),
    ('11', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '36000', 'COMPLETE', '2', '1', '4'),
    ('12', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '18000', 'CANCELED', '1', '2', '1'),

    ('13', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '18000', 'CANCELED', '1', '2', '1'),

    ('14', '2020-01-14 21:25:49', '2020-01-14 21:25:49', '180000', 'CREATE', '10', '2', '1');
