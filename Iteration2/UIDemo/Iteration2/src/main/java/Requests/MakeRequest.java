package Requests;

import Enums.Day;
import Enums.RequestType;
import Enums.Shift;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class MakeRequest extends JFrame {
    Object[] days = {Day.SELECT, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY,
            Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};
    RequestType[] reqs = {RequestType.SELECT, RequestType.RESCHEDULE, RequestType.SWAP,
            RequestType.OVERTIME, RequestType.PTO};
    Shift[] shifts = {Shift.SELECT, Shift.Day, Shift.Night, Shift.Swing};

    RequestHandler handler;

    RequestType type;
    Day when;
    Shift time;

    JLabel reqType, day, shift, reason;

    JTextField reasonField;


    public void initRequest() {
        JFrame makeReq = new JFrame("Make Request");
        makeReq.setLayout(new GridLayout(2,2));
        makeReq.setSize(500,200);

        reqType = new JLabel("What type of request?");

        JComboBox typeBox = new JComboBox(reqs);
        //typeBox.setSelectedIndex(0);
        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                type = (RequestType) cb.getSelectedItem();
            }
        });

        makeReq.add(reqType);
        makeReq.add(typeBox);

        makeReq.setVisible(true);

        JButton cancel = new JButton("Cancel");
        JButton confirm = new JButton("Confirm");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeReq.setVisible(false);
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler = new RequestHandler();
                makeReq.setVisible(false);
                getReqDetails();
            }
        });

        makeReq.add(cancel);
        makeReq.add(confirm);
    }

    public void getReqDetails() {
        JFrame reqInfo = new JFrame("Enter Request Details");
        reqInfo.setLayout(new GridLayout(4,2));
        reqInfo.setSize(500,200);

        day = new JLabel("Day");
        shift = new JLabel("Shift");
        reason = new JLabel("Reason");

        reasonField = new JTextField();

        JComboBox dayBox = new JComboBox(days);
        //dayBox.setSelectedIndex(0);
        dayBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                when = (Day) cb.getSelectedItem();
            }
        });

        JComboBox shiftBox = new JComboBox(shifts);
        //shiftBox.setSelectedIndex(0);
        shiftBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                time = (Shift) cb.getSelectedItem();
            }
        });

        JButton cancel = new JButton("Cancel");
        JButton confirm = new JButton("Confirm");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reqInfo.setVisible(false);
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.setWorkdays(when);
                handler.setHours(time);
                handler.setReason(reasonField.getText());
                reqInfo.setVisible(false);
            }
        });

        reqInfo.add(day);
        reqInfo.add(dayBox);

        reqInfo.add(shift);
        reqInfo.add(shiftBox);

        reqInfo.add(reason);
        reqInfo.add(reasonField);

        reqInfo.add(cancel);
        reqInfo.add(confirm);

        reqInfo.setVisible(true);
    }
}

