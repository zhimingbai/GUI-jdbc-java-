

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    public static void main(String[] args) throws Exception {
        new Login();
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    Login() {
        //设置窗口的基本操作
        this.setBounds(0, 0, 1000, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("白芷茗");

        JPanel jPanel = new JPanel();
        //JPanel将不再使用任何布局管理器来自动调整组件的位置和大小。
        //然后，我们可以手动添加和调整组件的位置和大小。
        jPanel.setBounds(0, 0, 1000, 800);
        jPanel.setLayout(null);


        //设置图片
        JLabel jLabel1 = new JLabel(new ImageIcon("src/imgs/p3.jpg"));
        jLabel1.setBounds(0, 0, 1000, 400);


        JLabel txt1 = new JLabel("用户名");
        JLabel txt2 = new JLabel("密码");
        txt1.setForeground(Color.black);
        txt2.setForeground(Color.black);
        txt1.setBounds(370, 395, 300, 50);
        txt2.setBounds(370, 440, 300, 50);
        txt1.setFont(new Font("楷体", Font.PLAIN, 25));
        txt2.setFont(new Font("楷体", Font.PLAIN, 25));

        JTextField userNameTxt = new JTextField("");
        userNameTxt.setForeground(Color.black);
        userNameTxt.setBounds(470, 400, 200, 40);
        userNameTxt.setFont(new Font("楷体", Font.BOLD, 20));
        JPasswordField userCodeTxt = new JPasswordField("");
        userCodeTxt.setBounds(470, 450, 200, 40);

        JButton jButton1 = new JButton("确定");
        jButton1.setFont(new Font("楷体", Font.BOLD, 18));
        jButton1.setBounds(520, 500, 100, 40);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/hsp_db02",
                            "root", "123456");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String sql01 = "select name,pwd from hsp_db02.admin where name =? and pwd=? ";

                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement(sql01);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String zh_String = userNameTxt.getText();
                String mm_String = userCodeTxt.getText();

                try {
                    preparedStatement.setString(1, zh_String);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    preparedStatement.setString(2, mm_String);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                ResultSet resultSet = null;
                try {
                    resultSet = preparedStatement.executeQuery();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if ("".equals(zh_String)) {
                    JOptionPane.showMessageDialog(null, "账号不能为空", "警告",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        if (resultSet.next()) {
                            System.out.println("登录成功");
                            JOptionPane.showMessageDialog(null, "登录成功", "提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new MainUI(Login.this);
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "用户名或者密码错误", "提示",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        jPanel.add(txt1);
        jPanel.add(txt2);
        jPanel.add(jButton1);
        jPanel.add(userNameTxt);
        jPanel.add(userCodeTxt);
        jPanel.add(jLabel1);

        add(jPanel);

        setVisible(true);
    }
}