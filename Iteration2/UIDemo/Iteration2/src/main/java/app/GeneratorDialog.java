package app;

import Models.*;
import javax.swing.*;
import java.awt.*;

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
