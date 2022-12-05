package app;

import Models.*;

import javax.annotation.processing.SupportedSourceVersion;
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

                String daysSplit[] = daysF.getText().replace(" ", "").split(",");
                String shiftsSplit[] = shiftF.getText().replace(" ", "").split(",");

                for(String string: daysSplit) {
                    if(string.equalsIgnoreCase("M")) {
                        if(!days.contains(Day.MONDAY)) {
                            days.add(Day.MONDAY);
                        }
                    } else if(string.equalsIgnoreCase("T")) {
                        if(!days.contains(Day.THURSDAY)) {
                            days.add(Day.TUESDAY);
                        }
                    } else if(string.equalsIgnoreCase("W")) {
                        if(!days.contains(Day.WEDNESDAY)) {
                            days.add(Day.WEDNESDAY);
                        }
                    } else if(string.equalsIgnoreCase("TR")) {
                        if(!days.contains(Day.THURSDAY)) {
                            days.add(Day.THURSDAY);
                        }
                    } else if(string.equalsIgnoreCase("F")) {
                        if(!days.contains(Day.FRIDAY)) {
                            days.add(Day.FRIDAY);
                        }
                    }
                }
                for(String string: shiftsSplit) {
                    if (string.equalsIgnoreCase("night")){
                        if(!shifts.contains(Shift.Night)) {
                            shifts.add(Shift.Night);
                        }
                    } else if (string.equalsIgnoreCase("swing")) {
                        if(!shifts.contains(Shift.Swing)) {
                            shifts.add(Shift.Swing);
                        }
                    } else if(string.equalsIgnoreCase("day")) {
                        if(!shifts.contains(Shift.Day)) {
                            shifts.add(Shift.Day);
                        }
                    }
                }

                

                frame.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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
