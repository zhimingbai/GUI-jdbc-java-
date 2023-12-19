import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DetailedMassage extends JFrame {
    DetailedMassage(Student student){
        setTitle("详细信息");
        setBounds(0,0,600,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel jLabel1=new JLabel("详细信息");
        JLabel jLabel2=new JLabel("学生姓名：");
        JLabel jLabel3=new JLabel("学生ID：");
        JLabel jLabel4=new JLabel("学生班级：");

        jLabel1.setBounds(250,0,150,50);
        jLabel2.setBounds(0,50,150,50);
        jLabel3.setBounds(0,150,150,50);
        jLabel4.setBounds(0,250,150,50);
        jLabel1.setFont(new Font("楷体",Font.BOLD,20));
        jLabel2.setFont(new Font("楷体",Font.BOLD,20));
        jLabel3.setFont(new Font("楷体",Font.BOLD,20));
        jLabel4.setFont(new Font("楷体",Font.BOLD,20));

        JLabel jLabel5=new JLabel(student.getName());
        JLabel jLabel6=new JLabel(student.getId());
        JLabel jLabel7=new JLabel(student.getClas());
        jLabel5.setBounds(150,50,250,50);
        jLabel6.setBounds(150,150,250,50);
        jLabel7.setBounds(150,250,250,50);
        jLabel5.setFont(new Font("楷体",Font.BOLD,20));
        jLabel6.setFont(new Font("楷体",Font.BOLD,20));
        jLabel7.setFont(new Font("楷体",Font.BOLD,20));

        getContentPane().add(jLabel1);
        getContentPane().add(jLabel2);
        getContentPane().add(jLabel3);
        getContentPane().add(jLabel4);
        getContentPane().add(jLabel5);
        getContentPane().add(jLabel6);
        getContentPane().add(jLabel7);

        setVisible(true);
    }

}
