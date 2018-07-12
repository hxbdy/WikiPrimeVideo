import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;

public class Search extends HttpServlet {     
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        int value=1;
        String genre[]={
            "アクション",
            "恋愛",
            "学園",
            "お仕事",
            "アイドル",
            "視聴注意",
            "感動",
            "ギャグ",
            "戦闘",
            "銃火器",
            "獣耳",
            "グルメ",
            "魔法少女",
            "異世界",
            "ロボット",
            "SF",
            "分類不能",
            "日常"
        };
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<html>");
        out.println("<body bgcolor=\"gray\">");
        out.println("<head>");
        out.println("<b>");
        out.println("検索");
        out.println("</b>");
        out.println("</head>");
        out.println("<hr>");
        out.println("<center>");
        out.println("<form action=\"/workspace/servlet/Contents\" method=\"GET\" target=\"Contents\">");
        out.println("<input type=\"search\" name=\"search\" placeholder=\"キーワードを入力\">");
        out.println("<input type=\"submit\" name=\"submit\" value=\"検索\">");
        out.println("</center>");
        out.println("<hr>");
        out.println("<head>");
        out.println("<b>");
        out.println("ジャンル");
        out.println("</b>");
        out.println("</head>");
        out.println("<hr>");
        for(int i=0;i<genre.length;i++){
            out.println("<label>");
            out.println("<input type=\"checkbox\" name=\"genre\" value="+ value +">"+genre[i]);
            out.println("</label>");
            out.println("<br>");
            value*=2;
        }
        out.println("<input type=\"submit\" name=\"submit\" value=\"検索\">");
        out.println("<br>");
        out.println("<hr>");
        /*
        シーズン検索機能
        out.println("<head>");
        out.println("シーズン");
        out.println("</head>");
        out.println("<hr>");
        out.println("<input type=\"checkbox\" name=\"season\" value=\"spring\">春");
        out.println("<br>");
        out.println("<input type=\"checkbox\" name=\"season\" value=\"summer\">夏");
        out.println("<br>");
        out.println("<input type=\"checkbox\" name=\"season\" value=\"fall\">秋");
        out.println("<br>");
        out.println("<input type=\"checkbox\" name=\"season\" value=\"winter\">冬");
        out.println("<br>");
        out.println("<input type=\"submit\" name=\"submit\" value=\"検索\">");
        */
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
} 