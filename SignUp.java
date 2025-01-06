import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SignUp {

    JFrame frameSignup = new JFrame("Signup Form");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JTextField nameField = new JTextField(30);
    JTextField emailField = new JTextField(30);
    JTextField phoneField = new JTextField(30);
    JPasswordField passwordField = new JPasswordField(30);
    JPasswordField rePasswordField = new JPasswordField(30);

    public void showSignup() {

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

        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Email Address
        JLabel emailLabel = new JLabel("Email address");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone number");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Re-enter Password
        JLabel rePasswordLabel = new JLabel("Re-enter password");
        rePasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(rePasswordLabel, gbc);

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
        signupButton.addActionListener(buttonSignupEvent);
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
        backButton.addActionListener(buttonBackEvent);

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

        // Create the main frame
        frameSignup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSignup.setSize(screenSize.width, screenSize.height);
        frameSignup.setLayout(new GridLayout(1, 2));

        // Add panels to the frame
        frameSignup.add(formPanel);
        frameSignup.add(imagePanel);

        // Set frame visibility
        frameSignup.setVisible(true);

    }

    ActionListener buttonBackEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            frameSignup.setVisible(false);

            LogInPage login = new LogInPage();
            login.createWelcomePage();

        }
    };

    ActionListener buttonSignupEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            boolean name, email, phone, pass, rePass;
            name = email = phone = pass = rePass = false;

            if (!nameField.getText().isEmpty()) name = true;
            if (!emailField.getText().isEmpty()) email = true;
            if (!phoneField.getText().isEmpty()) phone = true;
            if (passwordField.getPassword().length > 0) pass = true;
            if (rePasswordField.getPassword().length > 0) rePass = true;

            if (name && email && phone && pass && rePass) {
                try (BufferedReader reader = new BufferedReader(new FileReader("src/UserInfoFile.txt"))) {
                    int x = 0;
                    String[] line = new String[1000];

                    while ((line[x] = reader.readLine()) != null) {
                        x++;
                    }

                    x = x / 6;

                    for (int i = 0 ; x >= 0; x--, i+=6) {
                        name = email = phone = true;

                        if (line[i].equals(nameField.getText())) {
                            JOptionPane.showMessageDialog(null, "Username " + nameField.getText() + " already exist",
                                    "ERROR! USERNAME EXIST", JOptionPane.WARNING_MESSAGE);
                        }

                        if (line[i].equals(emailField.getText())) {
                            email = true;
                        }

                        if (line[i].equals(phoneField.getText())) {
                            phone = true;
                        }
                    }
                }
                catch (IOException fileException) {
                    JOptionPane.showMessageDialog(null, "File not found", "File not found", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You must fill in all the text field!!", "ERROR", JOptionPane.WARNING_MESSAGE);
            }

        }
    };

    public static void main(String[] args) {
        SignUp signup = new SignUp();
        signup.showSignup();
    }

}
