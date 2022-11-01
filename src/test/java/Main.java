import dao.DiscussDao;

public class Main {
    public static void main(String[] args) {
//        String path = "D:/bit/Java_study/javaweb/delicious_food/src/main/webapp/images/24e65029-8f9e-4470-bb86-356204f5e886.png";
//        int length = path.length();
//        String substring = path.substring(i, length);
//        System.out.println(substring);
        DiscussDao discussDao = new DiscussDao();
        int sum = discussDao.sum();
        System.out.println(sum);
    }
}
