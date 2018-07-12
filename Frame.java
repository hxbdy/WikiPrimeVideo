import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;

public class Frame extends HttpServlet {     
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8"); 
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<html><head>");
        out.println("<title>WikiPrimeVideo</title></head>");
        out.println("<frameset rows=\"130,*\">");
        out.println("<frame name=\"Header\" src=\"./Header\">");
        out.println("<frameset cols=\"300,*\">");
        out.println("<frame name=\"Search\" src=\"./Search\">");
        out.println("<frame name=\"Contents\" src=\"./Contents\">");
        out.println("</frameset></frameset></html>"); 
    }
} 