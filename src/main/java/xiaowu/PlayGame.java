/*
 * Created by JFormDesigner on Sun Mar 22 21:30:19 CST 2020
 */

package xiaowu;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Brainrain
 */
public class PlayGame extends JFrame {
    /*public static void main(String[] args) {
        new PlayGame();
    }*/
    //������ʱʱ��
    private static double oldtime;
    //��ȷ��������
    private int sum;
    //������ȷ�ʺ��ٶ�
    private static double accuracy = 0;
    private static int sudu;
    //ʱ����
    private static int hour;
    private static int mintue;
    private static int second;
    DecimalFormat df = new DecimalFormat("######0.00");
    Font font = new Font("����", Font.BOLD, 12);
    Jdbcs jdbcs = new Jdbcs();
    Random r = new Random();
    public PlayGame() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("��Ϸ����");
        super.setVisible(true);
        super.setResizable(false);
        initComponents();
        setText1();
    }
    //���ɼ��������ݿ�
    private void insert1(){
        String username = Login.getNameTe();
        int speed = sudu;
        accuracy = Double.parseDouble(df.format(accuracy));
        jdbcs.ginsertdb(username,speed,accuracy);
    }
    private void insert2(){
        String username = Login.getNameTe();
        String reg = "[^\u4e00-\u9fa5]";//ֻȡ���Ĳ���
        String topic = GameFrame.getComItem().replaceAll(reg,"");
        jdbcs.uainsert(username,topic);
    }
    //����һ���̣߳����textpane2ʵʱ�����ı�
    private void minitorTP2(){
        Thread t = new Thread(new Runnable() {
            public void run() {
                String output = textPane1.getText();
                String input = textPane2.getText();
                double len = input.length();
                sum = 0;
                if (input.length() >= output.length()){
                    JOptionPane.showMessageDialog(null,"�Ѵ��������֣���Ϸ���������������档");
                    new GameFrame();
                    PlayGame.super.setVisible(false);
                }
                else {
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) == output.charAt(i)) {
                            sum += 1;
                            accuracy = (sum / len) * 100;
                        }
                    }
                    synchronized (this) {
                        textField2.setText(String.valueOf("  " + input.length()) + "��");
                        textField3.setText(String.valueOf("  " + sum) + "��");
                        if (len > 0) {
                            textField5.setText(df.format(accuracy) + "%");
                        } else {
                            textField5.setText(String.valueOf(0) + "%");
                        }

                    }
                }
            }
        });
        t.start();
    }
    //����
    private void button1ActionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            new GameFrame();
            super.dispose();
        }
    }

    //������ʱ��
    private void startThread(){
            Thread t = new Thread(new Runnable() {
                public void run() {
                    time();
                }
            });
            t.start();

    }
    //��ʱ��
    private synchronized void time(){
        hour = 0;
        mintue = 0;
        second = 0;
        for (int i = 0; i < 60 * 60 * 24; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            second += 1;
            if (second == 60) {
                second = 0;
                mintue += 1;
            }
            if (mintue == 60) {
                mintue = 0;
                hour += 1;
            }
            String hms = hour + " : " + mintue + " : " + second;
            textField1.setFont(font);
            textField1.setText(hms);
            int len = textPane2.getText().length();
            if(mintue > 0){
                textField4.setText(String.valueOf(len/mintue)+"��/��");
                sudu = len/mintue;;
            }else {
                textField4.setText(String.valueOf(len)+"��/��");
                sudu = len;
            }
        }

    }
    //����textPane1�ı�
    private void setText1(){
        String s = null;
        if(Login.recommend==true){
            int id = r.nextInt(8)+1;
            s = jdbcs.ref(id);
        }else {
            String reg = "[^\u4e00-\u9fa5]";//ֻȡ���Ĳ���
            String topic = GameFrame.getComItem().replaceAll(reg,"");
            s = jdbcs.fangru(topic);
        }
        textPane1.setText(s);
        textPane1.setEnabled(false);
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);
        textField5.setEditable(false);
        textPane2.setEditable(false);
        button3.setEnabled(false);
    }
    private void textPane2CaretPositionChanged(InputMethodEvent e) {
        // TODO add your code here
    }

    private void textPane2CaretUpdate(CaretEvent e) {
        if(e.getSource() == textPane2){
            minitorTP2();
        }
    }
    //��ʼ��ť
    private void button2ActionPerformed(ActionEvent e) {
        if(e.getSource() == button2){
            String reg = "[^\u4e00-\u9fa5]";//ֻȡ���Ĳ���
            String nD = GameFrame.getComItem1().replaceAll(reg,"");
            if(nD == "�Ѷ�ѡ��"){
                startThread();
            }
            else {
                JOptionPane.showMessageDialog(null,"��ս��ʼ��");
                startThread();
            }
            textPane2.setEditable(true);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(true);
        }
    }
    //������ť
    private void button3ActionPerformed(ActionEvent e) {
        if(e.getSource() == button3){
            String reg = "[^\u4e00-\u9fa5]";//ֻȡ���Ĳ���
            String nD = GameFrame.getComItem1().replaceAll(reg,"");
            if(nD != "�Ѷ�ѡ��"){
                if(accuracy>=70.0 && sudu >= 15){
                    JOptionPane.showMessageDialog(null,"��ϲ�㣬��ս�ɹ������������档");
                }
                else JOptionPane.showMessageDialog(null,"���ź�����սʧ�ܣ����������档");
            }
            insert1();
            insert2();
            new GameFrame();
            super.setVisible(false);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();
        scrollPane2 = new JScrollPane();
        textPane2 = new JTextPane();
        button1 = new JButton();
        label4 = new JLabel();
        label5 = new JLabel();
        textField4 = new JTextField();
        textField5 = new JTextField();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u65f6  \u95f4\uff1a");
        contentPane.add(label1);
        label1.setBounds(150, 10, label1.getPreferredSize().width, 30);
        contentPane.add(textField1);
        textField1.setBounds(195, 5, 115, 40);

        //---- label2 ----
        label2.setText("\u5df2\u6253\u5b57\u6570\uff1a");
        contentPane.add(label2);
        label2.setBounds(310, 10, label2.getPreferredSize().width, 30);
        contentPane.add(textField2);
        textField2.setBounds(375, 5, 120, 40);

        //---- label3 ----
        label3.setText("\u6b63\u786e\u5b57\u6570\uff1a");
        contentPane.add(label3);
        label3.setBounds(505, 10, label3.getPreferredSize().width, 30);
        contentPane.add(textField3);
        textField3.setBounds(565, 5, 115, 40);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textPane1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 60, 680, 205);

        //======== scrollPane2 ========
        {

            //---- textPane2 ----
            textPane2.addInputMethodListener(new InputMethodListener() {
                public void caretPositionChanged(InputMethodEvent e) {
                    textPane2CaretPositionChanged(e);
                }
                public void inputMethodTextChanged(InputMethodEvent e) {}
            });
            textPane2.addCaretListener(new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    textPane2CaretUpdate(e);
                }
            });
            scrollPane2.setViewportView(textPane2);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(0, 280, 680, 220);

        //---- button1 ----
        button1.setText("\u8fd4  \u56de");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1ActionPerformed(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(710, 5, 88, 40);

        //---- label4 ----
        label4.setText("     \u6253\u5b57\u901f\u5ea6 \uff1a");
        contentPane.add(label4);
        label4.setBounds(695, 50, 125, 35);

        //---- label5 ----
        label5.setText("\u6b63 \u786e \u7387 \uff1a");
        contentPane.add(label5);
        label5.setBounds(715, 135, 75, 25);
        contentPane.add(textField4);
        textField4.setBounds(710, 85, 90, 35);
        contentPane.add(textField5);
        textField5.setBounds(710, 165, 90, 35);

        //---- button2 ----
        button2.setText("\u5f00\u59cb");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button2ActionPerformed(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(0, 5, 70, 40);

        //---- button3 ----
        button3.setText("\u7ed3\u675f");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button3ActionPerformed(e);
            }
        });
        contentPane.add(button3);
        button3.setBounds(75, 5, 70, 40);

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
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    private JScrollPane scrollPane2;
    private JTextPane textPane2;
    private JButton button1;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
