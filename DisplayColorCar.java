import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayColorCar {

    private JFrame frame;
    private JPanel mainPanel;
    private JComboBox<String> colorComboBox;
    private JLabel carColorImageLabel;
    private JLabel totalPriceLabel;
    private JPanel buttonPanel;

    private String[][] colorVariants = {
            {"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"},
            {"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Solid Black", "Ultra Red"},
            {"Quicksilver"},
            {"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"},
            {"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Solid Black", "Ultra Red"}
    };

    private String[][] colorImagePaths = {
            {"/Images/Color/Aurora/PearlWhiteMulti-Coat.png", "/Images/Color/Aurora/DeepBlueMetallic.png", "/Images/Color/Aurora/StealthGrey.png", "/Images/Color/Aurora/Quicksilver.png", "/Images/Color/Aurora/UltraRed.png"},
            {"/Images/Color/Imperial/PearlWhiteMulti-Coat.png", "/Images/Color/Imperial/DeepBlueMetallic.png", "/Images/Color/Imperial/StealthGrey.png", "/Images/Color/Imperial/SolidBlack.png", "/Images/Color/Imperial/UltraRed.png"},
            {"/Images/Color/PowerHaul/Quicksilver.png"},
            {"/Images/Color/Stratos/PearlWhiteMulti-Coat.png", "/Images/Color/Stratos/DeepBlueMetallic.png", "/Images/Color/Stratos/StealthGrey.png", "/Images/Color/Stratos/Quicksilver.png", "/Images/Color/Stratos/UltraRed.png"},
            {"/Images/Color/TerraVolt/PearlWhiteMulti-Coat.png", "/Images/Color/TerraVolt/DeepBlueMetallic.png", "/Images/Color/TerraVolt/StealthGrey.png", "/Images/Color/TerraVolt/SolidBlack.png", "/Images/Color/TerraVolt/UltraRed.png"}
    };

    public DisplayColorCar(String[] selectedVariantPrice, int selectedCarIndex) {
        frame = new JFrame("EcoMotion Car Color");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Title
        mainPanel.add(Box.createVerticalStrut(20));
        JLabel titleLabel = new JLabel("Choose EcoMotion Color", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        // Add ComboBox for Color
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30,10));
        colorComboBox = new JComboBox<>(colorVariants[selectedCarIndex]);
        colorComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        colorComboBox.setPreferredSize(new Dimension(300, 40));
        comboBoxPanel.add(colorComboBox);
        colorComboBox.addActionListener(e -> updateColorImage(selectedCarIndex));
        mainPanel.add(comboBoxPanel);

        // Add Color Car
        mainPanel.add(Box.createVerticalStrut(20));
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        carColorImageLabel = new JLabel();
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource(colorImagePaths[selectedCarIndex][0]));
        Image scaledImage = defaultIcon.getImage().getScaledInstance(1000, 400, Image.SCALE_SMOOTH);
        carColorImageLabel.setIcon(new ImageIcon(scaledImage));
        carColorImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.add(carColorImageLabel);
        mainPanel.add(imagePanel);

        // Show total price
        totalPriceLabel = new JLabel("Total Price: " + selectedVariantPrice[selectedCarIndex], JLabel.CENTER);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 40));
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(totalPriceLabel);

        // Add Back and Confirm Buttons
        mainPanel.add(Box.createVerticalStrut(30));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 35));
        backButton.addActionListener(e -> showPreviousPage());
        buttonPanel.add(backButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 35));
        buttonPanel.add(confirmButton);

        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void updateColorImage(int selectedCarIndex) {
        String selectedColor = (String) colorComboBox.getSelectedItem();
        int colorIndex = colorComboBox.getSelectedIndex();
        ImageIcon colorIcon = new ImageIcon(getClass().getResource(colorImagePaths[selectedCarIndex][colorIndex]));

        Image img = colorIcon.getImage();
        Image scaledImg = img.getScaledInstance(1000, 400, Image.SCALE_SMOOTH);
        colorIcon = new ImageIcon(scaledImg);
        carColorImageLabel.setIcon(colorIcon);
    }

    private void showPreviousPage() {
        frame.dispose();
        new Description();
    }
}
