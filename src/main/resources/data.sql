INSERT INTO "USER" (id, password, nickname)
SELECT 'admin', 'admin123', 'Admin'
WHERE NOT EXISTS (SELECT 1 FROM "USER" WHERE id = 'admin');

INSERT INTO "USER" (id, password, nickname)
SELECT 'user1', 'user123', 'user1'
WHERE NOT EXISTS (SELECT 1 FROM "USER" WHERE id = 'user1');

INSERT INTO "USER" (id, password, nickname)
SELECT 'user2', 'user123', 'user2'
WHERE NOT EXISTS (SELECT 1 FROM "USER" WHERE id = 'user2');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '공지사항 1', '공지사항 1 내용', 'Admin', 'notice', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '공지사항 1');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 1', '자유게시판 1 내용', 'user1', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 1');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 2', '자유게시판 2 내용', 'user2', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 2');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 3', '자유게시판 3 내용', 'user1', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 3');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 4', '자유게시판 4 내용', 'user2', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 4');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 5', '자유게시판 5 내용', 'user1', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 5');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 6', '자유게시판 6 내용', 'user2', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 6');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 7', '자유게시판 7 내용', 'user1', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 7');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 8', '자유게시판 8 내용', 'user2', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 8');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 9', '자유게시판 9 내용', 'user1', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 9');

INSERT INTO POST (title, content, writer, boardType, regDate)
SELECT '자유게시판 10', '자유게시판 10 내용', 'user2', 'free', '2023-03-02'
WHERE NOT EXISTS (SELECT 1 FROM POST WHERE title = '자유게시판 10');