package con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class huiyuanlogin extends JFrame{
    JLabel label1,label2;
    JTextField text1;
    JPasswordField text2;
    JButton button1,button2;

    public String loginok="0";
    huiyuanlogin()
    {
        super("会员登陆");
        setLayout(new FlowLayout());
        label1=new JLabel("   会员号:");
        label2=new JLabel("      密码:");
        text1=new JTextField(8);
        text2=new JPasswordField(8);
        button1=new JButton("登陆");
        button2=new JButton("取消");
        add(label1);
        add(text1);
        add(label2);
        add(text2);
        add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number=text1.getText();
                String pass1=text2.getText();
                if (validate(number,pass1)){
                        JOptionPane.showMessageDialog(huiyuanlogin.this,number+"登陆成功!!!");
                        loginok="ok";
                        Loginok(loginok);
                        dispose();
                    }
                    else{
                        loginok="false";
                        Loginok(loginok);
                        JOptionPane.showMessageDialog(huiyuanlogin.this,"登陆失败，密码错误！！！");
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
        setBounds(700,300,200,200);
        setVisible(true);
        validate();
    }

    private boolean validate(String userNumber, String userPass) {
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
                ResultSet rs = sql.executeQuery("select *from 会员表 where 会员表.会员号='" + userNumber + "' and 会员表.密码='" + userPass + "'");
        ){
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void Loginok(String s){
        FileWriter fw=null;
        File f=new File("login.txt");
        try{
            if (!f.exists()){
                f.createNewFile();
            }
            fw=new FileWriter(f);
            //BufferedWriter out = new BufferedWriter(fw);
            fw.write(s, 0, s.length());
            //System.out.println(number);
            fw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
