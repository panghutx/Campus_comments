package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {
    //新增一条评论
    public void insert (Image image){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into image values(null,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, image.getContent());
            statement.setString(2, image.getImage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //展示评论列表
    public List<Image> selectAll(){
        List<Image> imageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet  = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from image";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Image image = new Image();
                image.setId(resultSet.getInt("id"));
                //字数限制，超过20字只显示20字
                String content = resultSet.getString("content");
                if(content.length()>20){
                    content = content.substring(0,20)+"……";
                }
                image.setContent(content);
                image.setImage(resultSet.getString("image"));
                imageList.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return imageList;
    }

    //展示详情页

    public Image selectOne(int id){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet  = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from image where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            Image image = new Image();
            if(resultSet.next()){
                image.setId(resultSet.getInt("id"));
                //字数限制，超过20字只显示20字
                String content = resultSet.getString("content");
                if(content.length()>20){
                    content = content.substring(0,20)+"……";
                }
                image.setContent(content);
                image.setImage(resultSet.getString("image"));
                return image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    public static void main(String[] args) {
        ImageDao imageDao = new ImageDao();
        List<Image> imageList = imageDao.selectAll();
        for (Image image:imageList){
            System.out.print(image.getImage()+image.getContent());
        }
    }
}
