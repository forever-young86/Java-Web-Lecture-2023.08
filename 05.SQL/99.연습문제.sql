/*
 * 연습문제
 */
 --1.1) 도서번호가 1인 도서의 이름
 select bookname from book where bookid =1;
 --1.2) 가격이 20000원 이상인 도서의 이름
 select bookname,price from book where price >= 20000;
 --1.3) 박지성의 총 구매액 (박지성의 고객번호는 1번으로 놓고 작성)
 select sum(saleprice) from orders where custid =1;
 select sum(o.saleprice) from orders o //join을 이용하여 두 테이블을 중복되는 custid 로 결합하여 사용 
    join customer c 
    on o.custid = c.custid
    where c.name = '박지성';
 --1.4) 박지성이 구매한 도서의 수 (박지성의 고객번호는 1번으로 놓고 작성)
 select count(*) from orders where custid =1; //*로 써도되고, orderid 또는 bookid로 써도 상관없음
 --2.1) 마당서점 도서의 총개수
 select count(*)from book;
 --2.2) 마당서점에 도서를 출고하는 출판사의 총 개수
 select count(distinct publisher)from book; //distinct : 중복출판사를 제거하고 갯수를 셈
 --2.3) 모든 고객의 이름, 주소
 select name, address from customer;
 --2.4) 2014년 7월 4일 ~ 7월 7일 사이에 주문 받은 도서의 주문번호
 select orderid from orders where orderdate between '2014-07-04' and '2014-07-07';
 --2.5) 2014년 7월 4일 ~ 7월 7일 사이에 주문 받은 도서를 제외한 도서의 주문번호
 select orderid from orders where orderdate not between '2014-07-04' and '2014-07-07';
 --2.6) 성이 '김'씨인 고객의 이름과 주소
 select name,address from customer where name like '김%'; //'김%' 으로하면 김은 고정, 뒤는 %
 --2.7) 성이 '김'씨이고 이름이 '아'로 끝나는 고객의 이름과 주소
 select name from customer where name like'%김%아%';

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

/*연습문제*/
--1.
--(1) 박지성이 구매한 도서의 출판사 수
select count(distinct b.publisher) from orders o
    join book b on o.bookid = b.bookid
    join customer c on o.custid = c.custid
    where c.name like '박지성';

--(2) 박지성이 구매한 도서의 이름, 가격, 정가와 판매가격의 차이
select b.bookname, b.price, (b.price - o.saleprice) from orders o 
    join book b on o.bookid = b.bookid
    join customer c on o.custid = c.custid
    where c.name like '박지성';
    
--(3) 박지성이 구매하지 않은 도서의 이름 //차집합 minus 사용하여 전체도서목록 - 박지성이 구매한 도서를 뺀다
select b.bookname from book b where b.bookname not in (select b.bookname from orders o
    join book b on o.bookid = b.bookid
    join customer c on o.custid = c.custid
    where c.name like '박지성');

--2.
--(1) 주문하지 않은 고객의 이름(부속질의 사용)
select custid from orders;
select name from customer where custid not in (select custid from orders);
--(2) 주문 금액의 총액과 주문의 평균 금액
select sum(saleprice), round(avg(saleprice),0) from orders; --round,0 을 써서 소수점을 없앤다
--(3) 고객의 이름과 고객별 구매액
select c.name, sum(o.saleprice) 
    from customer c join orders o on o.custid = c.custid group by c.name;
--(4) 고객의 이름과 고객이 구매한 도서 목록
select c.name, b.bookname from customer c, orders o, book b
    where c.custid = o.custid and b.bookid = o.bookid
    order by c.name;
--고객이 구매한 도서목록이 한번에 나열됨, 문자열은 listagg를 사용함  (선생님 답)
select c.name, listagg(b.bookname,',') within group (order by b.bookname) booklist from orders o
    join customer c on o.custid = c.custid
    join book b on o.bookid= b.bookid
    group by c.name;
--(5) 도서의 가격(Book 테이블)과 판매가격(Orders 테이블)의 차이가 가장 많은 주문
select orderid from book b, orders o 
    where o.bookid = b.bookid and(b.price - o.saleprice) 
    = (select max(b.price - o.saleprice) from orders o, book b where o.bookid = b.bookid);

--ABS 절대값 사용 (선생님 답)
select orderid, o.saleprice, b.price from orders o 
    join book b on o.bookid = b.bookid
    where abs (b.price - o.saleprice) 
    = (select max(abs(b.price - o.saleprice)) from orders o join book b on o.bookid = b.bookid);
    
--(6) 도서의 판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름
select c.name-- 고객개인별 평균 구매금액
    from customer c join orders o on o.custid = c.custid 
    group by c.name 
    having avg(o.saleprice) > (select avg(o.saleprice) from orders o);
    
/*select c.name from customer c (오 교수님 답)
        where c.custid like --> custid를 하나의 테이블 개념으로 만들어 다시 비교검색
            (select o.custid from orders o
            group by o.custid having avg(o.saleprice) > (select avg(o.saleprice) from orders o)); */
