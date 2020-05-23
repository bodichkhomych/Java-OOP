<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
</head>
<body>

<div id="id01" class="modal">
    <hr>
    <form class="modal-content animate" action="../registration" method="post">
        <div class="container">
            <%--@declare id="username"--%><%--@declare id="psw"--%> <%--@declare id="firstName"--%><%--@declare id="secondname"--%>

            <label for="firstName"><b>First name</b></label>
            <input type="text" name="firstName" placeholder="Enter first name" required>


            <label for="secondName"><b>Second name</b></label>
            <input type="text" name="secondName" placeholder="Enter second name" required>

            <label for="username"><b>Username</b></label>
            <input type="text" name="username" placeholder="Enter Username" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" name="password" placeholder="Enter Password" required>

        </div>

        <input type="submit" value="Registration">

    </form>
    <hr>
</div>

<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>

</body>
</html>
