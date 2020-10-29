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
DROP TABLE t_user;

CREATE TABLE free_board(
	i_board INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(30) NOT NULL,
	content VARCHAR(2000) NOT NULL,
	i_user INT UNSIGNED,
	r_dt DATETIME DEFAULT NOW(),
	i_category INT UNSIGNED,
	cnt INT UNSIGNED,
	FOREIGN KEY (i_category) REFERENCES t_category(i_category),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user)
);

DROP TABLE free_board;

SELECT i_board,title,content,A.r_dt,i_category,B.nick_nm FROM free_board A
INNER JOIN t_user B
ON A.i_user = B.i_user
WHERE i_board = 1
ORDER BY r_dt desc
;

CREATE TABLE t_category(
	i_category INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	category VARCHAR(10) NOT NULL
	
);
DROP TABLE t_category;

SELECT i_category, category FROM t_category;

CREATE TABLE board_cmt(
	i_cmt INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	i_board INT UNSIGNED NOT NULL,
	c_content VARCHAR(200) NOT NULL,
	i_user INT UNSIGNED,
	cnt INT UNSIGNED,
	r_dt DATETIME DEFAULT NOW(),
	FOREIGN KEY (i_board) REFERENCES free_board(i_board),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user)
);

DROP TABLE board_cmt;

UPDATE t_user
SET profile_img = 'ddd'
WHERE i_user = 1;

SELECT i_board,title,content,nick_nm,r_dt,i_category 
FROM free_board 
ORDER BY r_dt desc ;


SELECT i_cmt, c_content, B.nick_nm, A.r_dt FROM board_cmt A
INNER JOIN t_user B
ON A.i_user = B.i_user
WHERE i_board = 1;


CREATE TABLE user_like(
	i_user INT UNSIGNED,
	i_board INT UNSIGNED,
	PRIMARY KEY(i_user, i_board),
	FOREIGN KEY (i_board) REFERENCES free_board(i_board),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user)
);

DROP TABLE user_like;
INSERT INTO user_like
(i_user, i_board)
VALUES 
(1,1);

SELECT i_board FROM user_like A
INNER JOIN t_user B
ON A.i_user = B.i_user;

