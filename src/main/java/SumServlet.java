import dao.DiscussDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sum")
public class SumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json/application;charset=utf8");
        DiscussDao discussDao = new DiscussDao();
        int sum = discussDao.sum();
        System.out.println(sum);
        resp.getWriter().write("{\"sum\":"+sum+"}");
    }
}
