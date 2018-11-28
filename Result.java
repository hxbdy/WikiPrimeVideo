import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;

public class Result extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        String filepath=request.getParameter("filepath");
        String title,sub;
        title=filepath.replace("../anime/","");
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<html>");
        out.println("<body bgcolor=\"gray\">");
        out.println("<head>");
        out.println("<b>");
        out.println(title);
        out.println("</b>");
        out.println("<br>");
        out.println("<a href=\"Contents\">戻る</a>");
        out.println("<style type=\"text/css\">");
        out.println("p.resizevideo video {width: 90%; height: 90%;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<hr>");
        String ck=filepath;
        Cookie cookie=new Cookie("path",URLEncoder.encode(ck,"UTF-8"));
        cookie.setMaxAge(7 * 24 * 60 * 60); //a week
        response.addCookie(cookie);
        File dir=new File("../" + filepath);
        File[] list=dir.listFiles();
        //out.println("指定ディレクトリにあったファイル数:"+list.length);
        for(int i=0;i<list.length;i++){
            String dst=list[i].toString();
            dst=dst.replace("/mnt/hdd/HD-V3/アニメ/workspace/", "../");
            sub=dst.substring(dst.lastIndexOf("/")+1);
            sub=sub.replace(".mp4", "");
            sub=sub.replace(".MP4", "");
            if(dst.indexOf(".mp4")!=-1  || dst.indexOf(".MP4")!=-1){
                out.println("<div style=\"float:left;\">");
                out.println("<a href=\"View?filepath="+URLEncoder.encode(dst,"UTF-8")+"\" target=\"_blank\">"+sub+"</a>");
                out.println("</div>");
                out.println("<div style=\"float:right;\">");
                out.println("<a href=\""+dst+"\" download>ダウンロード</a>");
                out.println("</div>");
                out.println("<br><hr>");
            }
        }
        out.println("</body>");
        out.println("</html>"); 
    }
} 