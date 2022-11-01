import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import dao.Image;
import dao.ImageDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/img")
public class ImgServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("用户的当前工作目录: "+System.getProperty("user.dir"));
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        Part part = req.getPart("image");
        String content = req.getParameter("content");
        //输入内容限制
        if(content==null||content.equals("")){
            resp.getWriter().write("输入不能为空哦");
        }
        //得到文件类型
        String contentType = part.getContentType();
        String[] split = contentType.split("/");

        ServletContext context = getServletContext();
        //该路径不能被浏览器读取，需要进行截断
        String path = context.getRealPath("/images/")+UUID.randomUUID().toString()+"."+split[1];
        System.out.println(path);
        int length = path.length();
        int i=path.lastIndexOf("/");
        part.write(path);
        ImageDao imageDao = new ImageDao();
        Image image = new Image();
        image.setContent(content);
        String realPath = "images"+path.substring(i,length);
        System.out.println(realPath);
        image.setImage(realPath);

        imageDao.insert(image);
        resp.sendRedirect("show.html");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        ImageDao imageDao = new ImageDao();
        resp.setContentType("json/application;charset=utf8");

        if(id==null){

            List<Image> imageList = imageDao.selectAll();
            String asString = objectMapper.writeValueAsString(imageList);
            resp.getWriter().write(asString);

        }else{
            Image image = imageDao.selectOne(Integer.parseInt(id));
            if(image!=null){
                String asString = objectMapper.writeValueAsString(image);
                resp.getWriter().write(asString);
            }
        }

    }
}
