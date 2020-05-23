package servlets;

import Database.DBConnection;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class simpleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request);
        System.out.println(response);
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("SESSION") && cookie.getValue() != null) {
                response.sendRedirect("userPage");
                return;
            }
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        if (username.equals(password) && username.equals("Admin")) {

            response.addCookie(new Cookie("SESSION", new String("AdminAdmin")));
            response.sendRedirect("adminPage");
            return;
        }
        int id = DBConnection.checkUser(username, password);
        if (id == -1) {
            response.addCookie(new Cookie("success", "no"));
            response.sendRedirect("login");
        } else {
            String text = "HelloWorld" + Integer.toString(id, 10);
            byte[] encrypted = text.getBytes();
            response.addCookie(new Cookie("SESSION", new String(encrypted)));
            response.sendRedirect("userPage");
        }


    }


}


