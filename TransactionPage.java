import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionPage {

    public void createTransactionPage() {

        JFrame frameTransactionPage = new JFrame("EcoMotion Transaction Page");
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

        JRadioButton cashRB = new JRadioButton("CASH");
        cashRB.setFont(new Font("Century Gothic", Font.BOLD, 24));
        cashRB.setBackground(Color.WHITE);

        JRadioButton cardRB = new JRadioButton("CARD");
        cardRB.setFont(new Font("Century Gothic", Font.BOLD, 24));
        cardRB.setBackground(Color.WHITE);

        ButtonGroup groupTransB = new ButtonGroup();
        groupTransB.add(cashRB);
        groupTransB.add(cardRB);

        radioBPanel.add(cashRB);
        radioBPanel.add(cardRB);
        transactionPanel.add(Box.createVerticalStrut(30));
        transactionPanel.add(radioBPanel);


        JPanel deliveryDetailsPanel = new JPanel(new GridBagLayout());
        deliveryDetailsPanel.setBorder(BorderFactory.createTitledBorder("Delivery Details"));
        deliveryDetailsPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] deliveryLabels = {"Name:", "Contact Number:", "Street:", "City:", "State/Province:", "Postal Code:"};
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

            JTextField textField = new JTextField();
            textField.setPreferredSize(textFieldDimension);
            gbc.gridx = 1;
            deliveryDetailsPanel.add(textField, gbc);
        }
        transactionPanel.add(Box.createVerticalStrut(30));
        transactionPanel.add(deliveryDetailsPanel);


        JPanel cardDetailsPanel = new JPanel(new GridBagLayout());
        cardDetailsPanel.setBorder(BorderFactory.createTitledBorder("Card Details"));
        cardDetailsPanel.setBackground(Color.WHITE);
        cardDetailsPanel.setVisible(false);

        String[] cardLabels = {"Cardholder Name:", "Card Number:", "Expiry Date (MM/YY):", "CVV:", "Billing Address (Optional):"};
        for (int i = 0; i < cardLabels.length; i++) {
            JLabel label = new JLabel(cardLabels[i]);
            label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            label.setPreferredSize(labelDimension);
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            cardDetailsPanel.add(label, gbc);

            JTextField textField = new JTextField();
            textField.setPreferredSize(textFieldDimension);
            gbc.gridx = 1;
            cardDetailsPanel.add(textField, gbc);
        }

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


        cashRB.addActionListener(e -> cardDetailsPanel.setVisible(false));
        cardRB.addActionListener(e -> cardDetailsPanel.setVisible(true));


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton backTButton = new JButton("Back");
        backTButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        backTButton.setForeground(Color.WHITE);
        backTButton.setBackground(Color.BLACK);
        backTButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(backTButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cashRB.isSelected()) {
                    JOptionPane.showMessageDialog(frameTransactionPage, "Cash payment selected! Proceeding with transaction.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else if (cardRB.isSelected()) {
                    JOptionPane.showMessageDialog(frameTransactionPage, "Card payment selected! Proceeding with transaction.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frameTransactionPage, "Please select a payment method.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        transactionPanel.add(Box.createVerticalStrut(30));
        transactionPanel.add(buttonPanel);


        JScrollPane scrollPane = new JScrollPane(transactionPanel);
        frameTransactionPage.add(scrollPane);
        frameTransactionPage.setVisible(true);
    }



    public static void main(String[] args) {
        TransactionPage transPage = new TransactionPage();
        transPage.createTransactionPage();
    }

}


