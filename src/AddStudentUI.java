import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentUI extends JFrame{
    public static void main(String[] args) {
        JFrame temp=new JFrame();
        new  AddStudentUI(temp);
    }

    JFrame superJFarm;
    AddStudentUI(JFrame superJFarm){

        this.superJFarm=superJFarm;
        setTitle("新增学生");
        setBounds(0,0,1010,535);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(0);

        JLabel tip=new JLabel("填写学生信息");
        tip.setFont(new Font("楷体",Font.BOLD,30));
        tip.setBounds(380,20,200,50);

        JTextField studentNameField=new JTextField();
        JLabel studentNameTxt=new JLabel("姓名:");
        studentNameTxt.setFont(new Font("楷体",Font.BOLD,20));
        JTextField studentIDField=new JTextField();
        JLabel studentIDTxt=new JLabel("学号:");
        studentIDTxt.setFont(new Font("楷体",Font.BOLD,20));
        JTextField studentClassField=new JTextField();
        JLabel studentClassTxt=new JLabel("班级:");
        studentClassTxt.setFont(new Font("楷体",Font.BOLD,20));

        studentNameTxt.setBounds(370,100,70,60);
        studentNameField.setBounds(420,110,150,30);
        studentIDTxt.setBounds(370,150,70,60);
        studentIDField.setBounds(420,160,150,30);
        studentClassTxt.setBounds(370,200,70,60);
        studentClassField.setBounds(420,210,150,30);

        //关闭当前窗口,上级窗口不变的操作
        JButton jButton1=new JButton("取消");
        jButton1.setFont(new Font("楷体",Font.BOLD,20));
        jButton1.setBounds(520,300,100,50);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                superJFarm.setVisible(true);
            }
        });

        JButton jButton2=new JButton("确定");
        jButton2.setBounds(380,300,100,50);
        jButton2.setFont(new Font("楷体",Font.BOLD,20));
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Student student=new Student();

                if (isRepeat(studentIDField.getText())){
                    JOptionPane.showMessageDialog(null,"学号不可重复","提示",2);
                }
                else if (studentNameField.getText().isEmpty()||studentIDField.getText().isEmpty()||studentClassField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"信息不能为空","提示",2);
                }
                else if (studentIDField.getText().length()>=255){
                    JOptionPane.showMessageDialog(null,"输入错误，id过长","提示",2);
                }
                else if (studentNameField.getText().length()>=255){
                    JOptionPane.showMessageDialog(null,"输入错误，名字过长","提示",2);
                }
                else if (studentClassField.getText().length()>=255){
                    JOptionPane.showMessageDialog(null,"输入错误，班级过长","提示",2);
                }
                else {

                    student.setName(studentNameField.getText());
                    student.setId(studentIDField.getText());
                    student.setClas(studentClassField.getText());

                    //直接添加到数据库，没有到list中
                    dataBase.intoData(student.getId(),student.getName(),student.getClas());

                    JOptionPane.showMessageDialog(null,"添加成功","提示",3);

                    setVisible(false);
                    superJFarm.setVisible(true);
                }
            }
        });

        getContentPane().add(jButton1);
        getContentPane().add(jButton2);
        getContentPane().add(studentNameTxt);
        getContentPane().add(studentIDTxt);
        getContentPane().add(studentClassTxt);
        getContentPane().add(studentNameField);
        getContentPane().add(studentIDField);
        getContentPane().add(studentClassField);
        getContentPane().add(tip);

        setVisible(true);
    }

    //判断学号重复
    Boolean isRepeat(String id){

        boolean state=false;

        if (!Util.studentList.isEmpty()){
            for (int i = 0; i < Util.studentList.size(); i++) {
                if (Util.studentList.get(i).getId().equals(id)) {
                    state = true;
                    break;
                }
            }
        }
        return state;
    }

}
