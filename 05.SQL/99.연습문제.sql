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
    
--(3) 박지성이 구매하지 않은 도서의 이름
select b.bookname from book b where b.bookname not in (select b.bookname from orders o
    join book b on o.bookid = b.bookid
    join customer c on o.custid = c.custid
    where c.name like '박지성');

--2.
--(1) 주문하지 않은 고객의 이름(부속질의 사용)
select custid from orders;
select name from customer where custid not in (select custid from orders);
--(2) 주문 금액의 총액과 주문의 평균 금액
select sum(saleprice), avg(saleprice) from orders; 
--(3) 고객의 이름과 고객별 구매액
select c.name, sum(o.saleprice) 
    from customer c join orders o on o.custid = c.custid group by c.name;
--(4) 고객의 이름과 고객이 구매한 도서 목록
select c.name, b.bookname from customer c, orders o, book b
    where c.custid = o.custid and b.bookid = o.bookid
    order by c.name;
--(5) 도서의 가격(Book 테이블)과 판매가격(Orders 테이블)의 차이가 가장 많은 주문
select orderid from book b, orders o 
    where o.bookid = b.bookid and(b.price - o.saleprice) 
    = (select max(b.price - o.saleprice) from orders o, book b where o.bookid = b.bookid);
    
--(6) 도서의 판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름
select c.name-- 고객개인별 평균 구매금액
    from customer c join orders o on o.custid = c.custid 
    group by c.name having avg(o.saleprice) > (select avg(o.saleprice) from orders o);
    
/*select c.name from customer c
        where c.custid like --> custid를 하나의 테이블 개념으로 만들어다시 비교검색
            (select o.custid from orders o
            group by o.custid having avg(o.saleprice) > (select avg(o.saleprice) from orders o)); */
