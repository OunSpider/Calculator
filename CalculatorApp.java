import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {

    private JTextField firstNumberTextField;
    private JTextField secondNumberTextField;
    private JTextField resultTextField;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton equalsButton;
    private JButton clearButton;
    private String selectedOperation;

    public CalculatorApp() {
        JLabel firstNumberLabel = new JLabel("First number: ");
        JLabel secondNumberLabel = new JLabel("Second number: ");
        JLabel resultLabel = new JLabel("Result: ");

        firstNumberTextField = new JTextField(10);
        secondNumberTextField = new JTextField(10);
        resultTextField = new JTextField(10);
        resultTextField.setEditable(false);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("Clear");

        addButton.addActionListener(new Operation());
        subtractButton.addActionListener(new Operation());
        multiplyButton.addActionListener(new Operation());
        divideButton.addActionListener(new Operation());
        equalsButton.addActionListener(new EqualAction());
        clearButton.addActionListener(new ClearAction());

        setLayout(new GridLayout(6, 2, 4, 4));
        add(firstNumberLabel);
        add(firstNumberTextField);
        add(secondNumberLabel);
        add(secondNumberTextField);
        add(resultLabel);
        add(resultTextField);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(equalsButton);
        add(clearButton);

        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private class Operation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            selectedOperation = source.getText();
        }
    }

    private class EqualAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double firstNum = Double.parseDouble(firstNumberTextField.getText());
                double secondNum = Double.parseDouble(secondNumberTextField.getText());
                double result = 0;

                switch (selectedOperation) {
                    case "+":
                        result = firstNum + secondNum;
                        break;
                    case "-":
                        result = firstNum - secondNum;
                        break;
                    case "*":
                        result = firstNum * secondNum;
                        break;
                    case "/":
                        if (secondNum != 0) {
                            result = firstNum / secondNum;
                        } else {
                            resultTextField.setText("Error: Division by zero");
                            return;
                        }
                        break;
                    default:
                        resultTextField.setText("Error: No operation selected");
                        return;
                }

                resultTextField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                resultTextField.setText("Error: Invalid input");
            }
        }
    }

    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            firstNumberTextField.setText("");
            secondNumberTextField.setText("");
            resultTextField.setText("");
            selectedOperation = null;
        }
    }
}