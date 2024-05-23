<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<h1>회원가입 페이지</h1>
<form id="registerForm" action="<c:url value='/saveUser'/>" method="post">
    <input type="text" id="username" name="username">
    <input type="text" id="password" name="password">
    <input type="email" id="email" name="email">
    <span id="selectCountryField"></span>
    <span id="selectAreaField"></span>
    <input type="submit" value="확인">
</form>
<p>
    <a href="<c:url value='/loginPage'/>">로그인 화면</a>
    <a href="<c:url value='/findIdPage'/>">아이디 찾기 화면</a>
    <a href="<c:url value='/findPasswordPage'/>">비밀번호 변경 화면</a>
</p>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>