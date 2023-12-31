<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">


    <script type="text/javascript">

        function check(){
            var flag = 0;

            if(document.form1.name.value == ""){
                flag = 1;
            }
            else if(document.form1.comment.value == ""){
                flag = 1;
            }

            if(flag){
                window.alert('Please enter your name and comment');
                return false; //Stop sending
            }
            else{
                return true; //Perform send
            }
        }
    </script>

    <title>Bulletin board</title>
</head>
<body>
<form action="/board/BoardServlet" method="post" name="form1" onSubmit="return check()">
    <p>name:<input type="text" name="name"></p>
    <p>comment:<br>
        <textarea name="comment" rows="5" cols="40"></textarea>
    </p>
    <p><input type="submit" value="Send"><input type="reset" value="reset">
    </p>
</form>

<c:forEach var="list" items="${listAttribute}">
    <p>ID:<c:out value="${list.id}"/>name:<c:out value="${list.name}"/>date:<c:out value="${list.time}"/><br>
        <c:out value="${list.comment}"/></p>
</c:forEach>

</body>
</html>