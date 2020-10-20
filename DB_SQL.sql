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
	nick_nm VARCHAR(10) NOT NULL,
	r_dt DATETIME DEFAULT NOW(),
	i_category INT UNSIGNED,
	FOREIGN KEY (i_category) REFERENCES t_category(i_category),
	FOREIGN KEY (nick_nm) REFERENCES t_user(nick_nm)
);

DROP TABLE free_board;
SELECT i_board,title,content,nick_nm,r_dt,ㅑ_category FROM free_board;

CREATE TABLE t_category(
	i_category INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	category VARCHAR(10) NOT NULL
	
);
DROP TABLE t_category;

SELECT i_category, category FROM t_category;

CREATE TABLE board_cmt(
	i_cmt INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	i_board INT UNSIGNED,
	c_content VARCHAR(200) NOT NULL,
	nick_nm VARCHAR(10) NOT NULL,
	r_dt DATETIME DEFAULT NOW(),
	FOREIGN KEY (i_board) REFERENCES free_board(i_board),
	FOREIGN KEY (nick_nm) REFERENCES t_user(nick_nm)
);


SELECT i_cmt, c_content, nick_nm, r_dt FROM board_cmt 
WHERE i_board = 1;