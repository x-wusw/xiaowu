package xiaowu;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jdbcs {
    Connection con = null;
    Statement statement = null;
    ResultSet res = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/bishe?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    String name = "root";
    String passwd = "shenwu0314";

    public Jdbcs() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, name, passwd);
            statement = con.createStatement();

        } catch (ClassNotFoundException e) {
            System.out.println("�Բ����Ҳ������Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //ȡ�������ı�������ʾ
    public String[] getText(){
        String sql = "select * from article";
        String s = null;
        String[] ss = null;
        List<String> list = new ArrayList<>();
        try {
            res = statement.executeQuery(sql);
            while (res.next()){
                s = res.getString("topic");
                //System.out.println(s);
                list.add(s);
                if(list != null && list.size()>0){
                    ss = new String[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        ss[i] = list.get(i);
                    }
                }

            }
            statement.close();
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ss;
    }
    //����idȡ���Ƽ�����
    public String ref(int id) {
        String s = null;
        String sql = "select article from article where id = \""+id+"\"";
        try {
            res = statement.executeQuery(sql);
            while (res.next()){
                s = res.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       /* con.close();
        statement.close();*/
        return s;
    }
    //��ѯ�������ݷ���textpane��
    public String fangru(String topic) {
        String s = null;
        String sql = "select article from article where topic = \""+topic+"\"";
        try {
            res = statement.executeQuery(sql);
            while (res.next()){
                s = res.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      /*  con.close();
        statement.close();*/
        return s;
    }
    //���û���Ϣ���޸�ʵ���Ͼ��Ƕ�������޸�
    public boolean update(String username1, String password1, String newpassword) {
        boolean judge = false;
        boolean s = compare(username1, password1);
        if (s) {
            String sql = "update user set password=\"" + newpassword + "\"where username=\"" + username1 + "\"";
            try {
                int a = statement.executeUpdate(sql);
                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "�����޸ĳɹ���");
                    judge = true;
                }
                con.close();
                statement.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "�û������ڣ�");
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "�޸�ʧ��");
        }
        return judge;
    }

    //ɾ���û���Ϣ
    public void delete(String username, String password) {
        if (compare(username, password)) {
            JOptionPane.showMessageDialog(null, "�Ѿ����ɾ��");
        } else {
            return;
        }
        String sql = "delete from user where username=\"" + username + "\"";
        try {
            int a = statement.executeUpdate(sql);
            con.close();
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "�����ڸ��û���");
            e.printStackTrace();
        }

    }
    //��Ϸ�ɼ�����
    public void ginsertdb(String username,int speed,double accuracy){
        String sql = "insert into grade(username,speed,accuracy) values(\""+username+"\",\"" + speed + "\",\"" + accuracy + "\")";
        try {
            statement.executeUpdate(sql);
            /*statement.close();
            con.close();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //��������������������������
    public void uainsert(String username,String topic){
        String sql = "insert into userarticle(username,topic) values(\""+username+"\",\""+topic+"\")";
        try {
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //�Ƽ�ҳ����Ϣ�������ݿ�
    public void insertdb(String username,String sex,int age,String career,String articletypename) {
        String sql = "insert into recommend(username,sex,age,career,articletypename) values(\"" + username + "\",\"" +
                sex + "\",\"" + age + "\",\""+career+"\",\""+articletypename+"\")";
        try {
            statement.executeUpdate(sql);
           // con.close();
           // statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //�û�ע�Ṧ�ܵ�ʵ�֣��������
    public void insert(String username, String password) {
        Jdbcs j = new Jdbcs();
        if (username == null || username.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "ע���˺�Ϊ�գ����������룡");
            return;
        }
        if(j.isexist1(username)){
            JOptionPane.showMessageDialog(null, "�Բ�����û����Ѿ����ˣ�");
            return;
        }
        String sql = "insert into user(username,password) values(\"" + username + "\",\"" + password + "\")";
        try {
            int a = statement.executeUpdate(sql);
            con.close();
            statement.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "ע��ɹ���");
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "�Բ�����û����Ѿ����ˣ�");
            e.printStackTrace();
        }
    }
    //�û������Ƿ��и��û�
    public boolean isexist1(String username){
        boolean b = false;
        String sql = "select username from user where username=\"" + username + "\"";
        try {
            res =  statement.executeQuery(sql);
            if(res.next()){
                String s = res.getString(1);
                // System.out.println(s);
                if(s.equals(username)){
                    b = true;
                }
            }
           /* res.close();
            con.close();
            statement.close();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;

    }
    //�Ƽ������Ƿ����û�������
    public boolean isexist(String username){
        boolean b = false;
        String sql = "select username from recommend where username=\"" + username + "\"";
        try {
            res =  statement.executeQuery(sql);
            if(res.next()){
                String s = res.getString(1);
               // System.out.println(s);
                if(s.equals(username)){
                    b = true;
                }
            }
           /* res.close();
            con.close();
            statement.close();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;

    }
    //�Ա��û����������ǲ�ƥ��
    public boolean compare(String username, String password) {
        boolean m = false;
        String sql = "select password from user where username=\"" + username + "\"";
        try {
            res = statement.executeQuery(sql);
            if (res.next()) {
                String pa = res.getString(1);
                //System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    m = true;
                } else {
                    JOptionPane.showMessageDialog(null, "�������");
                    new Login();
                }
            } else {
                JOptionPane.showMessageDialog(null, "�û��������ڣ�");
                new Login();
            }
            //res.close();
            //con.close();
           // statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
    //ȡ���ɼ�
    public int[] pushSpeed(String username){
        List<Integer> list = new ArrayList<>();
        String sql = "select speed from grade where username = \""+ username + "\"";
        try {
            res = statement.executeQuery(sql);
            while (res.next()){
                String a = res.getString(1);
                list.add(Integer.parseInt(a));
            }
            /*statement.close();
            res.close();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int len = list.size();
        int[] speed = new int[len];
        for (int i = 0; i < len; i++) {
            speed[i] = list.get(i);
        }
        return speed;
    }
    //ȡ����ȷ��
    public double[] pushAccuracy(String username){
        List<Double> list = new ArrayList<>();
        String sql = "select accuracy from grade where username = \""+ username + "\"";
        try {
            res = statement.executeQuery(sql);
            while (res.next()){
                String a = res.getString(1);
                list.add(Double.parseDouble(a));
            }
           /* statement.close();
            res.close();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int len = list.size();
        double[] accuracy = new double[len];
        for (int i = 0; i < len; i++) {
            accuracy[i] = list.get(i);
        }
        return accuracy;
    }
    //ȡ���Ƽ����������
    public List[] getData(){
        String sql = "select * from recommend";
        List[] list = new List[5];
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();
        List list4 = new ArrayList();
        List list5 = new ArrayList();
        try {
            res = statement.executeQuery(sql);
            while (res.next()){
                String s1 = res.getString(2);
                String s2 = res.getString(3);
                String s3 = res.getString(4);
                String s4 = res.getString(5);
                String s5 = res.getString(6);
                list1.add(s1);
                list2.add(s2);
                list3.add(s3);
                list4.add(s4);
                list5.add(s5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list[0] = list1;
        list[1] = list2;
        list[2] = list3;
        list[3] = list4;
        list[4] = list5;
        return list;
    }
}
