
-- 用户信息表
CREATE TABLE user_info
(
    id BIGINT IDENTITY PRIMARY KEY ,
    email VARCHAR(20) NOT NULL,
    pwd VARCHAR(20) NOT NULL,
    user_name VARCHAR(20) NOT NULL,
    created_time DATETIME,
    login_time DATETIME
);

-- 收入表