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
