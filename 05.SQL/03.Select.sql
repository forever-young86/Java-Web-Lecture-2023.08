/*
 * 1. 질의어(query)
 */ 
/*
SELECT 필드명 
    FROM 테이블명
    JOIN 테이블명
    ON 조인 조건
    WHERE 조건
    GROUP BY 필드명
    HAVING 그룹 조건
    ORDER BY  필드명 순서(ASC, DESC);
    
갯수 조절을 위한 필드가 추가됨  
 */

/* 기본 구문 */
select * from book;
select * from customer;
select * from orderes;
select name, address from customer;
-- book 테이블에 있는 출판사 이름은? (중복을 제거하고 나열 distinct)
select distinct publisher from book;

/* 1.1 조회 조건 */
-- 가격이 10000원 이상인 책
select * from book where price >=10000; //*는 모든 정보를 불러올때, 필요한 정보만을 불러오고싶을때 여기에 필드명을 넣는다
-- 대한미디어에서 출간한 책
select * from book where publisher = '대한미디어';
-- 축구와 관련된 책 (like '%축구%';)
select * from book where bookname like '%축구%';
-- 가격이 10000원 이상 20000원 이하인 책 (and를 써서 조건 두개를 만든다)
select * from book where price >=10000 and price <=20000;
select * from book where price BETWEEN 10000 and 20000;
-- 가격이 10000원 미만 또는 20000원 초과인 책
select * from book where price < 10000 or price > 20000;
-- 가격이 13000원이 아닌 책
select * from book where price != 13000;
select * from book where price <> 13000;
-- 가격이 7의 배수인 책 (price%7=0)
select * from book where mod(price, 7) = 0;
-- 나무수, 굿스포츠, 삼성당에서 출간한 책
select * from book where publisher = '나무수' or publisher = '굿스포츠' or publisher = '삼성당';
select * from book where publisher in ('나무수', '굿스포츠', '삼성당');
-- 전화번호가 null 인 고객은?
select * from customer where phone is null;
-- 미국과 영국에 사는 고객은?
select * from customer where address like '%미국%' or address like '%영국%'; 

/*
 * 1.2 순서(Order by)
 */
-- 책 이름의 오름차순 (ascending order)으로 정렬
select * from book order by bookname;
-- 책 가격의 내림차순 (descending ordeR) 으로 정렬
select * from book order by price desc;
-- 책 가격의 내림차순 (descending ordeR) 으로 정렬, 같으면 책 이름의 오름차순 (ascending order)으로 정렬 (bookname은 asc 생략가능한다!)
select * from book order by price desc, bookname asc;
-- 1) 대한민국에 사는 고객을 // 2)이름순으로 정렬(포함된 단어로 검색할때는 like '%단어%'로 찾는다) #1번 먼저 구문작성후 #2번 구문 작성
--#select 에도 구문순서가 있다! where 다음 order by, 만약 반대로 넣으면 에러발생!
select * from customer where address like '%대한민국%' order by name;

/*
 * 1.3 함수
 */
-- 고객의 수는?
select count(*) from customer;
select count(*) as numCustomers from customer; --as => alias 카운트한 고객의 수를 numbCustomers라고 명명
-- 주문금액의 합계, 평균, 최대, 최소는? (as는 붙여도 되고 안붙여도 된다, 보기좋게 _를 사용!)
select sum(saleprice) as sum_sales, avg(saleprice) as average_sales, max(saleprice) max_sales, min(saleprice) min_sales from orders;
-- 대한미디어에서 출간한 책 가격의 평균은?
select avg(price)from book where publisher = '대한미디어';

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