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

@WebServlet("/detail")
public class PageServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json/application;charset=utf-8");
        DiscussDao discussDao = new DiscussDao();
        List<Discuss> discussList = discussDao.load();
        String jsonString = objectMapper.writeValueAsString(discussList);
        System.out.println(jsonString);

        resp.getWriter().write(jsonString);
    }
}
