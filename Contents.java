import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contents extends HttpServlet {     

    public ArrayList<String> title = new ArrayList<String>(); //タイトル
    public ArrayList<String> srt = new ArrayList<String>(); //略称
    public ArrayList<Integer> ep = new ArrayList<Integer>(); //総話数
    public ArrayList<Integer> genre = new ArrayList<Integer>(); //ジャンル
    public ArrayList<Integer> recom = new ArrayList<Integer>(); //おすすめ度
    public ArrayList<Integer> start = new ArrayList<Integer>(); //放送年
    public ArrayList<String> season = new ArrayList<String>(); //シーズン
    public ArrayList<String> path = new ArrayList<String>(); //ファイルパス

    public ArrayList<String> and_title = new ArrayList<String>(); //タイトル
    public ArrayList<Integer> and_ep = new ArrayList<Integer>(); //総話数
    public ArrayList<Integer> and_genre = new ArrayList<Integer>(); //ジャンル
    public ArrayList<Integer> and_recom = new ArrayList<Integer>(); //おすすめ度
    public ArrayList<Integer> and_start = new ArrayList<Integer>(); //放送年
    public ArrayList<String> and_season = new ArrayList<String>(); //シーズン
    public ArrayList<String> and_path = new ArrayList<String>(); //ファイルパス

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        //条件を引っ張ってくる 
        String search=request.getParameter("search");
        String title=request.getParameter("title");
        String genre[] = request.getParameterValues("genre");
        String season[] = request.getParameterValues("season");
        int search_num=0;
        clear();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<html>");
        out.println("<body bgcolor=\"gray\">");
        out.println("<head>");

        /*
        if(search!="" && search!=null){
            out.println("キーワード:"+search);
            out.println("<br>");
        }
        */
        /*
        if(title!="" && title!=null){
            out.println("選択したタイトル:"+title);
            out.println("<br>");
        }
        */
        if(genre!=null){
            //out.println(" ジャンル:");
            for (int i = 0; i < genre.length; i++ ) {
                search_num+=Integer.parseInt(genre[i]);
            }
            //out.println(search_num + "<br>");
        }
        /*
        if(season!=null){
            out.println(" シーズン指定数:"+season.length);
            out.println("<br>");
            for (int i = 0; i < season.length; i++ ) {
                out.println(season[i] + "<br>");
            }
                
        }
        */

        out.println("</head>");

        Cookie[] cookies = request.getCookies();         
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i ++) { 
                String str = cookies[i].getName();
                if (str.equals("path")) {
           	        String value = cookies[i].getValue();
                    value = URLDecoder.decode(value, "UTF-8");
                    out.println("<b>");
                    out.println("前回観たアニメ : ");
                    out.println("</b>");
                    out.println("<a href=\"javascript:void(0)\" onclick=\"document.path.submit();return false;\">"+value.replace("anime/","")+"</a>");
                    out.println("<form action=\"/workspace/servlet/Result\" method=\"GET\" name=\"path\">");
                    out.println("<input type=\"hidden\" name=\"filepath\" value=\""+value+"\">");
                    out.println("</form>");
            	}        
            }
        }

        //csvから条件一致を探す
        CsvAnalyzer();
        AndSearch(search_num,search,season);
        int and_title_size=and_title.size();
        out.println("<b>");
        out.println("検索結果:"+and_title_size+"件");
        out.println("</b>");
        out.println("<br>");
        out.println("<hr>");
        for(int i=0;i<and_title_size;i++){
            out.println("<a href=\"javascript:void(0)\" onclick=\"document.path"+i+".submit();return false;\">"+and_title.get(i)+"</a>");
            out.println("<form action=\"/workspace/servlet/Result\" method=\"GET\" name=\"path"+i+"\">");
            out.println("<input type=\"hidden\" name=\"filepath\" value=\""+and_path.get(i)+"\">");
            out.println("</form>");
            out.println("<hr>");
        }
        out.println("</body>");
        out.println("</html>"); 
    }

    public void CsvAnalyzer(){
        
        try {
            File f = new File("animeDB2.csv");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            // 1行ずつCSVファイルを読み込む
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 0); // 行をカンマ区切りで配列に変換
                title.add(data[0]);
                srt.add(data[1]);
                ep.add(Integer.parseInt(data[2]));
                genre.add(Integer.parseInt(data[3]));
                recom.add(Integer.parseInt(data[4]));
                start.add(Integer.parseInt(data[5]));
                season.add(data[6]);
                path.add(data[7]);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void AndSearch(int search_genre,String search_title,String[] search_season){
        int cnt=0;
        boolean title_flg;
        boolean season_flg;

        for(Integer value:genre){
            //検索ボックス部
            if(search_title=="" || search_title==null){
                title_flg=true;
            }
            else{
                if(title.get(cnt).contains(search_title) || srt.get(cnt).contains(search_title)){
                    title_flg=true;
                }
                else{
                    title_flg=false;
                }
            }

            //シーズン部
            season_flg=true;
            /*
            if(search_season==null){
                season_flg=true;
            }
            else{
                season_flg=false;
                String str_season=season.get(cnt);
                for(int i=0;i<search_season.length;i++){
                    if(search_season[i].equals(str_season)){
                        season_flg=true;
                        break;
                    }
                }
            }
            */

            //全フラグ回収
            if((value & search_genre)==search_genre && title_flg && season_flg){
                and_title.add(title.get(cnt));
                and_ep.add(ep.get(cnt));
                and_genre.add(genre.get(cnt));
                and_recom.add(recom.get(cnt));
                and_start.add(start.get(cnt));
                and_season.add(season.get(cnt));
                and_path.add(path.get(cnt));
            }
            cnt++;
        }
    }

    public void clear(){
        title.clear();
        ep.clear();
        genre.clear();
        recom.clear();
        start.clear();
        season.clear();
        path.clear();
        and_title.clear();
        and_ep.clear();
        and_genre.clear();
        and_recom.clear();
        and_start.clear();
        and_season.clear();
        and_path.clear();
    }
} 