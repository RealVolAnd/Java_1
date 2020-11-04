package Homework_8;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWindow extends JDialog {

    public DialogWindow(JFrame owner) {
        super(owner, "About DialogTest", true);

        setBounds(750, 500, 300, 150);
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attribs, 18);
        StyleConstants.setBold(attribs, true);

        JTextPane jp1 = new JTextPane();
        jp1.setBackground(Color.WHITE);
        jp1.setParagraphAttributes(attribs, true);
        jp1.setText("\n\nИгра окончена!");
        jp1.setEditable(false);
        add(jp1);

        JButton ok = new JButton("Выйти");
        ok.setPreferredSize(new Dimension(120, 30));
        ok.addActionListener(new DialogActionListener(1));
        JButton ret = new JButton("Повторить");
        ret.setPreferredSize(new Dimension(120, 30));
        ret.addActionListener(new DialogActionListener(2));

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.add(ok);
        panel.add(ret);
        add(panel, BorderLayout.SOUTH);
    }

    class DialogActionListener implements ActionListener {

        int i;

        public DialogActionListener(int i) {
            this.i = i;
        }


        public void actionPerformed(ActionEvent e) {

            switch (i) {
                case 1:
                    System.exit(0);
                    break;
                case 2:
                    setVisible(false);
                    Game3.restartTheGame();

                    break;
                default:
                    break;
            }


        }
    }
}
