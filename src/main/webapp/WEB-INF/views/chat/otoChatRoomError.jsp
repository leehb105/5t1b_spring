<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 대화방</title>
<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn,
	em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
	b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline
}

article, aside, details, figcaption, figure, footer, header, hgroup,
	menu, nav, section {
	display: block
}

body {
	line-height: 1
}

ol, ul {
	list-style: none
}

blockquote, q {
	quotes: none
}

blockquote:before, blockquote:after, q:before, q:after {
	content: '';
	content: none
}

table {
	border-collapse: collapse;
	border-spacing: 0
}
</style>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>

<style>
.chat_wrap {
	border: none; solid #999;
	font-size: 13px;
	color: #333
}

.chat_wrap .chatwith {
	display: flex;
	padding-left: 10px;
	background-color: #0394fc;
	height: 40px;
	color: white;
	align-items: center;
	font-weight: bolder;
}

.chat_wrap img {
	border-radius: 50px;
	padding: 5px
}

.chat_wrap .inner {
	padding: 12px;
	background-color: white;
	overflow-y: scroll;
	height: 380px;
}

::-webkit-scrollbar {
	display: none;
}

.chat_wrap .item {
	margin-top: 15px
}

.chat_wrap .item:first-child {
	margin-top: 0px
}

.chat_wrap .item .box {
	display: inline-block;
	max-width: 180px;
	position: relative
}

.chat_wrap .item .box::before {
	content: "";
	position: absolute;
	left: -8px;
	top: 9px;
	border-top: 0px solid transparent;
	border-bottom: 8px solid transparent;
	border-right: 8px solid #0394fc;
}

.chat_wrap .item .box .msg {
	background: #0394fc;
	color: white;
	border-radius: 5px;
	padding: 8px;
	text-align: left
}

.chat_wrap .item .box .time {
	font-size: 11px;
	color: #999;
	position: absolute;
	right: -75px;
	bottom: 5px;
	width: 70px
}

.chat_wrap .item.mymsg {
	text-align: right;
}

.chat_wrap .item.mymsg .box::before {
	left: auto;
	right: -8px;
	border-left: 8px solid #d1d7e0;
	border-right: 0;
}

.chat_wrap .item.mymsg .box .msg {
	background: #d1d7e0;
	color: black;
}

.chat_wrap .item.mymsg .box .time {
	right: auto;
	left: -75px
}

.chat_wrap .item .box {
	transition: all .3s ease-out;
	margin: 0 0 0 20px;
	opacity: 0
}

.chat_wrap .item.mymsg .box {
	transition: all .3s ease-out;
	margin: 0 20px 0 0;
}

.chat_wrap .item.on .box {
	margin: 0;
	opacity: 1;
}

input[type="text"] {
	margin-top: 20px;
	margin-left: 10px;
	border: 0;
	width: 270px;
	background: #ddd;
	border-radius: 5px;
	height: 30px;
	padding-left: 10px;
	box-sizing: border-box;
}

input[type="text"]::placeholder {
	color: #999;
	display: inline;
}

button {
	border: none;
	display: inline;
	background-color: white;
}

button i {
	color: #0394fc;
	width: 30px;
	height: 30px;
}
</style>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://kit.fontawesome.com/01809a491f.js"
	crossorigin="anonymous"></script>
<script>
 
</script>

</head>
<body>
	오류! 채팅을 수행할 수 없습니다.
	<br />
	<%=request.getAttribute("errorMsg") %>

</body>
</html>