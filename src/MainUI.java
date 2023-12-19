import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainUI {
    public MainUI(JFrame superJFrame){
        JFrame m1=new JFrame();
        m1.setTitle("主面板");
        m1.setBounds(0,0,1010,535);
        m1.setLayout(null);
        m1.setLocationRelativeTo(null);
        m1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,1000);
        jPanel1.setLayout(null);

        JList<String> jlist =new JList<>();
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setFont(new Font("楷体",Font.BOLD,24));


        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(140,100,600,260);
        scrollPane.setViewportView(jlist);

        String[] strList=new String[20];

        strList[0]=" ";//注意这个strList[]，这是显示jList表值
        jlist.setListData(strList);
        for (int i = 1; i < strList.length; i++) {
            strList[i]=" ";
        }

        JButton jButton1=new JButton("返回");
        jButton1.setBounds(820,400,150,80);
        jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                m1.setVisible(false);
                superJFrame.setVisible(true);
            }
        });

        JButton jButton2=new JButton("新增");
        jButton2.setBounds(150,400,100,50);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //list清空
                Util.studentList.clear();
                //往list里载入数据
                try {
                    dataBase.loadData(Util.studentList);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                //跳转页面
                new AddStudentUI(m1);
                m1.setVisible(false);
            }
        });

        JButton jButton3=new JButton("删除");
        jButton3.setBounds(550,400,100,50);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jlist.getSelectedValue().equals(" ")){
                    JOptionPane.showMessageDialog(null,"空的选项！","提示",2);
                } else {
                    try {
                        dataBase.deleteData(jlist.getSelectedValue());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        JButton jButton4=new JButton("点击查看");
        jButton4.setBounds(800,120,100,50);
            jButton4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jlist.getSelectedValue().equals(" ")){
                        JOptionPane.showMessageDialog(null,"空的选项！","提示",2);
                    }else {
                        try {
                            new DetailedMassage(dataBase.queryData(jlist.getSelectedValue()));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });

        JButton jButton6=new JButton("清空");
        jButton6.setBounds(800,195,100,50);
        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataBase.clearData();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                Util.studentList.clear();
                Arrays.fill(strList, " ");


                m1.repaint();

            }
        });

        JButton jButton7=new JButton("刷新");
        jButton7.setBounds(800,270,100,50);
        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //两者清空
                Util.studentList.clear();
                Arrays.fill(strList," ");

                //往list里载入数据
                try {
                    dataBase.loadData(Util.studentList);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                //往jlist里填数据
                for (int i = 0; i <Util.studentList.size(); i++) {
                    if (Util.studentList.get(i)!=null){
                        strList[i]=Util.studentList.get(i).getId();
                    }
                }
                m1.repaint();

                //看看现在的集合怎么样
                System.out.println(Util.studentList);
            }
        });

        JLabel jLabel1=new JLabel("当前所有学生ID信息：");
        jLabel1.setFont(new Font("楷体",Font.BOLD,35));
        jLabel1.setBounds(300,20,400,50);

        m1.add(jPanel1);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        jPanel1.add(jButton3);
        jPanel1.add(jButton4);
        jPanel1.add(jButton6);
        jPanel1.add(jButton7);//更新按钮，待定
        jPanel1.add(jLabel1);
        jPanel1.add(scrollPane);
        m1.setVisible(true);
    }
}
