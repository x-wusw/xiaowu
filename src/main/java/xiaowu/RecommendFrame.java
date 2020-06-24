/*
 * Created by JFormDesigner on Mon Mar 23 15:45:07 CST 2020
 */

package xiaowu;

import xiaowu.GameFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RecommendFrame extends JFrame {

    public RecommendFrame() {
        super.setTitle("推荐页面");
        super.setVisible(true);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        initComboBox();
        getUsername();
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (e.getSource() == button1) {
            new GameFrame();
            super.dispose();

        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if(e.getSource() == button2){
            insert();
            new PlayGame();
            super.dispose();
        }
    }
    //将页面的信息存入数据库
    private void insert(){
        Jdbcs d = new Jdbcs();
        String username = textField1.getText();
        int age = Integer.parseInt(textField2.getText());
        String sex = comboBox2.getSelectedItem().toString();
        String career = comboBox3.getSelectedItem().toString();
        String articletypename = comboBox4.getSelectedItem().toString();
        d.insertdb(username,sex,age,career,articletypename);
    }

    //获取用户名
    private void getUsername(){
        String s = Login.getNameTe();
        textField1.setText(s);
        textField1.setEditable(false);
    }
    //为下拉框设置文本内容
    private void initComboBox(){
        String[] s1 = new String[]{"男","女"};
        String[] s3 = new String[]{"学生","老师","程序员","工人"};
        String[] s4 = new String[]{"科学","古诗","散文","天文"};
        for(String ss : s1){
            comboBox2.addItem(ss);
        }
        for(String ss : s3){
            comboBox3.addItem(ss);
        }
        for(String ss : s4){
            comboBox4.addItem(ss);
        }

    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void textField1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        textField1 = new JTextField();
        comboBox2 = new JComboBox();
        comboBox3 = new JComboBox();
        comboBox4 = new JComboBox();
        textField2 = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u8df3  \u8fc7");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1ActionPerformed(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(395, 20, 98, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("\u786e  \u5b9a");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2ActionPerformed(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(395, 395, 108, button2.getPreferredSize().height);

        //---- label1 ----
        label1.setText("\u804c              \u4e1a  \uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(70, 175), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u6027             \u522b   \uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(70, 130), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u5e74             \u9f84   \uff1a");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(70, 85), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u7231\u597d\u7684\u6587\u5b57\u7c7b\u578b \uff1a");
        contentPane.add(label4);
        label4.setBounds(70, 220, label4.getPreferredSize().width, 17);

        //---- label5 ----
        label5.setText("\u7528     \u6237     \u540d   \uff1a");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(70, 40), label5.getPreferredSize()));

        //---- textField1 ----
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1ActionPerformed(e);
                textField1ActionPerformed(e);
            }
        });
        contentPane.add(textField1);
        textField1.setBounds(175, 35, 100, textField1.getPreferredSize().height);
        contentPane.add(comboBox2);
        comboBox2.setBounds(175, 125, 150, comboBox2.getPreferredSize().height);
        contentPane.add(comboBox3);
        comboBox3.setBounds(175, 170, 150, comboBox3.getPreferredSize().height);
        contentPane.add(comboBox4);
        comboBox4.setBounds(175, 215, 150, comboBox4.getPreferredSize().height);
        contentPane.add(textField2);
        textField2.setBounds(175, 80, 100, 35);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField textField2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
