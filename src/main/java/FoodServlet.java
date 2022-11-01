import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Discuss;
import dao.DiscussDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet("/food")
public class FoodServlet extends HttpServlet {
    DiscussDao discussDao = new DiscussDao();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<Discuss> discussList = discussDao.headLoad();
            String jsonString = objectMapper.writeValueAsString(discussList);
            System.out.println(jsonString);
            resp.setContentType("json/application;charset=utf-8");
            resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将用户请求发送到服务器
        Discuss discuss = objectMapper.readValue(req.getInputStream(), Discuss.class);
        //设置发送的时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        discuss.setTime(timestamp);
        //将数据一起保存到数据库
        discussDao.save(discuss);
        resp.setContentType("json/application;charset=utf-8");
        resp.getWriter().write("{\"ok\":true}");

    }
}
