import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter {
    private static final double BGN_TO_EUR = 0.51;
    private static final double BGN_TO_USD = 0.53;
    private static final double EUR_TO_BGN = 1.96;
    private static final double EUR_TO_USD = 1.03;
    private static final double USD_TO_BGN = 1.90;
    private static final double USD_TO_EUR = 0.97;
// All conversions for every currency

    public static double convert(String fromCurrency, String toCurrency, double amount) {
        double result = 0;
        if (fromCurrency.equals("BGN")) {
            if (toCurrency.equals("EUR")) {
                result = amount * BGN_TO_EUR;
            } else if (toCurrency.equals("USD")) {
                result = amount * BGN_TO_USD;
            }
            //From BGN to EUR/USD

        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("USD")) {
                result = amount * EUR_TO_USD;
            }
            else if (toCurrency.equals("BGN")) {
                result = amount * EUR_TO_BGN;
            }
            //From EUR to USD/BGN

        } else if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("EUR")) {
                result = amount * USD_TO_EUR;
            }
            else if (toCurrency.equals("BGN")) {
                result = amount * USD_TO_BGN;
            }
        }
        //From USD to EUR/BGN
        return result;
        //Give back the converted value
    }
}

class CurrencyConverterGUI extends JFrame {
    private JComboBox<String> fromCurrencyCombo;
    private JComboBox<String> toCurrencyCombo;
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        Font largeFont = new Font("Arial", Font.PLAIN, 25);  // Large font for components

        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setFont(largeFont);
        fromCurrencyCombo = new JComboBox<>(new String[] {"USD", "EUR", "BGN"});
        fromCurrencyCombo.setFont(largeFont);
        // Pick from which currency to convert

        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setFont(largeFont);
        toCurrencyCombo = new JComboBox<>(new String[] {"USD", "EUR", "BGN"});
        toCurrencyCombo.setFont(largeFont);
        // Pick to which currency to convert

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(largeFont);
        amountField = new JTextField(10);
        amountField.setFont(largeFont);
        // Pick the amount of money

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 25));
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(largeFont);
        //Convert button

        add(fromLabel);
        add(fromCurrencyCombo);
        add(toLabel);
        add(toCurrencyCombo);
        add(amountLabel);
        add(amountField);
        add(convertButton);
        add(resultLabel);
        // Add every element of the GUI

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }
    //Initiate the action of conversion

    private void convertCurrency() {

        String amountText = amountField.getText();
        //Amount as a String

        double amount = Double.parseDouble(amountText);
        //Amount as a double
        String fromCurrency = (String) fromCurrencyCombo.getSelectedItem();
        String toCurrency = (String) toCurrencyCombo.getSelectedItem();
        //Which are the currencies

        double result = CurrencyConverter.convert(fromCurrency, toCurrency, amount);
        resultLabel.setText("Result: " + result);
        //Gives the result

    }

    public static void main(String[] args) {

        new CurrencyConverterGUI().setVisible(true);
        // Set the GUI to be visible

    }
}
