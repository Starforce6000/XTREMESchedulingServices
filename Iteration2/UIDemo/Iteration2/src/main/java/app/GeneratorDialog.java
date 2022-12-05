package app;

import Models.*;
import javax.swing.*;
import java.awt.*;
import Enums.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class GeneratorDialog {
    JFrame frame;

    public GeneratorDialog(Department d) {
        frame = new JFrame(d.getName() + ": Generate New Schedule");

        frame.setLayout(new GridLayout(4,2));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel perShiftL = new JLabel("Employees/shift");
        perShiftL.setPreferredSize(new Dimension(200,50));
        JLabel daysL = new JLabel("Days (separated by comma)");
        JLabel shiftL = new JLabel("Shifts (separated by coma)");
        JTextField perShiftF = new JTextField("Any");
        JTextField daysF = new JTextField("M,T,W,TR,F");
        JTextField shiftF = new JTextField("Day,Swing,Night");
        JButton create = new JButton("Create");
        JButton cancel = new JButton("Cancel");

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int perShift = 0;
                List<Day> days = new ArrayList<>();
                List<Shift> shifts = new ArrayList<>();

                try {
                    perShift = Integer.parseInt(perShiftF.getText());
                    perShift = Integer.max(0, perShift);
                } catch(NumberFormatException ex) {
                    perShift = 0;
                }

                String daysSplit[] = daysF.getText().split(",");
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.add(perShiftL);
        frame.add(perShiftF);
        frame.add(daysL);
        frame.add(daysF);
        frame.add(shiftL);
        frame.add(shiftF);
        frame.add(create);
        frame.add(cancel);

        frame.pack();
        frame.setVisible(true);
    }
}
