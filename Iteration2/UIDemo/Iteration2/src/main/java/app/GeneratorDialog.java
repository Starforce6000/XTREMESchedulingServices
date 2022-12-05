package app;

import Models.*;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import java.awt.*;
import Enums.*;
import Schedule.*;
import Schedule.Schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class GeneratorDialog {
    JFrame frame;
    Schedule newSchedule;

    public GeneratorDialog(Department d) {
        frame = new JFrame(d.getName() + ": Generate New Schedule");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4,2));
        JTextField scheduleName = new JTextField("Unnamed Schedule");

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
                List<Employee> ignored = new ArrayList<>();

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

                if(scheduleName.getText().equals("")) {
                    scheduleName.setText("Unnammed Schedule");
                }

                ScheduleGenerator generator = new ScheduleGenerator();
                newSchedule = generator.generateSchedule(scheduleName.getText(), d, perShift, days, shifts, ignored);
                d.addSchedule(newSchedule);

                frame.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        inputPanel.add(perShiftL);
        inputPanel.add(perShiftF);
        inputPanel.add(daysL);
        inputPanel.add(daysF);
        inputPanel.add(shiftL);
        inputPanel.add(shiftF);
        inputPanel.add(create);
        inputPanel.add(cancel);

        frame.add(scheduleName,BorderLayout.NORTH);
        frame.add(inputPanel,BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}
