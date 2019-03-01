<%--
  Created by IntelliJ IDEA.
  User: sjj
  Date: 2015/10/24 0024
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>test 添加url</title>

  <!-- 新 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="/css/bootstrap.min.css">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<div class="container">
  <h1>SpringMVC url</h1>
  <hr/>

  <h3>urlList <a href="/addUrl" type="button" class="btn btn-default btn-sm">添加</a></h3>

  <!-- 如果用户列表为空 -->
  <c:if test="${empty urlList}">
    <p class="bg-warning">
      <br/>
      User表为空，请<a href="/addUrl" type="button" class="btn btn-default btn-sm">添加</a>
      <br/>
      <br/>
    </p>
  </c:if>

  <!-- 如果用户列表非空 -->
  <c:if test="${!empty urlList}">
    <table class="table table-bordered table-striped">
      <tr>
        <th>ID</th>
        <th>longUrl</th>
        <th>shortUrl</th>
        <th>num</th>
        <th>操作</th>
      </tr>

      <c:forEach items="${urlList}" var="url">
        <tr>
          <td>${url.id}</td>
          <td>${url.longUrl}</td>
          <td>${url.shortUrl}</td>
          <td>${url.num}</td>
          <td>
            <a href="/showUser/${url.id}" type="button" class="btn btn-sm btn-success">详情</a>
            <a href="/updateUser/${url.id}" type="button" class="btn btn-sm btn-warning">修改</a>
            <a href="/deleteUser/${url.id}" type="button" class="btn btn-sm btn-danger">删除</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</div>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
