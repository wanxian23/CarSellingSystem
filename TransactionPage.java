import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionPage {

    private String nameUser;
    private String emailUser;
    private String phoneUser;
    private String addressFirstHalfUser;
    private String addressSecondHalfUser;
    private String paymentMethod;

    private String carCategoryChoose;
    private String carModelChoose;
    private String variantChoose;
    private double basePrice;
    private String colorChoose;
    private int motorPowerChoose;

    private double downPayment;
    private int roadTax;
    private double monthlyInstalmentEMI;
    private double totalAmount;

    String[] deliveryLabels = {"Name:","Phone Number:", "Street:", "City:", "State:", "Postal Code:"};
    JTextField[] textField1 = new JTextField[5];

    JComboBox<String> stateComboBox;

    String[] cardLabels = {"Cardholder Name:", "Card Number:", "Expiry Date (MM/YY):", "CVV:", "Billing Address (Optional):"};
    JTextField monthTField = new JTextField();
    JTextField yearTField = new JTextField();
    JTextField[] textField2 = new JTextField[4];

    private JFrame frameTransactionPage = new JFrame("EcoMotion Transaction Page");

    public void createTransactionPage(String emailUser, String carCategoryChoose, String carModelChoose,
                                      String variantChoose, double basePrice, String colorChoose, int motorPowerChoose, double downPayment,
                                      int roadTax, double monthlyInstalmentEMI, double totalAmount) {

        this.emailUser= emailUser;

        this.carCategoryChoose = carCategoryChoose;
        this.carModelChoose = carModelChoose;
        this.variantChoose = variantChoose;
        this.basePrice = basePrice;
        this.motorPowerChoose = motorPowerChoose;
        this.colorChoose = colorChoose;

        this.downPayment = downPayment;
        this.roadTax = roadTax;
        this.monthlyInstalmentEMI = monthlyInstalmentEMI;
        this.totalAmount = totalAmount;

        frameTransactionPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTransactionPage.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        transactionPanel.setBackground(Color.WHITE);
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Padding around the panel

        JLabel transMethod = new JLabel("Transaction Method");
        transMethod.setFont(new Font("Century Gothic", Font.BOLD, 36));
        transMethod.setAlignmentX(Component.CENTER_ALIGNMENT);
        transactionPanel.add(transMethod);

        JPanel radioBPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        radioBPanel.setBackground(Color.WHITE);

        JRadioButton chequeRB = new JRadioButton("CHEQUE");
        chequeRB.setFont(new Font("Century Gothic", Font.BOLD, 24));
        chequeRB.setBackground(Color.WHITE);

        JRadioButton cardRB = new JRadioButton("CARD");
        cardRB.setFont(new Font("Century Gothic", Font.BOLD, 24));
        cardRB.setBackground(Color.WHITE);

        ButtonGroup groupTransB = new ButtonGroup();
        groupTransB.add(chequeRB);
        groupTransB.add(cardRB);

        radioBPanel.add(chequeRB);
        radioBPanel.add(cardRB);
        transactionPanel.add(Box.createVerticalStrut(30));
        transactionPanel.add(radioBPanel);

        JPanel deliveryDetailsPanel = new JPanel(new GridBagLayout());
        TitledBorder deliveryDetailsTtile = BorderFactory.createTitledBorder("Delivery Details");
        deliveryDetailsTtile.setTitleFont(new Font("Arial", Font.BOLD, 20));
        deliveryDetailsPanel.setBorder(deliveryDetailsTtile);
        deliveryDetailsPanel.setFont(new Font("Arial", Font.BOLD, 20));
        deliveryDetailsPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension labelDimension = new Dimension(260, 30);
        Dimension textFieldDimension = new Dimension(300, 30);

        for (int i = 0; i < deliveryLabels.length; i++) {
            JLabel label = new JLabel(deliveryLabels[i]);
            label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            label.setPreferredSize(labelDimension);
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            deliveryDetailsPanel.add(label, gbc);

            if (i == 4) {
                String[] states = {
                        "Select your state", "Johor", "Kedah", "Kelantan", "Kuala Lumpur",
                        "Labuan", "Malacca", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis",
                        "Putrajaya", "Sabah", "Sarawak", "Selangor", "Terengganu"
                };
                stateComboBox = new JComboBox<>(states);
                stateComboBox.setPreferredSize(textFieldDimension);
                stateComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 15));
                gbc.gridx = 1;
                deliveryDetailsPanel.add(stateComboBox, gbc);

                textField1[4] = new JTextField();
                textField1[4].setPreferredSize(textFieldDimension);
                gbc.gridx = 1;
                gbc.gridy = 1 + i;
                deliveryDetailsPanel.add(textField1[4], gbc);

            } else if (i < 4) {

                textField1[i] = new JTextField();
                textField1[i].setPreferredSize(textFieldDimension);
                gbc.gridx = 1;
                deliveryDetailsPanel.add(textField1[i], gbc);
            }
        }

        textField1[0].requestFocusInWindow();

        transactionPanel.add(Box.createVerticalStrut(30));
        transactionPanel.add(deliveryDetailsPanel);

        JPanel cardDetailsPanel = new JPanel(new GridBagLayout());
        TitledBorder carDetailsTtile = BorderFactory.createTitledBorder("Card Details");
        carDetailsTtile.setTitleFont(new Font("Arial", Font.BOLD, 20));
        cardDetailsPanel.setBorder(carDetailsTtile);
        cardDetailsPanel.setBackground(Color.WHITE);
        cardDetailsPanel.setVisible(false);

        for (int i = 0; i < cardLabels.length; i++) {
            JLabel label = new JLabel(cardLabels[i]);
            label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            label.setPreferredSize(labelDimension);
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            cardDetailsPanel.add(label, gbc);

            if (cardLabels[i].equals("Expiry Date (MM/YY):")) {

                JPanel expiryDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                expiryDatePanel.setBackground(Color.WHITE);

                monthTField.setPreferredSize(new Dimension(50, 30));
                monthTField.setFont(new Font("Century Gothic", Font.PLAIN, 15));
                monthTField.setHorizontalAlignment(JTextField.CENTER);

                JLabel slashLabel = new JLabel("/");
                slashLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));

                yearTField.setPreferredSize(new Dimension(50, 30));
                yearTField.setFont(new Font("Century Gothic", Font.PLAIN, 15));
                yearTField.setHorizontalAlignment(JTextField.CENTER);

                expiryDatePanel.add(monthTField);
                expiryDatePanel.add(slashLabel);
                expiryDatePanel.add(yearTField);

                gbc.gridx = 1;
                cardDetailsPanel.add(expiryDatePanel, gbc);

                textField2[i] = new JTextField();
                textField2[i].setPreferredSize(textFieldDimension);
                gbc.gridx = 1;
                gbc.gridy = 1 + i;
                cardDetailsPanel.add(textField2[i], gbc);

            } else if ( i < cardLabels.length - 2) {
                textField2[i] = new JTextField();
                textField2[i].setPreferredSize(textFieldDimension);
                gbc.gridx = 1;
                cardDetailsPanel.add(textField2[i], gbc);
            }
        }

        textField2[3] = new JTextField();
        textField2[3].setPreferredSize(textFieldDimension);
        gbc.gridx = 1;
        gbc.gridy = 4;
        cardDetailsPanel.add(textField2[3], gbc);

        JLabel cardTypeLabel = new JLabel("Card Type:");
        cardTypeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        cardTypeLabel.setPreferredSize(labelDimension);
        gbc.gridx = 0;
        gbc.gridy = 5;
        cardDetailsPanel.add(cardTypeLabel, gbc);

        JPanel cardTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JRadioButton visaRB = new JRadioButton("Visa");
        visaRB.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        visaRB.setBackground(Color.WHITE);
        JRadioButton masterCardRB = new JRadioButton("MasterCard");
        masterCardRB.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        masterCardRB.setBackground(Color.WHITE);
        ButtonGroup cardTypeGroup = new ButtonGroup();
        cardTypeGroup.add(visaRB);
        cardTypeGroup.add(masterCardRB);
        cardTypePanel.add(visaRB);
        cardTypePanel.add(masterCardRB);
        gbc.gridx = 1;
        cardDetailsPanel.add(cardTypePanel, gbc);

        JCheckBox saveCardCB = new JCheckBox("Save this card for future use");
        saveCardCB.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        cardDetailsPanel.add(saveCardCB, gbc);

        JCheckBox termsCB = new JCheckBox("I agree to the terms and conditions");
        termsCB.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        gbc.gridy = 7;
        cardDetailsPanel.add(termsCB, gbc);

        transactionPanel.add(Box.createVerticalStrut(30));
        transactionPanel.add(cardDetailsPanel);

        chequeRB.addActionListener(e -> cardDetailsPanel.setVisible(false));
        cardRB.addActionListener(e -> cardDetailsPanel.setVisible(true));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton backTButton = new JButton("Back");
        backTButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        backTButton.setForeground(Color.WHITE);
        backTButton.setBackground(Color.BLACK);
        backTButton.setPreferredSize(new Dimension(100, 35));
        backTButton.addActionListener(backButtonEvent);
        buttonPanel.add(backTButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // User one (If textfield empty, show this)
                for (int x = 0; x < deliveryLabels.length - 2; x++) {
                    if (textField1[x].getText().isEmpty() && x != 1) {
                        JOptionPane.showMessageDialog(frameTransactionPage, "All the textfield cannot empty!", "Error! No Empty Textfield Allowed!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Textfield for phone and postal code
                if (textField1[1].getText().isEmpty() || textField1[4].getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frameTransactionPage, "All the textfield cannot empty!", "Error! No Empty Textfield Allowed!", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    if (textField1[1].getText().length() != 10 &&  textField1[1].getText().length() != 11) {
                        JOptionPane.showMessageDialog(null, "Phone Number " + textField1[1].getText() + " was not between the length of 10 or 11",
                                "ERROR! PHONE NUMBER OUT OF RANGE", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else if (textField1[4].getText().length() != 5) {
                        JOptionPane.showMessageDialog(null, "Postal Code " + textField1[4].getText() + " was not 5 digits",
                                "ERROR! POSTAL CODE OUT OF RANGE", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else {
                        String phoneInput = textField1[1].getText();

                        try {
                            int testInt = Integer.parseInt(phoneInput);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Phone Number " + textField1[1].getText() + " Must be all numbers!",
                                    "ERROR! PHONE NUMBER CONTAIN NON-NUMBER CHARACTER!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        String postalCodeInput = textField1[4].getText();

                        try {
                            int testInt = Integer.parseInt(postalCodeInput);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Postal Code " + textField1[4].getText() + " Must be all numbers!",
                                    "ERROR! POSTAL CODE CONTAIN NON-NUMBER CHARACTER!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                }

                if (stateComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(frameTransactionPage, "State must be selected either one!", "Error! No Empty State Allowed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (chequeRB.isSelected()) {

                    nameUser = textField1[0].getText();
                    phoneUser = textField1[1].getText();
                    addressFirstHalfUser = textField1[2].getText() + "  ";
                    for (int x = 3; x < deliveryLabels.length - 2; x++) {
                        addressFirstHalfUser += textField1[x].getText() + "  ";
                    }
                    addressSecondHalfUser = textField1[4].getText() + "  " + stateComboBox.getSelectedItem() + "  Malaysia.";
                    paymentMethod = chequeRB.getText();

                    JOptionPane.showMessageDialog(frameTransactionPage, "Cheque payment selected! Proceeding with transaction.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    frameTransactionPage.setVisible(false);

                    ReceiptPage receiptPage = new ReceiptPage();
                    receiptPage.showReceiptPage(nameUser, emailUser, phoneUser, addressFirstHalfUser, addressSecondHalfUser, paymentMethod, carCategoryChoose, carModelChoose,
                            variantChoose, basePrice, colorChoose, motorPowerChoose, downPayment, roadTax, monthlyInstalmentEMI, totalAmount);

                } else if (cardRB.isSelected()) {

                    for (int x = 0; x < cardLabels.length - 2; x++) {
                        if (textField2[x].getText().isEmpty() && x != 1 && x != 2) {
                            JOptionPane.showMessageDialog(frameTransactionPage, "All the textfield cannot empty!", "Error! No Empty Textfield Allowed!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    if (textField2[1].getText().isEmpty() || textField2[2].getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frameTransactionPage, "All the textfield cannot empty!", "Error! No Empty Textfield Allowed!", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else  {
                        if (textField2[1].getText().length() != 16) {
                            JOptionPane.showMessageDialog(null, "Card Number " + textField2[1].getText() + " was not 16 digits",
                                    "ERROR! CARD NUMBER WAS OUT OF RANGE", JOptionPane.WARNING_MESSAGE);
                            return;
                        } else if (textField2[2].getText().length() != 3) {
                            JOptionPane.showMessageDialog(null, "CVV Number " + textField2[2].getText() + " was not 3 digits",
                                    "ERROR! CVV NUMBER WAS OUT OF RANGE", JOptionPane.WARNING_MESSAGE);
                            return;
                        }else if (!textField2[1].getText().matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Card Number " + textField2[1].getText() + " Must be all numbers!",
                                    "ERROR! CARD NUMBER CONTAIN NON-NUMBER CHARACTER!", JOptionPane.WARNING_MESSAGE);
                            return;
                        } else {

                            String cvvInput = textField2[2].getText();

                            try {

                                int testInt = Integer.parseInt(cvvInput);

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "CVV Number " + textField2[2].getText() + " Must be all numbers!",
                                        "ERROR! CVV CONTAIN NON-NUMBER CHARACTER!", JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                        }
                    }

                    if (monthTField.getText().isEmpty() || yearTField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frameTransactionPage, "Must Filled In the Year and Month for card!", "Error! No Empty Month & Year Allowed!", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (monthTField.getText().length() != 2 || yearTField.getText().length() != 2) {
                        JOptionPane.showMessageDialog(frameTransactionPage, "Length of Year and Month for card must be 2 digits!", "Error! Year & Month OUT OF RANGE!", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {

                        String monthInput = monthTField.getText();
                        String yearInput = yearTField.getText();

                        try {
                            int testInt = Integer.parseInt(monthInput);
                            int testInt2 = Integer.parseInt(yearInput);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Month and Year must be all numbers!",
                                    "ERROR! MONTH & YEAR CONTAIN NON-NUMBER CHARACTER!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                    if (!visaRB.isSelected() && !masterCardRB.isSelected()) {
                        JOptionPane.showMessageDialog(frameTransactionPage, "Must Choose Visa or Master Either One Card Type!", "Error! No Card Type is Choose!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!termsCB.isSelected()) {
                        JOptionPane.showMessageDialog(frameTransactionPage, "Term policy must be selected!", "Error! Term Policy Must be Selected!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    nameUser = textField1[0].getText();
                    phoneUser = textField1[1].getText();
                    addressFirstHalfUser = textField1[2].getText() + "  ";
                    for (int x = 3; x < deliveryLabels.length - 2; x++) {
                        addressFirstHalfUser += textField1[x].getText() + "  ";
                    }
                    addressSecondHalfUser = textField1[4].getText() + "  " + stateComboBox.getSelectedItem() + "  Malaysia.";
                    paymentMethod = cardRB.getText();

                    JOptionPane.showMessageDialog(frameTransactionPage, "Card payment selected! Proceeding with transaction.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    frameTransactionPage.setVisible(false);

                    ReceiptPage receiptPage = new ReceiptPage();
                    receiptPage.showReceiptPage(nameUser, emailUser, phoneUser, addressFirstHalfUser, addressSecondHalfUser, paymentMethod, carCategoryChoose, carModelChoose,
                            variantChoose, basePrice, colorChoose, motorPowerChoose, downPayment, roadTax, monthlyInstalmentEMI, totalAmount);

                } else {
                    JOptionPane.showMessageDialog(frameTransactionPage, "Please select a payment method.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        transactionPanel.add(Box.createVerticalStrut(30));
        transactionPanel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(transactionPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        frameTransactionPage.add(scrollPane);
        frameTransactionPage.setVisible(true);
    }

    ActionListener backButtonEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frameTransactionPage.setVisible(false);
            PaymentSummaryPage paymentSummarypage = new PaymentSummaryPage();
            paymentSummarypage.showPaymentSummaryPage(emailUser, carCategoryChoose, carModelChoose,
                    variantChoose, basePrice, colorChoose, motorPowerChoose);
        }
    };

}


