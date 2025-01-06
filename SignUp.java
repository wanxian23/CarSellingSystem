import javax.swing.*;
import java.awt.*;

public class SignUp {
    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Create the main frame
        JFrame frame = new JFrame("Signup Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new GridLayout(1, 2));

        // Left panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Create your account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Enter your details to start your journey.");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        formPanel.add(subtitleLabel, gbc);

        // Full Name
        JLabel nameLabel = new JLabel("Full name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(30);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Email Address
        JLabel emailLabel = new JLabel("Email address");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(30);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone number");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(phoneLabel, gbc);

        JTextField phoneField = new JTextField(30);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(30);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Re-enter Password
        JLabel rePasswordLabel = new JLabel("Re-enter password");
        rePasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(rePasswordLabel, gbc);

        JPasswordField rePasswordField = new JPasswordField(30);
        gbc.gridx = 1;
        formPanel.add(rePasswordField, gbc);

        // Terms and Conditions
        JCheckBox termsCheckBox = new JCheckBox("I agree to the [Terms of Service] and [Privacy Policy].");
        termsCheckBox.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(termsCheckBox, gbc);


        // Signup Button
        JButton signupButton = new JButton("SIGN UP");
        signupButton.setBackground(Color.BLACK);
        signupButton.setForeground(Color.WHITE);
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        formPanel.add(signupButton, gbc);

        //Back Button
        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        gbc.gridy = 8;
        gbc.gridx = 0;
        formPanel.add(backButton, gbc);

        // Login Link
        JLabel loginLabel = new JLabel("Already have an account? LOG IN");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        loginLabel.setForeground(Color.BLUE);
        gbc.gridy = 9;
        gbc.gridx = 0;
        formPanel.add(loginLabel, gbc);

        // Right panel for the image
        JPanel imagePanel = new JPanel();
        ImageIcon signupCarImage = new ImageIcon(SignUp.class.getResource("Images/SignUpCar.png"));
        Image adjustionSignupCarImage = signupCarImage.getImage().getScaledInstance(screenSize.width / 2, screenSize.height, Image.SCALE_SMOOTH);
        signupCarImage.setImage(adjustionSignupCarImage);
        JLabel imageLabel = new JLabel();// Replace with your image path
        imageLabel.setIcon(signupCarImage);
        imagePanel.add(imageLabel);

        // Add panels to the frame
        frame.add(formPanel);
        frame.add(imagePanel);

        // Set frame visibility
        frame.setVisible(true);
    }
}