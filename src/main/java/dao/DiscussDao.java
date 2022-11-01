package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscussDao {

    //获取点评总数
    public int sum(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum=0;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(*) from food";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.next();
            sum  = resultSet.getInt(1);

        } catch (SQLException e) {

        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return sum;

    }
    public void save(Discuss discuss){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into food values(null,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,discuss.getScore());
            statement.setString(2, discuss.getTitle());
            statement.setString(3, discuss.getContent());
            statement.setTimestamp(4,discuss.getTime());
            statement.setInt(5,discuss.getRecommend());

            //执行sql
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    public List<Discuss> load(){
        List<Discuss> discussList = new ArrayList<>();
        Connection connection =null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try {
           connection = DBUtil.getConnection();
           String sql = "select * from food order by time desc";
           statement = connection.prepareStatement(sql);
           resultSet = statement.executeQuery();
           while(resultSet.next()){
               Discuss discuss = new Discuss();
               discuss.setCommentId(resultSet.getInt("commentId"));
               discuss.setScore(resultSet.getInt("score"));
               discuss.setContent(resultSet.getString("content"));
               discuss.setTitle(resultSet.getString("title"));
               discuss.setTime(resultSet.getTimestamp("time"));
               discuss.setRecommend(resultSet.getInt("recommend"));
               discussList.add(discuss);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return discussList;

    }


    //根据commentId更改推荐值
    public void recommend(int commentId,int recommend){

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update food set recommend = ? where commentId=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,recommend);
            statement.setInt(2,commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }

    }

    //获取首页6个评论
    public List<Discuss> headLoad(){
        List<Discuss> discussList = new ArrayList<>();
        Connection connection =null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from food order by time desc limit 6";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Discuss discuss = new Discuss();
                discuss.setCommentId(resultSet.getInt("commentId"));
                discuss.setScore(resultSet.getInt("score"));
                discuss.setContent(resultSet.getString("content"));
                discuss.setTitle(resultSet.getString("title"));
                discuss.setTime(resultSet.getTimestamp("time"));
                discuss.setRecommend(resultSet.getInt("recommend"));
                discussList.add(discuss);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return discussList;

    }

    //获取排行榜前五名
    public List<Discuss> top(){
        List<Discuss> discussList = new ArrayList<>();
        Connection connection =null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select title,recommend from food where score>5 order by recommend desc limit 5";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Discuss discuss = new Discuss();
                discuss.setTitle(resultSet.getString("title"));
                discuss.setRecommend(resultSet.getInt("recommend"));
                discussList.add(discuss);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return discussList;

    }

    //获取排行榜后五名
    public List<Discuss> down(){
        List<Discuss> discussList = new ArrayList<>();
        Connection connection =null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select title,recommend from food where score<5 order by recommend limit 5";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Discuss discuss = new Discuss();
                discuss.setTitle(resultSet.getString("title"));
                discuss.setRecommend(resultSet.getInt("recommend"));
                discussList.add(discuss);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return discussList;

    }


}
