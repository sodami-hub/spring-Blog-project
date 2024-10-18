-- h2 database 초기 입력값 설정
insert into member(name) values ('lee'),('hong'),('최');

insert into article(title,content,created_at,updated_at) values ('title1','content1',now(),now());
insert into article(title,content,created_at,updated_at) values ('title2','content2',now(),now());
insert into article(title,content,created_at,updated_at) values ('title3','content3',now(),now());
insert into article(title,content,created_at,updated_at) values ('title4','content4',now(),now());


insert into book(id,name,author) values ('1', '한강','한강에 살자');
insert into book(id,name,author) values ('2', '상적천','좋아');
insert into book(id,name,author) values ('3', '탄천','여기도 좋아');
insert into book(id,name,author) values ('4', '소양강','처녀');