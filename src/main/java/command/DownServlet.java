package command;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Discuss;
import dao.DiscussDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/down")
public class DownServlet extends HttpServlet {
    static class RequestJson{
        public int commentId;
        public int score;
        public int recommend;

    }
    static class ResponseJson{
        public int recommend;
    }
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json/application;charset=utf8");
        RequestJson requestJson = objectMapper.readValue(req.getInputStream(), RequestJson.class);
        int commentId = requestJson.commentId;
        int recommend = requestJson.recommend;
        int score = requestJson.score;
        System.out.println("recommend"+recommend+",commentId"+commentId);
        DiscussDao discussDao = new DiscussDao();
        recommend-=10;
        discussDao.recommend(commentId,recommend);
        ResponseJson responseJson = new ResponseJson();
        responseJson.recommend=recommend;
        objectMapper.writeValueAsString(responseJson);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json/application;charset=utf8");
        DiscussDao discussDao = new DiscussDao();
        List<Discuss> down = discussDao.down();
        String asString = objectMapper.writeValueAsString(down);
        resp.getWriter().write(asString);
    }
}