package xiaowu;

import xiaowu.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName Login.java
 */
public class Login extends JFrame implements ActionListener {
    public static boolean recommend = false;
    private JPanel pan = new JPanel();
    private JLabel namelab = new JLabel("��      ����");
    private JLabel passlab = new JLabel("��      �룺");
    private static JTextField nametext = new JTextField();
    private JPasswordField passtext = new JPasswordField();

    public JButton denglu = new JButton("��¼");
    public JButton zhuce = new JButton("ע��");
    public JButton updatepass = new JButton("��������");
    public JButton deleteuser = new JButton("ɾ���û�");
    public static String getNameTe(){
        String s = nametext.getText();
        return s;
    }

    public Login() {
        Font font = new Font("����", Font.BOLD, 12);
        super.setTitle("��¼ע�����");
        pan.setLayout(null);
        namelab.setBounds(20, 20, 60, 30);
        nametext.setBounds(90, 20, 140, 30);
        passlab.setBounds(20, 60, 60, 30);
        passtext.setBounds(90, 60, 140, 30);
        denglu.setBounds(30, 120, 90, 20);
        zhuce.setBounds(140, 120, 90, 20);
        updatepass.setBounds(30, 150, 90, 20);
        deleteuser.setBounds(140, 150, 90, 20);


        pan.add(namelab);
        pan.add(nametext);
        pan.add(passlab);
        pan.add(passtext);
        pan.add(denglu);
        pan.add(zhuce);
        pan.add(updatepass);
        pan.add(deleteuser);

        passtext.setFont(font);
        zhuce.setFont(font);
        updatepass.setFont(font);
        deleteuser.setFont(font);

        denglu.addActionListener(this);
        zhuce.addActionListener(this);
        updatepass.addActionListener(this);
        deleteuser.addActionListener(this);
        super.add(pan);
        super.setSize(300, 250);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == denglu) {
            denglu();
        } else if (arg0.getSource() == zhuce) {
            zhuce();
        } else if (arg0.getSource() == updatepass) {
            updatepass();
        } else if (arg0.getSource() == deleteuser) {
            deleteuser();
        }

    }

    public void denglu() {
        Jdbcs d = new Jdbcs();
        String username = nametext.getText();
        String password = passtext.getText();
        if (d.compare(username, password)) {
            if(d.isexist(username) == false){
                recommend = true;
                JOptionPane.showMessageDialog(null, "��¼�ɹ��������Ƽ�ҳ�档");
                new RecommendFrame();
                super.dispose();
            }else {
                new GameFrame();
                super.dispose();
            }

        }

        super.setVisible(false);
    }

    public void zhuce() {
        Jdbcs d = new Jdbcs();
        String username = nametext.getText();
        String password = passtext.getText();
        d.insert(username, password);
    }

    public void updatepass() {
        pan.setEnabled(false);
        final JFrame frame1 = new JFrame("��������");
        frame1.setSize(250, 200);
        JPanel updatepass = new JPanel();
        JLabel namelab1 = new JLabel("����");
        final JTextField nametext1 = new JTextField("" + nametext.getText());
        final JPasswordField passtext1 = new JPasswordField();
        final JPasswordField newpasstext = new JPasswordField();
        JLabel passlab1 = new JLabel("������");
        JLabel newpasslab = new JLabel("������");
        JButton ok = new JButton("ȷ��");
        JButton resert = new JButton("����");

        updatepass.setLayout(null);

        namelab1.setBounds(5, 5, 70, 20);
        nametext1.setBounds(80, 5, 120, 20);
        passlab1.setBounds(5, 30, 70, 20);
        passtext1.setBounds(80, 30, 120, 20);
        newpasslab.setBounds(5, 60, 70, 20);
        newpasstext.setBounds(80, 60, 120, 20);
        ok.setBounds(10, 110, 60, 20);
        resert.setBounds(90, 110, 60, 20);

        updatepass.add(namelab1);
        updatepass.add(nametext1);
        updatepass.add(passlab1);
        updatepass.add(passtext1);
        updatepass.add(newpasslab);
        updatepass.add(newpasstext);
        updatepass.add(ok);
        updatepass.add(resert);

        frame1.add(updatepass);
        frame1.setVisible(true);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Jdbcs d = new Jdbcs();
                String username = nametext.getText();
                String password1 = passtext.getText();
                String newpassword = newpasstext.getText();
                if (d.update(username, password1, newpassword)) {
                    frame1.dispose();
                }
            }
        });
        resert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                nametext.setText("");
                passtext.setText("");
            }
        });
    }

    public void deleteuser() {
        String username = nametext.getText();
        String password = passtext.getText();
        Jdbcs s = new Jdbcs();
        s.delete(username, password);
    }

    public static void main(String[] args) {
        new Login();

    }

}