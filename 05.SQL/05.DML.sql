/*
 * 2. 데이터 조작 언어 (DML : Data Manipulation Language)
*/

/*
 * 2.1 삽입 (Insert)
 INSERT INTO 테이블명 [(필드명1, 필드명2, ..., 필드명 n)]
    VALUES (값1, 값2, ..., 값n);
*/
--북테이블에 새로운 레코드 추가
insert into book values (11, '스포츠 의학', '한솔의학서적', 90000); --해당에 들어가는 필드값을 다 썼을경우에는 필드명 생략가능
select * from book;
desc book;
--스포츠 심리, 24000원, 출판사 모름
insert into book (bookid, bookname, price) values (12, '스포츠 심리', 24000);
--박세리 고객이 스포츠 의학을 오늘 구매한 사실을 orders 테이블에 기록
select * from orders;
insert into orders values(11, 90000, default, 5, 11);
--박찬호 고객이 스포츠심리를 오늘 구매한 사실을 orders 테이블에 기록
insert into orders (orderid, saleprice, custid, bookid) --default 필드는 필드명과 필드값 생략가능
    values(12,24000,1,12);
--고객 테이블에 신유빈 선수를 등록
insert into customer(custid, name) values(6, '신유빈');
commit;

/*
 * 2.2 갱신 (Update)
 UPDATE 테이블명 SET 필드명1 = 값1, ..., 필드명n=값n where 조건;
  */
--스포츠 심리 책의 출판사를 한솔의학서적으로 변경
update book set publisher = '한솔의학서적' where bookid = 12;
select * from book;
--신유빈 선수의 주소를 강원도 영월, 전화번호를 010-2345-6789 으로 변경
update customer set address = '강원도 영월', phone = '010-2345-6789' where custid = 6;
--전화번호가 null인 고객을 070-2345-6789로 변경
update customer set phone = '070-2345-6789' where phone is null; --조건 where을 안주면 모든 행이 다 바뀐다!
select * from customer;
commit;

/*
 * 2.3 삭제 (Delete)
DELETE FROM 테이블명 WHERE 조건;
*/
--고객 테이블에 테스트 데이터 입력후 삭제
insert into customer (custid, name) values (7, '테스트');
insert all into customer (custid, name) values (8, '류현진') into customer (custid, name) values (9, '손흥민') select *from dual;
select * from customer;
--custid가 7인 고객 삭제
delete from customer where custid=7;
--주소가 null인 고객 삭제
delete from customer where address is null;
commit;
/*연습문제*/
--1.
--(1) 박지성이 구매한 도서의 출판사와 같은 출판사에서 도서를 구매한 고객의 이름
select b.publisher from orders o 
    join book b on o.bookid = b.bookid
    join customer c on o.custid = c.custid
    where c.name like '박지성';
select distinct ec.name from orders eo
    join book eb on eo.bookid=eb.bookid
    join customer ec on eo.custid=ec.custid
    where eb.publisher in (select b.publisher from orders o
        join book b on o.bookid=b.bookid
        join customer c on o.custid=c.custid
        where c.name like '박지성');
--(2) 두 개 이상의 서로 다른 출판사에서 도서를 구매한 고객의 이름
select c.name, count(DISTINCT b.publisher) from orders o 
    join book b on o.bookid = b.bookid
    join customer c on o.custid = c. custid
    group by c.name having count (DISTINCT b.publisher)>=2;
--(3) (생략) 전체 고객의 30% 이상이 구매한 도서
select b.bookname, count(o.bookid) from orders o
    join book b on o.bookid = b.bookid
    group by b.bookname having count (o.bookid) >= 2;
select * from orders;
--2.
--(1) 새로운 도서 ('스포츠 세계', '대한미디어', 10000원)이 마당서점에 입고되었다. 
insert into book values(13, '스포츠 세계', '대한미디어', 10000); --primary key (bookid)가 잡혀있어서 무조건 bookid를 넣어줘야한다
select * from book;
-- 삽입이 안 될 경우 필요한 데이터가 더 있는지 찾아보자.
--(2) ‘삼성당’에서 출판한 도서를 삭제해야 한다.
delete from book where publisher like'삼성당';
--(3) ‘이상미디어’에서 출판한 도서를 삭제해야 한다. 삭제가 안 될 경우 원인을 생각해보자.
select * from orders where bookid in (select bookid from book where publisher like '이상미디어'); --이상미디어에서 출간한 bookid를 검색 orderid에 5,7,10에 걸려있다
delete from book where publisher like '이상미디어'; --foreign key로 잡혀있음, order table에 bookid로 잡혀있음. 그래서 book 테이블에선 지울수 있지만, order테이블에 아직 잡혀있어서 (판매기록에 있음) 지울수가 없음
--(4) 출판사 ‘대한미디어’가 ‘대한출판사’로 이름을 바꾸었다.
update book set publisher = '대한출판사' where publisher = '대한미디어'; 