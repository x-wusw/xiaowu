/*
 * Created by JFormDesigner on Sat Apr 25 14:50:55 CST 2020
 */

package xiaowu;

import view.XYLine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class GameFrame extends JFrame {
    Jdbcs d = new Jdbcs();
    String[] listData1 = new String[]{"1.简单", "2.一般", "3.中等", "4.困难"};
    //设置字体大小
    Font font = new Font("宋体", Font.BOLD, 16);

    public static void main(String[] args) {
        new GameFrame();
    }
    public GameFrame() {
        super.setTitle("游戏界面");
        super.setVisible(true);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setResizable(false);
        initComponents();
        setFont();
        comText();
        //setLabel1();
    }
    /*public void setLabel1(){
        //加载图片
        ImageIcon icon=new ImageIcon("E:\\IDEAProject\\xiaowu\\src\\main\\resources\\lib\\2.jpg");
        //将图片放入label中
        label1 = new JLabel(icon);
        super.getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
        //获取frame的顶层容器,并设置为透明
        JPanel j=(JPanel)super.getContentPane();
        j.setOpaque(false);
    }*/
    //给下拉框赋值
    public void comText(){
        String[] listDate = d.getText();
        comboBox1.addItem("内容选择");
        comboBox2.addItem("难度选择");
        for (int i = 0; i < listDate.length; i++) {
            Object item = listDate[i];
            comboBox1.addItem(i+1+"."+item);
        }
        for (int i = 0; i < listData1.length; i++) {
            comboBox2.addItem(listData1[i]);
        }
    }
    //给组件设置字体格式
    public void setFont(){
        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        comboBox1.setFont(font);
        comboBox2.setFont(font);
    }
    //得到内容下拉框选择的item
    public static String getComItem(){
        String s = (String) comboBox1.getSelectedItem();
        return s;
    }
    //得到难度下拉框选择的item
    public static String getComItem1(){
        String s = (String) comboBox2.getSelectedItem();
        return s;
    }
    private void button1ActionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            new PlayGame();
            super.dispose();
        }
    }

    private void button3ActionPerformed(ActionEvent e) {
        if(e.getSource() == button3){
            new Login();
            super.dispose();
        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        if(e.getSource() == button2){
            new XYLine();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(comboBox1);
        comboBox1.setBounds(30, 80, 170, 35);
        contentPane.add(comboBox2);
        comboBox2.setBounds(230, 80, 160, 35);

        //---- button1 ----
        button1.setText("\u5f00\u59cb\u6e38\u620f");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1ActionPerformed(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(30, 10, 130, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("\u5386\u53f2\u6210\u7ee9");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2ActionPerformed(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(350, 10, 132, button2.getPreferredSize().height);

        //---- button3 ----
        button3.setText("\u8fd4  \u56de");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3ActionPerformed(e);
            }
        });
        contentPane.add(button3);
        button3.setBounds(350, 260, 140, button3.getPreferredSize().height);

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
    private static JComboBox comboBox1;
    private static JComboBox comboBox2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
