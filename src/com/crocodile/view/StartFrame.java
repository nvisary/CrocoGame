package com.crocodile.view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class StartFrame extends JFrame implements ActionListener {
    private JButton btn1;
    private JButton btn2;
    private ImageIcon imageIcon;
    private JLabel pic;
    private PicMsg picMsg;
    public StartFrame(String title) {
            super(title);

            picMsg = new PicMsg();
            picMsg.setSize(264, 150);
            picMsg.setLocation(740, 200);
            picMsg.setVisible(false);

            //Картинка
            imageIcon = new ImageIcon("res/croco.jpg");
            pic = new JLabel(imageIcon);
            pic.setSize(264, 121);
            pic.setLocation(740, 350);
            pic.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null,
                            "Игра крокодил.\nАвторы:\nТарасов Никита\nКудрявцева Екатерина",
                            "О нас",
                            JOptionPane.PLAIN_MESSAGE);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    picMsg.paintText("Привет!");
                    picMsg.setVisible(true);

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    picMsg.setVisible(false);
                }
            });

            //параметры окна
            setFrameParametrs();

            //создание кнопок
            btn1 = new JButton(Styles.btnStartServerText);
            btn2 = new JButton(Styles.btnStartClientText);
            btn1.addActionListener(this);
            btn2.addActionListener(this);

            //параметры кнопок
            setButtonsParametrs();



            //добавление кнопок на форму
            getContentPane().add(btn1);
            getContentPane().add(btn2);
            getContentPane().add(pic);
            getContentPane().add(picMsg);
    }

    private void setFrameParametrs() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Styles.widthWindow, Styles.heightWindow);
        setLayout(null);
        setLocation(Styles.xLocationFrame, Styles.yLocationFrame);
        setResizable(false);
        getContentPane().setBackground(Styles.backgroundFrameColor);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.getText().equals(Styles.btnStartServerText)) {
            //нажатие первой кнопки
            ServerFrame serverFrame = new ServerFrame();
            setVisible(false);
        } else {
            //нажатие второй кнопки
            ClientFrame clientFrame = new ClientFrame();
            setVisible(false);
        }
    }
    private void setButtonsParametrs() {
        btn1.setLocation(Styles.xLocationBtnStartServer, Styles.yLocationBtnStartServer);
        btn1.setSize(Styles.widthBtn, Styles.heightBtn);
        btn1.setBackground(Styles.buttonColor);
        btn1.setFont(Styles.buttonFont);
        btn1.setToolTipText("Нажми меня что бы создать игру");

        btn2.setLocation(Styles.xLocationBtnStartClient, Styles.yLocationBtnStartClient);
        btn2.setSize(Styles.widthBtn, Styles.heightBtn);
        btn2.setBackground(Styles.buttonColor);
        btn2.setFont(Styles.buttonFont);
        btn2.setToolTipText("Нажми меня что бы подключиться к игре");
    }

}
