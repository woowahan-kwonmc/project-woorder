-- MEMBER dummy
INSERT INTO `MEMBER`
    (`member_no`, `create_time`, `modified_time`, `member_name`)
VALUES
    ('1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'already');

-- MENU dummy
INSERT INTO `MENU`
    (`menu_no`, `create_time`, `modified_time`, `menu_name`, `menu_price`)
VALUES
    ('1', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '간장치킨', '18000'),
    ('2', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '마늘치킨', '18000'),
    ('3', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '양념치킨', '18000'),
    ('4', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '치즈볼(4개)', '4000');

INSERT INTO `COUPON_TYPE`
    (`coupon_type_no`, `create_time`, `modified_time`, `coupon_type_name`, `coupon_type_count`, `coupon_type_discount`, `coupon_usable_start_time`, `coupon_usable_end_time`)
VALUES
    ('1', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '이달의 카피라이터', '100', '18000', '2020-01-20 15:13:20', '2020-01-22 15:13:20'),
    ('2', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '민철이가 쏜다', '50', '10000', '2020-01-18 15:13:20', '2020-01-30 15:13:20'),
    ('3', '2020-01-10 15:13:20', '2020-01-10 15:13:20', '배달의 민족 첫 주문할인', '1000', '3000', '2020-01-20 15:13:20', '2021-01-19 15:13:20');
