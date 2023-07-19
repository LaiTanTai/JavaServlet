<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<% String contextPath = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>404 Error - Page Not Found</title>
<style>
    body {
      background-color: #f8f9fa;
    }

    .error-container {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .error-title {
      font-size: 6rem;
      font-weight: bold;
      color: #343a40;
      margin-bottom: 0.5rem;
    }

    .error-subtitle {
      font-size: 2rem;
      color: #6c757d;
      margin-bottom: 2rem;
    }

    .error-actions {
      margin-top: 2rem;
    }

    .error-actions a {
      display: inline-block;
      font-size: 1.5rem;
      padding: 0.75rem 2.5rem;
      border-radius: 0.25rem;
      background-color: #343a40;
      color: #fff;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    .error-actions a:hover {
      background-color: #23272b;
    }
  </style>
</head>
<body>
  <div class="error-container">
    <h1 class="error-title">404</h1>
    <h2 class="error-subtitle">Trang Không Tồn Tại</h2>
    <h4 class="error-subtitle">Có Vẻ Như Không Tồn Tại Dịch Vụ nào Được Cung Cấp</h4>
    <div class="error-actions">
      <a href="<%=contextPath%>/profile" class="btn btn-primary">Trở Về Trang Chủ</a>
    </div>
  </div>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>