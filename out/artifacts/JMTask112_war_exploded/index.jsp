<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 09.06.2020
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Test</title>
  </head>
  <body>
  <h1> Это тестовая страница WebTask112</h1>
  <br>
  <i> Вы може те просматривать данные о всех User хранящихся в базе данных MySQL.<br>
      Так же вам доступно добавление, удаление из базы данных User.<br>
      Вы так же можете изменять данные User, заполнив соответствующие поля форм.<br><br></i>

      <%
          List<User> list = (List) request.getAttribute("list");
          if (list != null && list.size() != 0 ) {
      %>
  <table border="1" >
      <tr bgcolor="#2f4f4f">
          <th width="50" height="25" align="centre">id</th> <th width="100" height="25" align="centre">name</th>
          <th width="170" height="25" align="centre">lastName</th><th width="70" height="25" align="centre">age</th>
      </tr>
      <%
          for (User user: list
               ) {
              %>
      <tr>
          <td> <%= user.getId()%></td>
          <td> <%= user.getName()%></td>
          <td> <%= user.getLastName()%></td>
          <td> <%= user.getAge()%></td>
      </tr>
      <% }
       %>

  </table><br>
      <%
          } else {
      %>
  Список пока что пуст!
  <%
      }
  %>
  <form action="" method="POST" >
      <b>Данные для добавления пользователя:</b><br>
    <input type="text" name="name" value="name"><br>
    <input type="text" name="lastName" value="last name"><br>
    <input type="number" name="age" value="age"><br>
    <input type="submit" value="Send">
  </form><br>

  <form action="/JM/delete" method="POST">
      <b>Введите id пользователя, которого необходимо удалить из базы данных:</b><br>
      <input text="number" name="id"><br>
      <input type="submit" value="Send id">
  </form><br>

  <form action="/JM/update" method="POST">
      <b>Введите id и новые данные пользователя, которого вы хотите изменить:</b><br>
      <input type="number" name="id"><br>
      <input type="text" name="name" value="name"><br>
      <input type="text" name="lastName" value="last name"><br>
      <input type="number" name="age" value="age"><br>
      <input type="submit" value="Send">
  </form>

  </body>
</html>
