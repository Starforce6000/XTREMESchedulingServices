package app;

import Models.*;
import javax.swing.*;

public class GeneratorDialog {
    JFrame frame;

    public GeneratorDialog(Department d) {
        frame = new JFrame("Generate New Schedule");

        frame.setVisible(true);
    }

    public static void main(String[] args){
        Department testDept = new Department();

        GeneratorDialog gd = new GeneratorDialog(testDept);
    }
}
