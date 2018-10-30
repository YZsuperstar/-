package con;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.io.*;
public class login extends JFrame {
    JLabel label1, label2;
    JTextField text1;
    JPasswordField text2;
    JButton button1, button2,button3;
    String number;
    login() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        myJFrame bj = new myJFrame("bj.png", 0.7f);
        bj.setLayout(null);
        Font font = new Font("宋体", Font.PLAIN, 30);

        label1 = new JLabel("职工号");
        label1.setFont(font);
        label1.setBounds(320, 285, 100, 30);

        label2 = new JLabel("密码");
        label2.setFont(font);
        label2.setBounds(320, 375, 100, 30);

        text1 = new JTextField();
        text1.setBounds(465, 280, 230, 40);
        text2 = new JPasswordField();
        text2.setBounds(465, 370, 230, 40);
        button1 = new JButton("登陆");               
        button1.setBounds(408, 440, 140, 60);
        button2 = new JButton("退出");
        button2.setBounds(575, 440, 140, 60);
        button3=new JButton("注册");
        button3.setBounds(720,280,70,40);

        bj.add(label1);
        bj.add(text1);
        bj.add(label2);
        bj.add(text2);
        bj.add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == button2) {
                    System.exit(0);
                } else if (e.getSource() == button1) {
                    String user, pass;
                    user = text1.getText();
                    pass = text2.getText();

                    //System.out.println(user + "," + pass);
                    if (validate(user, pass)) {
                        getNumber(user);
                        Number();

                        JOptionPane.showMessageDialog(bj, "登录成功");
                        boolean y = true, n = false;
                        bj.setVisible(n);
                        new frame();

                    } else
                        JOptionPane.showMessageDialog(bj, "用户名或密码误！", "消息", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        bj.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button2) {
                    System.exit(0);
                }
            }
        });
        bj.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new zhuce();
            }
        });

        bj.setLocation(300, 100);
        bj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bj.setVisible(true);
        bj.validate();
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
                ResultSet rs = sql.executeQuery("select *from 职工表 where 职工表.职工号='" + userNumber + "' and 职工表.密码='" + userPass + "'");
        ){
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getNumber(String s) {
        this.number=s;
    }

    public void Number(){
        FileWriter fw=null;
        File f=new File("a.txt");
        try{
            if (!f.exists()){
                f.createNewFile();
            }
            fw=new FileWriter(f);
            //BufferedWriter out = new BufferedWriter(fw);
            fw.write(number, 0, number.length());
            //System.out.println(number);
            fw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}