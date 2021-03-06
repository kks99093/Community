CREATE TABLE t_user(
	i_user INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	user_id VARCHAR(15) UNIQUE NOT NULL,
	user_pw VARCHAR(100) NOT NULL,
	user_nm VARCHAR(10) NOT NULL,
	user_email VARCHAR(40) NOT NULL,
	nick_nm VARCHAR(10) UNIQUE NOT NULL,
	salt VARCHAR(50) NOT NULL,
	profile_img VARCHAR(100),
	r_dt DATETIME DEFAULT NOW()
);
CREATE TABLE free_board(
	i_board INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(30) NOT NULL,
	content VARCHAR(2000) NOT NULL,
	i_user INT UNSIGNED NOT NULL,
	r_dt DATETIME DEFAULT NOW(),
	i_category INT UNSIGNED NOT NULL,
	cnt INT UNSIGNED DEFAULT 0 ,
	FOREIGN KEY (i_category) REFERENCES t_category(i_category),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user)
);

CREATE TABLE t_category(
	i_category INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	category VARCHAR(10) NOT NULL
	
);
CREATE TABLE board_cmt(
	i_cmt INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	i_board INT UNSIGNED NOT NULL,
	c_content VARCHAR(200) NOT NULL,
	i_user INT UNSIGNED NOT NULL,
	r_dt DATETIME DEFAULT NOW(),
	FOREIGN KEY (i_board) REFERENCES free_board(i_board),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user)
);
CREATE TABLE board_like(
	i_user INT UNSIGNED,
	i_board INT UNSIGNED,
	PRIMARY KEY(i_user, i_board),
	FOREIGN KEY (i_board) REFERENCES free_board(i_board),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user)
);

CREATE TABLE cmtByCmt(
	i_cmtByCmt INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	i_user INT UNSIGNED NOT NULL,
	i_cmt INT UNSIGNED,
	i_board INT UNSIGNED NOT NULL,
	c_content VARCHAR(200) NOT NULL,
	r_dt DATETIME DEFAULT NOW(),
	FOREIGN KEY (i_board) REFERENCES free_board(i_board),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user),
	FOREIGN KEY (i_cmt) REFERENCES board_cmt(i_cmt)
);

SELECT * FROM free_board
WHERE title LIKE '%a%' OR content LIKE '%c%';

SELECT i_board,title,content,A.r_dt,i_category,B.nick_nm,A.i_user FROM free_board A
INNER JOIN t_user B
ON A.i_user = B.i_user
WHERE i_board = 8
ORDER BY r_dt DESC;

SELECT i_board, COUNT(i_board) FROM board_cmt
GROUP BY i_board;

SELECT A.i_board,title,content,A.r_dt,i_category,B.nick_nm,A.i_user,C.cmtCnt FROM free_board A
INNER JOIN t_user B
ON A.i_user = B.i_user
INNER JOIN 
(
SELECT i_board, COUNT(i_board) AS cmtCnt FROM board_cmt
GROUP BY i_board
) C
ON A.i_board = C.i_board
WHERE title LIKE '%a%' OR content LIKE '%c%'
ORDER BY r_dt desc
LIMIT 0,10;

SELECT count(i_board) AS cmtCnt FROM board_cmt
WHERE i_board = 1;

SELECT COUNT(i_board) as totalBoard FROM free_board;

DELETE FROM free_board
WHERE i_board = 1;

DELETE FROM board_cmt
WHERE i_board = 1;

DELETE FROM board_like
WHERE i_board = 1;

UPDATE free_board
SET cnt = cnt+1
WHERE i_board = 108;

SELECT A.i_board,A.title FROM free_board A
LEFT JOIN(
SELECT i_board, COUNT(i_board) AS cnt FROM board_like
GROUP BY i_board
) B
ON A.i_board = B.i_board
WHERE A.r_dt > DATE_ADD(now(),INTERVAL -7 DAY)
ORDER BY B.cnt DESC
LIMIT 0,5;

SELECT A.i_board,A.title FROM free_board A
LEFT JOIN(
SELECT i_board, COUNT(i_board) AS cnt FROM board_like
GROUP BY i_board
) B
ON A.i_board = B.i_board
WHERE A.r_dt > DATE_ADD(now(),INTERVAL -1 MONTH)
ORDER BY B.cnt DESC
LIMIT 0,5;

SELECT A.i_board,title,content,A.r_dt,A.i_category,B.nick_nm,A.i_user,C.cmtCnt,A.cnt,D.category FROM free_board A
		INNER JOIN t_user B
		ON A.i_user = B.i_user
		LEFT JOIN 
			(
			SELECT i_board, COUNT(i_board) AS cmtCnt FROM board_cmt
			GROUP BY i_board
			) C
		ON A.i_board = C.i_board
		LEFT JOIN t_category D
		ON A.i_category = D.i_category
		WHERE A.i_category = 1
ORDER BY r_dt desc
LIMIT 0,5;

SELECT A.i_board,title,content,A.r_dt,i_category,B.nick_nm,A.i_user,C.i_cmt,C.cmtCnt,A.cnt, IFNULL(D.like_cnt,0) AS like_cnt FROM free_board A
		INNER JOIN t_user B
		ON A.i_user = B.i_user
		LEFT JOIN 
			(
			SELECT i_board, COUNT(i_board) AS cmtCnt,i_cmt FROM board_cmt
			GROUP BY i_board
			) C
		ON A.i_board = C.i_board
		LEFT JOIN 
			(
				SELECT i_board, COUNT(i_board) AS like_cnt FROM board_like
				WHERE i_board = 1
			) D
		ON A.i_board = D.i_board
		WHERE A.i_board = 1;
		
		SELECT i_board, count(i_board) as like_cnt FROM board_like
		WHERE i_board = 1;
		
		
		SELECT A.i_cmtByCmt, A.i_cmt, A.c_content, B.nick_nm, A.r_dt,A.i_user FROM cmtbycmt A
		INNER JOIN t_user B
		ON A.i_user = B.i_user 
		WHERE i_board = 1;