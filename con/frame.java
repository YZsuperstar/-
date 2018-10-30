package con;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.naming.Name;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.io.IOException;
import java.lang.String;

public class frame extends JFrame {
    //创建一个JLayeredPane用于分层的。
    public String x1="";
    JLayeredPane layeredPane;
    //创建一个Panel和一个Label用于存放图片，作为背景。
    JPanel jp;
    JLabel jl;
    ImageIcon image;

    //创建一个按钮用于测试的。
    JButton button1, button2, button3;
    JLabel label1, label2, label3;
    JTree tree;

    //购票界面
    JTextField text1,text2,text3,text4;
    JButton huiyuanbutton,putongbutton;
    JLabel jageLable;
    public String loginok="1";

    //座位按钮
    JButton button1_1,button1_2,button1_3,button1_4,button1_5,button1_6,button1_7;
    JButton button2_1,button2_2,button2_3,button2_4,button2_5,button2_6,button2_7;
    JButton button3_1,button3_2,button3_3,button3_4,button3_5,button3_6,button3_7;
    JButton button4_1,button4_2,button4_3,button4_4,button4_5,button4_6,button4_7;

    //电影信息
    String name1=Name(1); //电影1信息
    String name2=Name(2); //电影2信息
    String name3=Name(3); //电影2信息
    String name4=Name(4); //电影2信息

    String gting;

    JLabel Lname,Lcountry,Ltype,Lsytime,Labs;
    JLabel picture;


    String sNumber=Number();       //职工号
    String sName=mysql1(sNumber);    //职工名

    public frame() {

        super("售票系统");
        layeredPane = new JLayeredPane();
        image = new ImageIcon("picture/界面3.png");
        //创建背景的那些东西
        jp = new JPanel();
        jp.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());

        jl = new JLabel(image);
//      jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        jp.add(jl);

        //将背景放低一点的地方
        layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);


        Font font = new Font("宋体", Font.PLAIN, 30);

        label1 = new JLabel(sNumber);
        label1.setFont(font);
        label1.setBounds(20, 35, 200, 30);
        label1.setForeground(Color.white);

        label2 = new JLabel(","+sName );
        label2.setFont(font);
        label2.setBounds(130, 35, 200, 30);
        label2.setForeground(Color.white);

        label3 = new JLabel(",欢迎您！");
        label3.setFont(font);
        label3.setBounds(250, 35, 200, 30);
        label3.setForeground(Color.white);

        layeredPane.add(label1, JLayeredPane.MODAL_LAYER);
        layeredPane.add(label2, JLayeredPane.MODAL_LAYER);
        layeredPane.add(label3, JLayeredPane.MODAL_LAYER);

   /*     button1 = new JButton("添加/修改影片");
        button1.setBounds(400, 35, 100, 50);
        layeredPane.add(button1, JLayeredPane.MODAL_LAYER);
*/
        button2 = new JButton("顾客注册会员");
        button2.setBounds(450, 35, 100, 50);
        layeredPane.add(button2, JLayeredPane.MODAL_LAYER);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new huiyuanzhuce();
            }
        });

        button3 = new JButton("退出程序");
        button3.setBounds(640, 35, 100, 50);
        layeredPane.add(button3, JLayeredPane.MODAL_LAYER);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //电影信息的设置
        Font labelfont =new Font("宋体",Font.PLAIN,30);
       // ImageIcon icon1=new ImageIcon("picture/燃烧.jpg");
       // icon1.setImage(icon1.getImage().getScaledInstance(250,335,Image.SCALE_DEFAULT));
        picture=new JLabel();
        picture.setBounds(180,150,250,335);
        layeredPane.add(picture, JLayeredPane.MODAL_LAYER);

        Lname=new JLabel();
        Lname.setForeground(Color.white);
        Lname.setBounds(600,170,200,30);
        Lname.setFont(labelfont);
        layeredPane.add(Lname, JLayeredPane.MODAL_LAYER);

        Lcountry=new JLabel();
        Lcountry.setForeground(Color.white);
        Lcountry.setBounds(560,225,200,30);
        Lcountry.setFont(labelfont);
        layeredPane.add(Lcountry, JLayeredPane.MODAL_LAYER);

        Ltype=new JLabel();
        Ltype.setForeground(Color.white);
        Ltype.setBounds(800,225,200,30);
        Ltype.setFont(labelfont);
        layeredPane.add(Ltype, JLayeredPane.MODAL_LAYER);

        Lsytime=new JLabel();
        Lsytime.setForeground(Color.white);
        Lsytime.setBounds(645,290,200,30);
        Lsytime.setFont(labelfont);
        layeredPane.add(Lsytime, JLayeredPane.MODAL_LAYER);

        Labs=new JLabel();
        Labs.setForeground(Color.white);
        Labs.setBounds(643,220,400,400);
        Labs.setFont(labelfont);
        layeredPane.add(Labs, JLayeredPane.MODAL_LAYER);


        //    System.out.println(name1+","+country1+","+type1+","+sytime1+","+abs1);

        //tree树键
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("电影");
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(name1);
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(name2);
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode(name3);
        DefaultMutableTreeNode node4 = new DefaultMutableTreeNode(name4);
        DefaultMutableTreeNode Time1_1 = new DefaultMutableTreeNode("1号厅,7:00-8:30");
        DefaultMutableTreeNode Time1_2 = new DefaultMutableTreeNode("2号厅,16:00-17:30");
        DefaultMutableTreeNode Time2_1 = new DefaultMutableTreeNode("1号厅，7:00-8:30");
        DefaultMutableTreeNode Time2_2 = new DefaultMutableTreeNode("2号厅，16:00-17:30");
        DefaultMutableTreeNode Time3 = new DefaultMutableTreeNode("1号厅，9:00-10:40");
        DefaultMutableTreeNode Time4 = new DefaultMutableTreeNode("2号厅，12:00-13:25");
        root.add(node1);
        root.add(node2);
        root.add(node3);
        root.add(node4);
        node1.add(Time1_1);
        node1.add(Time1_2);
        node2.add(Time2_1);
        node2.add(Time2_2);
        node3.add(Time3);
        node4.add(Time4);
        tree = new JTree(root);
        tree.setBounds(0, 150, 173, 800);
        layeredPane.add(tree, JLayeredPane.MODAL_LAYER);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // 获取当前选择的第一个结点中的最后一个路径组件
                DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (dmt.isLeaf()) {
                    String str = dmt.toString();// 叶子结点的字符串
                    // 判断
                    if (str.equals("1号厅,7:00-8:30")) {
                        x1="1";
                        treesetdianying(name1);
                        treesetduowei(name1,x1);
                        setjage(name1,x1);
                    } else if (str.equals("2号厅,16:00-17:30")) {
                        x1="2";
                        treesetdianying(name1);
                        treesetduowei(name1,x1);
                        setjage(name1,x1);
                    } else if (str.equals("1号厅，7:00-8:30")) {
                        x1="1";
                        treesetdianying(name2);
                        treesetduowei(name2,x1);
                        setjage(name2,x1);
                    } else if (str.equals("2号厅，16:00-17:30")) {
                        x1="2";
                        treesetdianying(name2);
                        treesetduowei(name2,x1);
                        setjage(name2,x1);
                    }else if (str.equals("1号厅，9:00-10:40")) {
                        x1="1";
                        treesetdianying(name3);
                        treesetduowei(name3,x1);
                        setjage(name3,x1);
                    }else if (str.equals("2号厅，12:00-13:25")) {
                        x1="2";
                        treesetdianying(name4);
                        treesetduowei(name4,x1);
                        setjage(name4,x1);
                    }
                }
            }
        });



        //座位按钮设置
        Font buttonFont = new Font("宋体", Font.PLAIN, 45);
        button1_1=new JButton("1-1");
        button1_1.setBounds(260,563,100,100);
        layeredPane.add(button1_1, JLayeredPane.MODAL_LAYER);
        button1_1.setFont(buttonFont);
        button1_1.setBackground(Color.green);
        button1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("01");
                text3.setText("1");
                text4.setText("1");
            }
        });
     /*   if (shifouyoupaio(name1,"1","1","1"))
            button1_1.setForeground(Color.GREEN);
        else
            button1_1.setForeground(Color.RED);
*/
        button1_2=new JButton("1-2");
        button1_2.setBounds(363,563,100,100);
        layeredPane.add(button1_2, JLayeredPane.MODAL_LAYER);
        button1_2.setFont(buttonFont);
        button1_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("02");
                text3.setText("1");
                text4.setText("2");
            }
        });
        button1_3=new JButton("1-3");
        button1_3.setBounds(463,563,110,100);
        layeredPane.add(button1_3, JLayeredPane.MODAL_LAYER);
        button1_3.setFont(buttonFont);
        button1_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("03");
                text3.setText("1");
                text4.setText("3");
            }
        });
        button1_4=new JButton("1-4");
        button1_4.setBounds(575,563,110,100);
        layeredPane.add(button1_4, JLayeredPane.MODAL_LAYER);
        button1_4.setFont(buttonFont);
        button1_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("04");
                text3.setText("1");
                text4.setText("4");
            }
        });

        button1_5=new JButton("1-5");
        button1_5.setBounds(686,563,95,100);
        layeredPane.add(button1_5, JLayeredPane.MODAL_LAYER);
        button1_5.setFont(buttonFont);
        button1_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("05");
                text3.setText("1");
                text4.setText("5");
            }
        });

        button1_6=new JButton("1-6");
        button1_6.setBounds(780,563,100,100);
        layeredPane.add(button1_6, JLayeredPane.MODAL_LAYER);
        button1_6.setFont(buttonFont);
        button1_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("06");
                text3.setText("1");
                text4.setText("6");
            }
        });

        button1_7=new JButton("1-7");
        button1_7.setBounds(882,563,95,100);
        layeredPane.add(button1_7, JLayeredPane.MODAL_LAYER);
        button1_7.setFont(buttonFont);
        button1_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("07");
                text3.setText("1");
                text4.setText("7");
            }
        });

        button2_1=new JButton("2-1");
        button2_1.setBounds(260,663,100,87);
        layeredPane.add(button2_1, JLayeredPane.MODAL_LAYER);
        button2_1.setFont(buttonFont);
        button2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("08");
                text3.setText("2");
                text4.setText("1");
            }
        });

        button2_2=new JButton("2-2");
        button2_2.setBounds(363,663,100,87);
        layeredPane.add(button2_2, JLayeredPane.MODAL_LAYER);
        button2_2.setFont(buttonFont);
        button2_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("09");
                text3.setText("2");
                text4.setText("2");
            }
        });

        button2_3=new JButton("2-3");
        button2_3.setBounds(463,663,110,87);
        layeredPane.add(button2_3, JLayeredPane.MODAL_LAYER);
        button2_3.setFont(buttonFont);
        button2_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("10");
                text3.setText("2");
                text4.setText("3");
            }
        });

        button2_4=new JButton("2-4");
        button2_4.setBounds(575,663,110,87);
        layeredPane.add(button2_4, JLayeredPane.MODAL_LAYER);
        button2_4.setFont(buttonFont);
        button2_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("11");
                text3.setText("2");
                text4.setText("4");
            }
        });

        button2_5=new JButton("2-5");
        button2_5.setBounds(686,663,95,87);
        layeredPane.add(button2_5, JLayeredPane.MODAL_LAYER);
        button2_5.setFont(buttonFont);
        button2_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("12");
                text3.setText("2");
                text4.setText("5");
            }
        });

        button2_6=new JButton("2-6");
        button2_6.setBounds(780,663,100,87);
        layeredPane.add(button2_6, JLayeredPane.MODAL_LAYER);
        button2_6.setFont(buttonFont);
        button2_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("13");
                text3.setText("2");
                text4.setText("6");
            }
        });

        button2_7=new JButton("2-7");
        button2_7.setBounds(882,663,95, 87);
        layeredPane.add(button2_7, JLayeredPane.MODAL_LAYER);
        button2_7.setFont(buttonFont);
        button2_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("14");
                text3.setText("2");
                text4.setText("7");
            }
        });

        button3_1=new JButton("3-1");
        button3_1.setBounds(260,747,100,75);
        layeredPane.add(button3_1, JLayeredPane.MODAL_LAYER);
        button3_1.setFont(buttonFont);
        button3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("15");
                text3.setText("3");
                text4.setText("1");
            }
        });

        button3_2=new JButton("3-2");
        button3_2.setBounds(363,747,100,75);
        layeredPane.add(button3_2, JLayeredPane.MODAL_LAYER);
        button3_2.setFont(buttonFont);
        button3_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("16");
                text3.setText("3");
                text4.setText("2");
            }
        });


        button3_3=new JButton("3-3");
        button3_3.setBounds(463,747,110,75);
        layeredPane.add(button3_3, JLayeredPane.MODAL_LAYER);
        button3_3.setFont(buttonFont);
        button3_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("17");
                text3.setText("3");
                text4.setText("3");
            }
        });

        button3_4=new JButton("3-4");
        button3_4.setBounds(575,747,110,75);
        layeredPane.add(button3_4, JLayeredPane.MODAL_LAYER);
        button3_4.setFont(buttonFont);
        button3_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("18");
                text3.setText("3");
                text4.setText("4");
            }
        });

        button3_5=new JButton("3-5");
        button3_5.setBounds(686,747,95,75);
        layeredPane.add(button3_5, JLayeredPane.MODAL_LAYER);
        button3_5.setFont(buttonFont);
        button3_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("19");
                text3.setText("3");
                text4.setText("5");
            }
        });

        button3_6=new JButton("3-6");
        button3_6.setBounds(780,747,100,75);
        layeredPane.add(button3_6, JLayeredPane.MODAL_LAYER);
        button3_6.setFont(buttonFont);
        button3_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("20");
                text3.setText("3");
                text4.setText("6");
            }
        });

        button3_7=new JButton("3-7");
        button3_7.setBounds(882,747,95,75);
        layeredPane.add(button3_7, JLayeredPane.MODAL_LAYER);
        button3_7.setFont(buttonFont);
        button3_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("21");
                text3.setText("3");
                text4.setText("7");
            }
        });

        button4_1=new JButton("4-1");
        button4_1.setBounds(260,823,100,86);
        layeredPane.add(button4_1, JLayeredPane.MODAL_LAYER);
        button4_1.setFont(buttonFont);
        button4_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("22");
                text3.setText("4");
                text4.setText("1");
            }
        });

        button4_2=new JButton("4-2");
        button4_2.setBounds(363,823,100,86);
        layeredPane.add(button4_2, JLayeredPane.MODAL_LAYER);
        button4_2.setFont(buttonFont);
        button4_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("23");
                text3.setText("4");
                text4.setText("2");
            }
        });

        button4_3=new JButton("4-3");
        button4_3.setBounds(463,823,110,86);
        layeredPane.add(button4_3, JLayeredPane.MODAL_LAYER);
        button4_3.setFont(buttonFont);
        button4_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("24");
                text3.setText("4");
                text4.setText("3");
            }
        });

        button4_4=new JButton("4-4");
        button4_4.setBounds(575,823,110,86);
        layeredPane.add(button4_4, JLayeredPane.MODAL_LAYER);
        button4_4.setFont(buttonFont);
        button4_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("25");
                text3.setText("4");
                text4.setText("4");
            }
        });

        button4_5=new JButton("4-5");
        button4_5.setBounds(686,823,95,86);
        layeredPane.add(button4_5, JLayeredPane.MODAL_LAYER);
        button4_5.setFont(buttonFont);
        button4_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("26");
                text3.setText("4");
                text4.setText("5");
            }
        });

        button4_6=new JButton("4-6");
        button4_6.setBounds(780,823,100,86);
        layeredPane.add(button4_6, JLayeredPane.MODAL_LAYER);
        button4_6.setFont(buttonFont);
        button4_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("27");
                text3.setText("4");
                text4.setText("6");
            }
        });

        button4_7=new JButton("4-7");
        button4_7.setBounds(882,823,95,86);
        layeredPane.add(button4_7, JLayeredPane.MODAL_LAYER);
        button4_7.setFont(buttonFont);
        button4_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2.setText("28");
                text3.setText("4");
                text4.setText("7");
            }
        });


        //购票界面
        Font textfont=new Font("宋体",Font.PLAIN,20);
        text1=new JTextField();
        text1.setFont(textfont);
        text1.setBounds(1140,360,130,50);
        layeredPane.add(text1, JLayeredPane.MODAL_LAYER);

        text2=new JTextField();
        text2.setFont(font);
        text2.setBounds(1140,420,130,50);
        layeredPane.add(text2, JLayeredPane.MODAL_LAYER);

        text3=new JTextField();
        text3.setFont(font);
        text3.setBounds(1140,490,130,50);
        layeredPane.add(text3, JLayeredPane.MODAL_LAYER);

        text4=new JTextField();
        text4.setFont(font);
        text4.setBounds(1140,550,130,50);
        layeredPane.add(text4, JLayeredPane.MODAL_LAYER);

        jageLable=new JLabel();
        jageLable.setFont(font);
        jageLable.setBounds(1170,660,200,30);
        layeredPane.add(jageLable, JLayeredPane.MODAL_LAYER);

        Font bufont =new Font("宋体",Font.PLAIN,15);
        huiyuanbutton=new JButton("会员登陆");
        huiyuanbutton.setFont(bufont);
        huiyuanbutton.setBounds(1020,730,100,60);
        layeredPane.add(huiyuanbutton, JLayeredPane.MODAL_LAYER);
        huiyuanbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loginok=gethuiyuanok();  //会员登陆成功后loginok ="login"\
                new huiyuanlogin();

            }
        });

        putongbutton=new JButton("普通购票");
        putongbutton.setFont(bufont);
        putongbutton.setBounds(1160,730,100,60);
        layeredPane.add(putongbutton, JLayeredPane.MODAL_LAYER);
        putongbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gname=text1.getText();
                String ghang=text3.getText();
                String glie=text4.getText();
                String gjage=jageLable.getText();
                if (!shifouyoupaio(gname, x1, ghang, glie))
                    JOptionPane.showMessageDialog(layeredPane, "该座位无票出售");
                else {
                    setzuowei(gname, x1, ghang, glie);
                    treesetduowei(gname, x1);
                    String s="ok";
                    if (!loginok.equals(s)){
                        JOptionPane.showMessageDialog(layeredPane, "支付"+gjage+",购票成功");

                    }
                    else if(loginok.equals(s)){
                        String a=jageLable.getText();
                        int b=Integer.parseInt(a);
                        b=b-5;
                        String jage=String.valueOf(b);
                        JOptionPane.showMessageDialog(layeredPane, "支付"+jage+"元,购票成功");
                        loginok="1";
                    }
                }
            }
        });

        this.setLayeredPane(layeredPane);
        this.setSize(image.getIconWidth(), image.getIconHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 10);
        this.setVisible(true);
    }

    public String mysql1(String sNumber) {
        Connection con = null;
        String name=null;
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
        Statement sql= con.createStatement();
        ResultSet rs = sql.executeQuery("select 姓名 from 职工表 where 职工表.职工号='"+sNumber+"' ");
        while (rs.next())
            name=rs.getString(1);
        //System.out.println(name);
        con.close();
        }catch (SQLException e){}
        return name;
    }

    public String Number() {
        String lineTxt = null;
        try {
            String encoding = "utf-8";
            File file = new File("a.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);

                lineTxt = bufferedReader.readLine();
                //System.out.println(lineTxt);
                read.close();
                file.delete();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lineTxt;
    }

    public String Name(int x) {
        Connection con = null;
        String number="S0"+x;
        String name=null;
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
            Statement sql= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = sql.executeQuery("select 电影名 from 电影表 WHERE 电影表.电影号='"+number+"'");
            while (rs.next())
                name =rs.getString(1);
            con.close();
        }catch (SQLException e){}
        return name;
    }

/*   public String Time(String name) {
        Connection con = null;
        String time,ting;
       // String time[]=new String[]{};
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
            Statement sql= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = sql.executeQuery("select 厅号 时间 from 排片表 JOIN 电影表 ON 排片表.电影号=电影表.电影号 WHERE 电影名='"+name+"'");
            while (rs.next()) {
                time = rs.getString(1);
        }
            con.close();

        }catch (SQLException e){}

    }
*/
    public int setXinxi(String name) {
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
            Statement sql= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = sql.executeQuery("select 国家,类型,上映时间,电影简介 from 电影表 WHERE 电影表.电影名='"+name+"'");
                while (rs.next()){
                    String country =rs.getString(1);
                    String type=rs.getString(2);
                String sytime=rs.getString(3);
                String abs=rs.getString(4);
                //System.out.println(country+type+sytime+abs);
                FileWriter country1,type1,sytime1,abs1=null;
                File f1=new File("country.txt"),f2=new File("type.txt");
                File f3=new File("sytime.txt"),f4=new File("abs.txt");
                try{
                    if (!f1.exists()){
                        f1.createNewFile();
                    }
                    if (!f2.exists()){
                        f1.createNewFile();
                    }
                    if (!f3.exists()){
                        f1.createNewFile();
                    }
                    if (!f4.exists()){
                        f1.createNewFile();
                    }
                    country1=new FileWriter(f1);
                    type1=new FileWriter(f2);
                    sytime1=new FileWriter(f3);
                    abs1=new FileWriter(f4);
                    country1.write(country, 0, country.length());
                    type1.write(type, 0, type.length());
                    sytime1.write(sytime, 0, sytime.length());
                    abs1.write(abs, 0, abs.length());
                    country1.flush();
                    type1.flush();
                    sytime1.flush();
                    abs1.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            con.close();
        }catch (SQLException e){}
       // System.out.println(cou);
        return 1;
    }

    //判断是否有票
    private boolean shifouyoupaio(String name,String ting,String h, String l) {
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
                ResultSet rs = sql.executeQuery("select *from 座位表 JOIN 电影表 ON 座位表.电影号=电影表.电影号" +
                        " where 电影表.电影名='"+name+"'and 座位表.厅号='"+ting+"'AND 座位表.行='"+h+"' and 座位表.列='"+l+"'AND 座位表.是否已售='否'");
        ){
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getCountry(){
        String lineTxt = null;
        try {
            String encoding = "utf-8";
            File file = new File("country.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                lineTxt = bufferedReader.readLine();
                //System.out.println(lineTxt);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lineTxt;
    }
    public String gettype(){
        String lineTxt = null;
        try {
            String encoding = "utf-8";
            File file = new File("type.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                lineTxt = bufferedReader.readLine();
                //System.out.println(lineTxt);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lineTxt;
    }
    public String getsytime(){
        String lineTxt = null;
        try {
            String encoding = "utf-8";
            File file = new File("sytime.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                lineTxt = bufferedReader.readLine();
                //System.out.println(lineTxt);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lineTxt;
    }
    public String getabs(){
        String lineTxt = null;
        try {
            String encoding = "utf-8";
            File file = new File("abs.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                lineTxt = bufferedReader.readLine();
                //System.out.println(lineTxt);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lineTxt;
    }

    public void treesetdianying(String name){
        int yes=setXinxi(name);
        String country=getCountry();
        String type=gettype();
        String sytime=getsytime();
        String abs=getabs();
        String iconname="picture/"+name+".jpg";
        ImageIcon icon=new ImageIcon(iconname);
        icon.setImage(icon.getImage().getScaledInstance(250,335,Image.SCALE_DEFAULT));
        picture.setIcon(icon);
        //System.out.println(country+type+sytime+abs);
        Lname.setText(name);
        Lcountry.setText(country);
        Ltype.setText(type);
        Lsytime.setText(sytime);
        Labs.setText(abs);
        text1.setText(name);
        FileWriter country1,type1,sytime1,abs1=null;
        File f1=new File("country.txt"),f2=new File("type.txt");
        File f3=new File("sytime.txt"),f4=new File("abs.txt");
        f1.delete();
        f2.delete();
        f3.delete();
        f4.delete();
    }

    public void treesetduowei(String name,String ting){
        if (shifouyoupaio(name,ting,"1","1"))
            button1_1.setForeground(Color.GREEN);
        else
            button1_1.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"1","2"))
            button1_2.setForeground(Color.GREEN);
        else
            button1_2.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"1","3"))
            button1_3.setForeground(Color.GREEN);
        else
            button1_3.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"1","4"))
            button1_4.setForeground(Color.GREEN);
        else
            button1_4.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"1","5"))
            button1_5.setForeground(Color.GREEN);
        else
            button1_5.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"1","6"))
            button1_6.setForeground(Color.GREEN);
        else
            button1_6.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"1","7"))
            button1_7.setForeground(Color.GREEN);
        else
            button1_7.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"2","1"))
            button2_1.setForeground(Color.GREEN);
        else
            button2_1.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"2","2"))
            button2_2.setForeground(Color.GREEN);
        else
            button2_2.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"2","3"))
            button2_3.setForeground(Color.GREEN);
        else
            button2_3.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"2","4"))
            button2_4.setForeground(Color.GREEN);
        else
            button2_4.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"2","5"))
            button2_5.setForeground(Color.GREEN);
        else
            button2_5.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"2","6"))
            button2_6.setForeground(Color.GREEN);
        else
            button2_6.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"2","7"))
            button2_7.setForeground(Color.GREEN);
        else
            button2_7.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"3","1"))
            button3_1.setForeground(Color.GREEN);
        else
            button3_1.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"3","2"))
            button3_2.setForeground(Color.GREEN);
        else
            button3_2.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"3","3"))
            button3_3.setForeground(Color.GREEN);
        else
            button3_3.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"3","4"))
            button3_4.setForeground(Color.GREEN);
        else
            button3_4.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"3","5"))
            button3_5.setForeground(Color.GREEN);
        else
            button3_5.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"3","6"))
            button3_6.setForeground(Color.GREEN);
        else
            button3_6.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"3","7"))
            button3_7.setForeground(Color.GREEN);
        else
            button3_7.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"4","1"))
            button4_1.setForeground(Color.GREEN);
        else
            button4_1.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"4","2"))
            button4_2.setForeground(Color.GREEN);
        else
            button4_2.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"4","3"))
            button4_3.setForeground(Color.GREEN);
        else
            button4_3.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"4","4"))
            button4_4.setForeground(Color.GREEN);
        else
            button4_4.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"4","5"))
            button4_5.setForeground(Color.GREEN);
        else
            button4_5.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"4","6"))
            button4_6.setForeground(Color.GREEN);
        else
            button4_6.setForeground(Color.RED);
        if (shifouyoupaio(name,ting,"4","7"))
            button4_7.setForeground(Color.GREEN);
        else
            button4_7.setForeground(Color.RED);
    }

    public void  setzuowei(String name,String ting,String hang,String lie){
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
            sql.execute("UPDATE 座位表 JOIN 电影表 ON 座位表.电影号=电影表.电影号 set 是否已售='是' WHERE 电影表.电影名='"+name+"'and 厅号='"+ting+"'and 行='"+hang+"' and 列='"+lie+"' ");

            con.close();
        }catch (SQLException e){}
    }

    public String gethuiyuanok() {
        String lineTxt = null;
        try {
            String encoding = "utf-8";
            File file = new File("login.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);

                lineTxt = bufferedReader.readLine();
                //System.out.println(lineTxt);
                read.close();
                file.delete();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lineTxt;
    }

    public void setjage(String name,String ting){
        Connection con = null;
        String jage=null;
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
            Statement sql= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = sql.executeQuery("select 价格 from 电影表 JOIN 排片表 ON 电影表.电影号=排片表.电影号 WHERE 电影表.电影名='"+name+"'AND 排片表.厅号='"+ting+"'");
            while (rs.next())
                jage =rs.getString(1);
            jageLable.setText(jage);
            //System.out.println(jage);
            con.close();
        }catch (SQLException e){}

    }
}