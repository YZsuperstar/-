package con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class huiyuanzhuce extends JFrame{
    JLabel label1,label2,label3,label4;
    JTextField text1,text2,text3,text4;
    JButton button1,button2;
    huiyuanzhuce()
    {
        super("会员注册");
        setLayout(new FlowLayout());
        label1=new JLabel("   会员号:");
        label2=new JLabel("      姓名:");
        label3=new JLabel("      密码:");
        label4=new JLabel("确认密码:");
        text1=new JTextField(8);
        text2=new JTextField(8);
        text3=new JTextField(8);
        text4=new JTextField(8);
        button1=new JButton("注册");
        button2=new JButton("取消");
        add(label1);
        add(text1);
        add(label2);
        add(text2);
        add(label3);
        add(text3);
        add(label4);
        add(text4);
        add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number=text1.getText(),name=text2.getText();
                String pass1=text3.getText(),pass2=text4.getText();
                if (pass1.equals(pass2)){
                    if (selectNumber(number)){
                        tianjia(number,name,pass1);
                        JOptionPane.showMessageDialog(huiyuanzhuce.this,"注册成功!!!");
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(huiyuanzhuce.this,"会员号已经存在");
                }
                else {
                    JOptionPane.showMessageDialog(huiyuanzhuce.this,"输入的密码不相同!!!");
                }
            }
        });
        add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setBounds(700,300,200,250);
        setVisible(true);
        validate();
    }

    private boolean selectNumber(String userNumber) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
        }
        String uri = "jdbc:mysql://localhost:3306/dianying?characterEncoding=utf8&useSSL=false";
        String user = "root";
        String password = "123456";
        try {
            con = DriverManager.getConnection(uri, user, password);
        } catch (SQLException e) {
        }
        try (
                Statement sql = con.createStatement();
                ResultSet rs = sql.executeQuery("select *from 会员表表 where 会员表.会员号='" + userNumber + "'");

        ){
            if (rs.next()) {
                return false;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void tianjia(String number,String name,String pass) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
        }
        String uri = "jdbc:mysql://localhost:3306/dianying?characterEncoding=utf8&useSSL=false";
        String user = "root";
        String password = "123456";
        try {
            con = DriverManager.getConnection(uri, user, password);
        } catch (SQLException e) {
        }
        try{
            Statement sql=con.createStatement();
            ResultSet rs ;
            sql.execute("INSERT INTO 会员表(会员号, 密码, 会员名) VALUES('"+number+"','"+pass+"','"+name+"')");

            con.close();
        }catch (SQLException e){}
    }
}
