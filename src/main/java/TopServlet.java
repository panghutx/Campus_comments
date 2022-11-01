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

@WebServlet("/top")
public class TopServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json/application;charset=utf8");
        DiscussDao discussDao = new DiscussDao();
        List<Discuss> top = discussDao.top();
        String asString = objectMapper.writeValueAsString(top);
        resp.getWriter().write(asString);
    }
}
