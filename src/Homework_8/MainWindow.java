package Homework_8;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    JButton[] jbs = new JButton[9];
    DialogWindow dialog = new DialogWindow(this);
    private final String WND_TITLE = "Угадай число. Попыток осталось: ";

    public MainWindow() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 600, 700);
        setResizable(false);
        setTitle(WND_TITLE + Game3.tryCount);

        JPanel p = new JPanel();
        p.setToolTipText("Panel0");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        add(p);

        JPanel p1 = new JPanel();
        p1.setToolTipText("Panel1");
        p1.setBackground(Color.WHITE);
        p1.setPreferredSize(new Dimension(600, 100));
        // p1.setMinimumSize(new Dimension(600,100));
        p1.setMaximumSize(new Dimension(600, 100));

        JTextPane ta1 = new JTextPane();

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attribs, 18);
        StyleConstants.setBold(attribs, true);
        ta1.setParagraphAttributes(attribs, true);
        ta1.setText("Правила игры");

        ta1.setPreferredSize(new Dimension(600, 30));

        p1.add(ta1);

        JTextPane ta2 = new JTextPane();

        StyleConstants.setFontSize(attribs, 14);
        StyleConstants.setBold(attribs, false);
        ta2.setParagraphAttributes(attribs, true);
        ta2.setText("Загадано число от 0 до 9. Вам дается 3 попытки чтобы его угадать.");
        ta2.setPreferredSize(new Dimension(600, 50));
        p1.add(ta2);

        p.add(p1);

        Font font = new Font("Mono", Font.BOLD, 22);

        JPanel p2 = new JPanel();
        p2.setToolTipText("Panel2");
        p2.setSize(600, 600);
        p2.setBackground(Color.YELLOW);
        p2.setLayout(new GridLayout(3, 3));
        int i;

        for (i = 0; i < 9; i++) {
            jbs[i] = new JButton(String.valueOf(i + 1));
            jbs[i].setFont(font);
            jbs[i].addActionListener(new MyActionListener(i));
            jbs[i].setBackground(Color.LIGHT_GRAY);

            p2.add(jbs[i]);
        }
        p.add(p2);

    }


    public void resetButtons() {
        for (int i = 0; i < 9; i++) {
            jbs[i].setBackground(Color.LIGHT_GRAY);
            setTitle(WND_TITLE + Game3.tryCount);
        }
    }


    private class MyActionListener implements ActionListener {

        int i;

        public MyActionListener(int i) {
            this.i = i;
        }

        public void actionPerformed(ActionEvent e) {
            Game3.tryCount--;
            setTitle(WND_TITLE + Game3.tryCount);

            if (i == Game3.magicNumber) {
                jbs[i].setBackground(Color.GREEN);
                dialog.setVisible(true);
            } else {
                jbs[i].setBackground(Color.RED);
            }

            if (Game3.tryCount == 0) {
                dialog.setVisible(true);

            }

        }
    }

}
