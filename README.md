# 5t1b_spring
> 스프링으로 만든 커뮤니티 사이트   
> 자유/익명게시판을 이용할 수 있고 사용자간의 쪽지전송이 가능합니다.    
> http://www.5t1b.site/   
>  ~~_사이트 이름에는 큰 의미가 없습니다._~~    
<br>            
## 사용한 기술 스택
- JAVA 8
- Spring
- Spring Security
- Oracle Cloud
- Mybatis
- Maven
- JSP    
<br>   
## 주요 기능   
> 회원   
- 로그인: 스프링 시큐리티를 통한 로그인 구현 
   - BCryptPasswordEncoder를 통한 비밀번호 암호화 작업         
- 마이페이지: 회원정보 변경 가능, 프로필 사진 업로드, 비밀번호 변경 가능      

> 게시판    
- 자유/익명/공지게시판 CRUD 구현      
- 게시글 작성시 파일첨부 가능: MultipartFile 인터페이스를 통한 파일 업로드    
- 게시글 조회수 증가, 게시글 추천: 조회/추천에 관한 쿠키를 생성해 중복 증가 방지
- 댓글과 대댓글 기능 구현     
- 게시글 제목 검색 기능 구현

> 쪽지      
- 회원간의 쪽지 전송기능 구현    
- 보낸쪽지의 경우 받은날짜 확인가능    
- 수신인 검색 자동완성기능 구현(jquery ui autocompletet사용)   

> 관리자페이지
- 사원목록 조회      
- 부서/직급 변경기능 구현     
<br>     
## ERD
<details>
<summary>이미지 펼치기</summary>
<div markdown="1">
<img src="https://github.com/leehb105/5t1b_spring/blob/master/img/erd.png?raw=true">
</div>
</details>

<br>     

## 개발시 고려한 점      
> XSS 방지 필터 적용   
- 네이버의 [lucy-xss-filter](https://github.com/naver/lucy-xss-servlet-filter) 사용
- 문제점    
  - 게시판 기능의 댓글작성은 필터를 거쳐 DB에 저장되는것을 확인했지만 본문의 내용은 필터를 거치지 않았다.
- 해결 
  - 본문은 파일 업로드를 구현하기 위해 폼데이터를 multipart형식으로 전송하고 있는데 해당 폼의 request는 xss필터를 거치지 않고있었다.
  - XSS필터 전에 MultipartFilter를 적용해 multipart형식도 필터링되게 해주었음
  - 톰캣 context.xml 파일에 allowCasualMultipartParsing 옵션을 추가해주었다. 공식문서에 가보니 multipart/form-data 요청 본문을 자동으로 구문 분석해야 하는 경우 설정해 준다고 명시되어 있었다.
- <details>
    <summary>설정</summary>
    <div markdown="1">
       <a href="https://github.com/leehb105/5t1b_spring/blob/master/src/main/webapp/WEB-INF/web.xml">
          필터설정
       </a>
       <br>
       <a href="https://github.com/leehb105/5t1b_spring/blob/master/src/main/resources/lucy-xss-servlet-filter-rule.xml">
          필터링 룰 작성
       </a>
    </div>
 </details>

 > 익명게시판 댓글 익명화
 



