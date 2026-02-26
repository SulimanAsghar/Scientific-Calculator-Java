import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame implements ActionListener {

    JTextField display;
    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    Color bgColor = new Color(25, 25, 25);
    Color btnColor = new Color(40, 40, 40);
    Color opColor = new Color(0, 170, 255);
    Color textColor = Color.WHITE;

    ScientificCalculator() {

        setTitle("Scientific Calculator");
        setSize(340, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        getContentPane().setBackground(bgColor);

        // Display
        display = new JTextField();
        display.setFont(new Font("Consolas", Font.BOLD, 28));
        display.setForeground(Color.GREEN);
        display.setBackground(Color.BLACK);
        display.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Panel
        JPanel panel = new JPanel(new GridLayout(6,4,10,10));
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        String[] buttons = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+",
                "sin","cos","tan","√",
                "x²","log","C","Exit"
        };

        for(String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setFocusPainted(false);
            btn.setForeground(textColor);
            btn.setBackground(isOperator(text) ? opColor : btnColor);
            btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("=") || s.equals("C");
    }

    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        try {

            if ((cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') || cmd.equals(".")) {
                display.setText(display.getText() + cmd);
            }

            else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/")) {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            }

            else if (cmd.equals("=")) {
                num2 = Double.parseDouble(display.getText());

                switch(operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num1 / num2; break;
                }

                display.setText(String.valueOf(result));
            }

            else if (cmd.equals("sin")) {
                result = Math.sin(Math.toRadians(Double.parseDouble(display.getText())));
                display.setText(String.valueOf(result));
            }

            else if (cmd.equals("cos")) {
                result = Math.cos(Math.toRadians(Double.parseDouble(display.getText())));
                display.setText(String.valueOf(result));
            }

            else if (cmd.equals("tan")) {
                result = Math.tan(Math.toRadians(Double.parseDouble(display.getText())));
                display.setText(String.valueOf(result));
            }

            else if (cmd.equals("√")) {
                result = Math.sqrt(Double.parseDouble(display.getText()));
                display.setText(String.valueOf(result));
            }

            else if (cmd.equals("x²")) {
                double v = Double.parseDouble(display.getText());
                display.setText(String.valueOf(v*v));
            }

            else if (cmd.equals("log")) {
                result = Math.log10(Double.parseDouble(display.getText()));
                display.setText(String.valueOf(result));
            }

            else if (cmd.equals("C")) {
                display.setText("");
            }

            else if (cmd.equals("Exit")) {
                System.exit(0);
            }

        } catch(Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}