<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<div id="id01" class="modal">
    <hr>
    <form class="modal-content animate" action="../login" method="post">
        <div class="container">
            <label for="username"><b>Username</b></label>
            <input type="text" id="username" name="username" placeholder="Enter Username" required>

            <label for="password"><b>Password</b></label>
            <input type="password" id="password" name="password" placeholder="Enter Password" required>
            <input type="submit" value="Login">
            <label>
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>

        </div>

    </form>
    <div id="invalid">
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies.length == 0) return;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("success")) {
                    if (cookie.getValue().equals("no")) {
                        out.println("invalid login or password");
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        %>
    </div>
    <hr>
</div>

<script>


</script>

</body>
</html>