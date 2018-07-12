import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;

public class Header extends HttpServlet {     
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8"); 
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<html>");
        out.println("<body bgcolor=\"gray\">");
        out.println("<head>");
        out.println("<font size=\"7\">Wiki Prime Video</font>");
        out.println("</head>");
        out.println("Ver1.0.0");
        out.println("</body>");
        out.println("</html>");
    }
} 