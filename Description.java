import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Description {

    private String emailUser;

    private String carCategoryChoose;
    private String carModelChoose;
    private String variantChoose;
    private double basePrice;
    private int motorPowerChoose;

    private JFrame frame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JPanel carPanel;
    private JPanel descriptionPanel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JPanel buttonPanel;

    private String[] carCategories = {"EcoMotion Sedan", "EcoMotion Luxury EV", "EcoMotion Pickup Truck", "EcoMotion Sports EV", "Ecomotion SUV"};
    private String[] carModels = {"Aurora", "Imperial", "PowerHaul", "Stratos", "TerraVolt"};

    private String[][] variants = {
            {"  -- Select a Variant --", "Standard Range Plus (RWD)", "Long Range AWD"},
            {"  -- Select a Variant --", "Standard Range", "Long Range AWD", "Plaid"},
            {"  -- Select a Variant --", "Single Motor RWD", "Dual Motor AWD", "Tri Motor AWD"},
            {"  -- Select a Variant --", "Standard Range"},
            {"  -- Select a Variant --", "Long Range AWD", "Plaid"}
    };

    private String[][] variantDescriptions = {
            {"", "Standard Range Plus (RWD): Most affordable, with a range of ~350 km.", "Long Range AWD: Better range (~530 km) and performance."},
            {"", "Standard Range: The basic luxury sedan model.", "Long Range AWD: Offers a significantly longer range (~650 km).", "Plaid: High performance variant, with a 0-100 km/h in under 2 seconds."},
            {"", "Single Motor RWD: Entry-level, expected to have a range of about 400 km.", "Dual Motor AWD: Mid-range, with a range of about 547 km.", "Tri Motor AWD: Top-tier performance with up to 845 hp."},
            {"", "Standard Range: Reach 0-100 km/h in under 2 seconds, making it one of the fastest production cars, a range of over 1,000 km on a single charge."},
            {"", "Long Range AWD: Offers great performance and range (~560 km).", "Plaid: High-performance version, accelerating from 0-100 km/h in about 2.5 seconds."}
    };

    private String[][] variantPrices = {
            {"", "189,000", "218,000"},
            {"", "539,000", "589,000", "1,017,000"},
            {"", "285,000", "380,000", "475,000"},
            {"", "1,000,000"},
            {"", "408,000", "577,000"}
    };

    private int[][] motorPower = {
            {0, 190, 258},
            {0, 190, 258, 340},
            {0, 210, 360, 560},
            {0, 760},
            {0, 500, 760}
    };

    private double[][] variantPricesDouble = {
            {0, 189000, 218000},
            {0, 539000, 589000, 1017000},
            {0, 285000, 380000, 475000},
            {0, 1000000},
            {0, 408000, 577000}
    };

    private int[] selectedVariantIndex = new int[5];
    private int selectedCarIndex = -1;

    public void showDescriptionPage(String emailUser) {

        this.emailUser= emailUser;

        frame = new JFrame("EcoMotion Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title
        mainPanel.add(Box.createVerticalStrut(10));
        titleLabel = new JLabel("Description for EcoMotion", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(5));

        // Car Images Panel
        carPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 50));
        carPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 30));

        // Create car section (image + category + combo box)
        for (int i = 0; i < 5; i++) {
            JPanel carSetPanel = new JPanel();
            carSetPanel.setLayout(new BoxLayout(carSetPanel, BoxLayout.Y_AXIS));
            carSetPanel.setPreferredSize(new Dimension(220, 220));

            // Add image
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/Preview_n_Model/Car" + (i + 1) + ".png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            JLabel carImageLabel = new JLabel(resizedIcon);
            carImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            carSetPanel.add(carImageLabel);
            carSetPanel.add(Box.createVerticalStrut(15));

            // Add category label
            JLabel carCategoryLabel = new JLabel("<html>Category: " + carCategories[i], JLabel.CENTER);
            carCategoryLabel.setFont(new Font("Arial", Font.BOLD, 13));
            carCategoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            carSetPanel.add(carCategoryLabel);
            carSetPanel.add(Box.createVerticalStrut(10));

            // Add model label
            JLabel carModelLabel = new JLabel("Model: " + carModels[i], JLabel.CENTER);
            carModelLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            carModelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            carSetPanel.add(carModelLabel);
            carSetPanel.add(Box.createVerticalStrut(10));

            // Create the combo box
            JComboBox<String> variantComboBox = new JComboBox<>(variants[i]);
            final int carIndexFinal = i;

            variantComboBox.addActionListener(e -> {
                selectedVariantIndex[carIndexFinal] = variantComboBox.getSelectedIndex();
                selectedCarIndex = carIndexFinal;
                updateDescriptionAndPrice(carIndexFinal, variantComboBox.getSelectedIndex());
            });
            variantComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            variantComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
            variantComboBox.setPreferredSize(new Dimension(150, 30));
            carSetPanel.add(variantComboBox);

            carPanel.add(carSetPanel);
        }

        mainPanel.add(carPanel);

        mainPanel.add(Box.createVerticalStrut(30));
        // Description and Price Display
        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BorderLayout());

        descriptionLabel = new JLabel("<html><p style='padding:10px;'>Select a variant to see details here.</p></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER);

        priceLabel = new JLabel("Price: RM 0", JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 40));
        descriptionPanel.add(priceLabel, BorderLayout.SOUTH);
        mainPanel.add(descriptionPanel);

        // Buttons Panel
        mainPanel.add(Box.createVerticalStrut(10));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0));

        JButton backButton = new JButton("Back To Overview");
        backButton.setFont(new Font("Arial", Font.PLAIN, 35));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.black);
        backButton.addActionListener(e -> showBackPage());
        buttonPanel.add(backButton, BorderLayout.WEST);

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 35));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(Color.black);
        nextButton.addActionListener(e -> showNextPage());
        buttonPanel.add(nextButton, BorderLayout.EAST);

        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void updateDescriptionAndPrice(int carIndex, int variantIndex) {

        if (variantIndex < 1) {
            descriptionLabel.setText("<html><p style='padding:10px;'>Select a variant to see details here.</p></html>");
            priceLabel.setText("Price: RM 0");
            priceLabel.setFont(new Font("Arial", Font.BOLD, 40));
        } else {
            String description = variantDescriptions[carIndex][variantIndex];
            String price = variantPrices[carIndex][variantIndex];

            carCategoryChoose = carCategories[carIndex];
            carModelChoose = carModels[carIndex];
            variantChoose = variants[carIndex][variantIndex];
            basePrice = variantPricesDouble[carIndex][variantIndex];
            motorPowerChoose = motorPower[carIndex][variantIndex];

            descriptionLabel.setText("<html><p style='padding:10px;'>" + description + "</p></html>");
            priceLabel.setText("Price: RM " + price);
        }
    }

    private void showBackPage() {
        frame.dispose();
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.showOverviewPage();
    }

    private void showNextPage() {
        if (selectedCarIndex == -1 || selectedVariantIndex[selectedCarIndex] == 0) {
            JOptionPane.showMessageDialog(frame, "Please select a variant.");
            return;
        }

        String[] selectedVariantPrice = new String[5];
        for (int i = 0; i < selectedVariantPrice.length; i++) {
            int selectedIndex = selectedVariantIndex[i];
            if (selectedIndex != -1) {
                selectedVariantPrice[i] = variantPrices[i][selectedIndex];
            }
        }

       DisplayColorCar colorCar = new DisplayColorCar();
        colorCar.colorCarPage(emailUser, carCategoryChoose, carModelChoose, variantChoose, basePrice, motorPowerChoose, selectedVariantPrice, selectedCarIndex);

        frame.dispose();
    }

}
