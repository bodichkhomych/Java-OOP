<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>
<%@ page import="entities.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        List<User> users;
        int userNo = 0;
        int cardNo = 0;


        Cookie[] cookies = request.getCookies();
        if (cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    userNo = Integer.parseInt(cookie.getValue());
                }
                if (cookie.getName().equals("cardsId")) {
                    cardNo = Integer.parseInt(cookie.getValue());
                }
            }
        }
        Object object = request.getAttribute("userList");
        if (!(object instanceof List) ||
                ((List) object).size() == 0 ||
                !(((List) object).get(0) instanceof User)) {
            response.sendRedirect("adminPage");
            return;
        }
        users = (List<User>) object;
    %>
</head>
<body>
<div id="logout">
    <button onclick="function myFunction() {
    document.cookie='SESSION= ; expires = Thu, 01 Jan 1970 00:00:00 GMT';
    location.reload();
}myFunction()">
        log out
    </button>
</div>


<form action="../adminPage" method="post">
    <label for="users"></label>
    <select id="users" name="users">
        <% for (int i = 0; i < users.size(); i++) {%>
        <option value=<%=i%>><%=users.get(i).getUsername()%>
        </option>
        <%}%>

    </select><br>

    <label for="cards"></label>
    <select id="cards" name="cards">

    </select><br>

    <input type="submit" id="submit" value="unblock">
</form>

</body>
<script>
    let cards = [];
    let cardsID = [];
    let cardsBlock = [];
    let pays = [];
    let comments = [];
    window.onload = function () {
        <%for(int i = 0; i <users.size() ; i++) {
            List<Card> cards=users.get(i).getCards();%>
        let cards<%=i%> = [];
        let cardsID<%=i%> = [];
        let cardsBlock<%=i%> = [];
        <% for(Card card: cards){ %>
        cards<%=i%>.push("<%=card.toString()%>");
        cardsID<%=i%>.push("<%=card.getId()%>");
        cardsBlock<%=i%>.push(<%=card.isBlocked()%>);
        <%}%>
        cards.push(cards<%=i%>);
        cardsID.push(cardsID<%=i%>);
        cardsBlock.push(cardsBlock<%=i%>);
        <%}%>
        document.getElementById("users").value =<%=userNo%>;

        change();

        document.getElementById("cards").value =<%= cardNo%>;

    };

    document.getElementById("users").onchange = function () {
        change();
    };

    function change() {
        let id = document.getElementById("users").value;
        console.log(id);
        let str = " ";
        console.log(" cards[id].size" + cards[id].length);
        for (let i = 0; i < cards[id].length; i++) {
            str = str.concat("<option value=", cardsID[id][i], ">", cards[id][i], "</option>\n");
            console.log(str);
        }

        console.log(str);
        document.getElementById("cards").innerHTML = str;
        document.getElementById("cards").value = 0;
        updateCard();
        document.cookie = 'userId=' + id;
    }

    function updateCard() {

        let userId = document.getElementById("users").value;
        let cardId = document.getElementById("cards").selectedIndex;
        console.log("userId" + userId);
        console.log("cardId" + cardId);

        document.cookie = 'cardsId=' + cardId;

        document.getElementById("submit").disabled = !(cardsBlock[userId][cardId]);
    }

    document.getElementById("cards").onchange = function () {
        updateCard();

    };

</script>

</html>
