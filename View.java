import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;

public class View extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        //条件を引っ張ってくる 
        String filepath=URLDecoder.decode(request.getParameter("filepath"),"UTF-8");
        String dst=filepath.substring(filepath.lastIndexOf("/")+1);
        dst=dst.replace(".mp4", "");
        dst=dst.replace(".MP4", "");
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<html>");
        out.println("<body bgcolor=\"gray\">");
        out.println("<head>");
        out.println("<b>");
        out.println(dst);
        out.println("</b>");
        out.println("<style type=\"text/css\">");
        out.println("p.resizevideo video {width: 90%; height: 90%;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<hr>");
        out.println("<div style=\"text-align:center\">");
        out.println("<p class=\"resizevideo\">");
                 
        out.println("<video src=\""+filepath+"\" preload=\"none\" controls></video>");
                
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>"); 
    }
} 