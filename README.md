# 5t1b_spring
> 스프링으로 만든 커뮤니티 사이트   
> 자유/익명게시판을 이용할 수 있고 사용자간의 쪽지전송이 가능합니다.    
> http://www.5t1b.site/   
>  ~_사이트 이름에는 큰 의미가 없습니다._~
   
## 사용한 기술 스택
- JAVA 8
- Spring
- Spring Security
- Oracle Cloud
- Mybatis
- Maven
- JSP
   
## 주요 기능   
> 회원   
- 로그인: 스프링 시큐리티를 통한 로그인 구현        
- 마이페이지: 회원정보 변경 가능, 프로필 사진 업로드, 비밀번호 변경 구현     

> 게시판    
- 자유/익명/공지게시판 CRUD 구현      
- 게시글 작성시 말머리, 제목, 내용, 파일첨부 가능      
   - 파일 업로드: MultipartFile를 통해 구현   

## ERD
<details>
<summary>이미지 펼치기</summary>
<div markdown="1">
<img src="https://github.com/leehb105/5t1b_spring/blob/master/img/erd.png?raw=true">
</div>
</details>

