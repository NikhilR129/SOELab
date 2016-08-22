import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class DisplayAttendance {

    private JFrame frame;
    private JPanel panel;

    void run(Subject subject) {
        frame = new JFrame("Attendance of: " + subject.name);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BorderLayout());

        JLabel studentsLabel = new JLabel("<html>S<br>t<br>u<br>d<br>e<br>n<br>t<br>s");
        panel.add(studentsLabel, BorderLayout.WEST);

        JLabel subjectsLabel = new JLabel("Months", SwingConstants.CENTER);
        panel.add(subjectsLabel, BorderLayout.NORTH);

        JPanel centralPanel = new JPanel();
        centralPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        fillCentralPanel(subject, centralPanel);
        panel.add(centralPanel, BorderLayout.CENTER);

        setUpFrame();
    }

    void fillCentralPanel(Subject subject, JPanel centralPanel) {
        int rows = subject.studentsEnrolled.size();
        int cols = subject.studentsEnrolled.get(0).attendance.get(subject).size();
        centralPanel.setLayout(new GridLayout(rows, cols, 2, 2));
        for (Student student : subject.studentsEnrolled) {
            centralPanel.add(new JLabel(student.name));
            for (int i = 0; i < student.attendance.get(subject).size(); i++) {
                JLabel label = new JLabel("" + student.attendance.get(subject).get(i));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                centralPanel.add(label);
            }
        }
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    }


    void setUpFrame() {
        frame.add(panel);
        frame.setSize(500, 400);
        //frame.pack();
        frame.setVisible(true);
    }
}