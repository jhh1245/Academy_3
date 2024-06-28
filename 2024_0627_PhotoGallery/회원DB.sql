/*
-- 회원테이블
create table member_test
(
	mem_idx		int, 					-- 회원번호 
	mem_name 	varchar2(100) not null, -- 회원명
	mem_id		varchar2(100) not null, -- 회원 ID
	mem_pwd		varchar2(100) not null, -- 회원 비번 
	mem_zipcode char(5) 	  not null, -- 우편번호 5글자 고정 
	mem_addr    varchar2(1000) not null,-- 주소 
	mem_ip      varchar2(100) not null, -- 아이피 
	mem_regdate date default sysdate,	-- 가입일자
	mem_grade   varchar2(100) default '일반' -- 회원 등급   
)	 	

-- 
drop table member 
select * from member_test order by mem_idx
-- 시퀀스 추가 
create sequence seq_member_test_idx

-- 기본키 
alter table member_test
	add constraint pk_member_test_idx primary key(mem_idx);
	
-- 회원등급(check)
alter table member_test
	add constraint ch_member_grade check(mem_grade in('일반','관리자'));  

-- sample data
insert into member_test values(seq_member_idx.nextVal, '김관리', 'admin', 'admin', '00000', '서울시 관악구', '127.0.0.1', sysdate, '관리자');

insert into member_test values(seq_member_idx.nextVal, '일길동', 'one','1234', '12345', '서울시 관악구', '127.0.0.1', default, default);


select * from member_test

-- JDBC에서 사용할 insert문 
insert into member values(seq_member_idx.nextVal, ?, ?, ?, ?, ?, ?, default, default);


-- JDBC에서 사용할 update문
update member set mem_name=?, , pwd='1234', ip='192.168.0.107', regdate=sysdate where mem_idx=?; 
 

*/
