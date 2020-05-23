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
            System.out.println("login response.sendRedirect(\"index.jsp\");");
            response.sendRedirect("login");
        } else {
            String text = "HelloWorld" + Integer.toString(id, 10);
            System.out.println(text);
            byte[] encrypted = text.getBytes();
            /*String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = null;

            // encrypt the text
            try {
                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                encrypted = cipher.doFinal(text.getBytes());
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
                e.printStackTrace();
            }*/
            System.out.println(new String(encrypted));
            response.addCookie(new Cookie("SESSION", new String(encrypted)));
            response.sendRedirect("userPage");

            //getServletContext().getRequestDispatcher("/userPage").forward(request, response);
            //System.err.println(new String(encrypted));

        }


    }


}


