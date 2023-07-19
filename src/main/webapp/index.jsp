<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f8f9fa;
    }

    .home-container {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .home-title {
      font-size: 4rem;
      font-weight: bold;
      color: #343a40;
      margin-bottom: 2rem;
    }

    .login-button {
      font-size: 1.5rem;
      padding: 0.75rem 2.5rem;
      border-radius: 0.25rem;
      background-color: #343a40;
      color: #fff;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    .login-button:hover {
      background-color: #23272b;
    }
  </style>
</head>
<body>
  <div class="home-container">
    <h1 class="home-title">Welcome to My Website</h1>
    <a href="/CRM_project/login" class="btn btn-primary login-button">Login</a>
  </div>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>