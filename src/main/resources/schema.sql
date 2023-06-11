CREATE TABLE IF NOT EXISTS "USER" (
    u_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS POST (
    p_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(50) NOT NULL,
    boardType VARCHAR(20) NOT NULL CHECK (boardType IN ('notice', 'free')),
    regDate TIMESTAMP NOT NULL
);