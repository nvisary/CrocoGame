package com.crocodile.view;

import com.crocodile.model.Game;
import com.crocodile.io.Server;

import com.crocodile.model.Point;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.IntrospectionException;
import java.io.*;
import java.util.Scanner;


public class PaintPanelServer extends JPanel {
    private int x1, x2, y1, y2;
    private Graphics g;
    private Color color = Color.black;
    private Server server;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private Game game;
    private  MouseHandler mouseHandler;

    public PaintPanelServer(Game game) {
        super();
        server = game.getServer();
        this.game = game;
        setBackground(Color.white);
        setLayout(null);

        //добавление на панель кнопки очистить поле и обработчик этой кнопки + кнопки изменения цвета рисования
        addButtonsListener();

        mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);


    }
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    private void addButtonsListener() {
        JButton btnСlear = new JButton("Очистить поле");
        JButton btnRed = new JButton();
        JButton btnGreen = new JButton();
        JButton btnBlue = new JButton();
        JButton btnBlack = new JButton();
        JButton btnSave = new JButton("Save");
        JButton btnLoad = new JButton("Load");

        btnСlear.setSize(125, 25);
        btnСlear.setLocation(0, 0);

        btnBlack.setSize(25,25);
        btnBlack.setLocation(126, 0);
        btnBlack.setBackground(Color.black);

        btnRed.setSize(25, 25);
        btnRed.setLocation(151, 0);
        btnRed.setBackground(Color.red);

        btnGreen.setSize(25, 25);
        btnGreen.setLocation(176, 0);
        btnGreen.setBackground(Color.green);

        btnBlue.setSize(25, 25);
        btnBlue.setLocation(201, 0);
        btnBlue.setBackground(Color.blue);

        btnSave.setSize(125, 25);
        btnSave.setLocation(226, 0);

        btnLoad.setSize(100, 25);
        btnLoad.setLocation(351, 0);


        add(btnLoad);
        add(btnSave);
        add(btnСlear);
        add(btnBlack);
        add(btnRed);
        add(btnGreen);
        add(btnBlue);

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner in = null;
                try {
                    JFileChooser fileOpen = new JFileChooser();
                    fileOpen.setDialogType(JFileChooser.OPEN_DIALOG);
                    fileOpen.setCurrentDirectory(new File("C:/Users/Никита/Desktop/Saves"));
                    int ret = fileOpen.showDialog(null, "Открыть файл");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        in = new Scanner(fileOpen.getSelectedFile());
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                String num = "";
                while (in.hasNext()) {
                    String line = in.nextLine();

                    //System.out.println(line);
                    Point p = new Point();
                    for (int i = 2; i < line.length(); i++) {
                        char ch = line.charAt(i);
                        if (ch != ' ' && i != line.length() - 1) {
                            num += ch;
                            //System.out.println("num: " + Integer.parseInt(num));
                        } else {
                            if (i == line.length() - 1) {
                                num += line.charAt(i);
                            }
                            if (p.x1 == -1) {
                                p.x1 = Integer.parseInt(num);
                                System.out.print("x1:" + p.x1);
                                num = "";
                            } else if (p.y1 == -1) {
                                p.y1 = Integer.parseInt(num);
                                System.out.print("y1:" + p.y1);
                                num = "";
                            } else if (p.x2 == -1) {
                                p.x2 = Integer.parseInt(num);
                                num = "";
                                System.out.print("x2:" + p.x2);
                            } else if (p.y2 == -1) {
                                p.y2 = Integer.parseInt(num);
                                num = "";
                                System.out.print("y2:" + p.y2);
                            }
                        }
                    }
                    server.addPoint(p.x1, p.y1, p.x2, p.y2, color);
                }
                server.sendAllPoints();

            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileSave = new JFileChooser();
                    fileSave.setDialogType(JFileChooser.SAVE_DIALOG);
                    fileSave.setCurrentDirectory(new File("C:/Users/Никита/Desktop/Saves"));
                    int ret = fileSave.showDialog(null, "Создать файл");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileSave.getSelectedFile();
                        bufferedWriter = new BufferedWriter(new FileWriter(file));
                        for (int i = 0; i < game.getState().getPoints().size(); i++) {
                            x1 = game.getState().getPoints().get(i).x1;
                            y1 = game.getState().getPoints().get(i).y1;
                            x2 = game.getState().getPoints().get(i).x2;
                            y2 = game.getState().getPoints().get(i).y2;
                            bufferedWriter.write("p:" + x1 + " " + y1 + " " + x2 + " " + y2 + "\n");
                        }

                        bufferedWriter.close();
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnСlear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                game.getState().getPoints().clear();
                server.sendClearPaintPanel();
            }
        });

        btnBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.black;
            }
        });

        btnBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.blue;
            }
        });

        btnRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.red;
            }
        });

        btnGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.green;
            }
        });
    }


   private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (server.isServerRun()) {
                x1 = e.getX();
                y1 = e.getY();

                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(color);
                g2.setStroke(new BasicStroke((float)3));

                g2.drawLine(x1, y1, x1, y1);
                server.addPoint(x1, y1, x2, y2, color);
            }

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (server.isServerRun()) {
                x1 = e.getX();
                y1 = e.getY();

                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(color);
                g2.setStroke(new BasicStroke((float)3));
               /* try {
                    bufferedWriter.write("p:" + x1 + " "+ y1 + " " + x2+ " " + y2 + "\n");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/
                g2.drawLine(x1, y1, x2, y2);

                server.addPoint(x1, y1, x2, y2, color);
                x2 = x1;
                y2 = y1;
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (server.isServerRun()) {
                server.sendAllPoints();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (server.isServerRun()) {
                x1 = e.getX();
                y1 = e.getY();

                g = getGraphics();

                x2 = x1;
                y2 = y1;
            }
        }
    }
}