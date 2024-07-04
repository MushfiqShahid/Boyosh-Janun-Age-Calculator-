package Basic_JAVA;

//    *** The Code of the Age Calculator ***
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeCalculatorApp extends JFrame {
    private final JTextField birthDateField;
    private final JLabel resultLabel;

    public AgeCalculatorApp() {
        setTitle("Advanced Age Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel instructionLabel = new JLabel("Enter your birthdate (YYYY-MM-DD):");
        birthDateField = new JTextField(10);
        JButton calculateButton = new JButton("Calculate Age");
        resultLabel = new JLabel("");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAge();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(instructionLabel);
        panel.add(birthDateField);
        panel.add(calculateButton);

        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private void calculateAge() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthDateString = birthDateField.getText();

        try {
            Date birthDate = sdf.parse(birthDateString);
            Calendar birthDateCalendar = Calendar.getInstance();
            birthDateCalendar.setTime(birthDate);

            Calendar todayCalendar = Calendar.getInstance();
            int years = todayCalendar.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR);
            int months = todayCalendar.get(Calendar.MONTH) - birthDateCalendar.get(Calendar.MONTH);
            int days = todayCalendar.get(Calendar.DAY_OF_MONTH) - birthDateCalendar.get(Calendar.DAY_OF_MONTH);

            if (days < 0) {
                months--;
                days += birthDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }

            if (months < 0) {
                years--;
                months += 12;
            }

            resultLabel.setText("Your age is approximately " + years + " years, " + months + " months, and " + days + " days.");
        } catch (ParseException ex) {
            resultLabel.setText("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgeCalculatorApp app = new AgeCalculatorApp();
            app.setVisible(true);
        });
    }
}
