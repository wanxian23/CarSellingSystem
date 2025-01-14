import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogInPage {

    private String emailUser;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JFrame frameLoginPage = new JFrame("EcoMotion Log In Page");

    JTextField emailField = new JTextField(20);
    JPasswordField passwField = new JPasswordField(20);

    // create log in page
    public void createLogInPage(){


        // panel for log in page
        JPanel LogInPanel = new JPanel();
        LogInPanel.setLayout(new BoxLayout(LogInPanel, BoxLayout.Y_AXIS));
        LogInPanel.setBorder(BorderFactory.createEmptyBorder(50,100,50,100));

        // top left Eco Motion label
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel companyName = new JLabel("Eco Motion");
        companyName.setFont(new Font("Century Gothic", Font.BOLD, 45));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(companyName, BorderLayout.NORTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 40));


        // Center panel for the login form
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // log in prompt text
        JLabel LogInPrompt = new JLabel("Log in to your account");
        LogInPrompt.setFont(new Font("Century Gothic", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(LogInPrompt, gbc);

        JLabel usernameLabel = new JLabel("Email");
        usernameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(usernameLabel, gbc);

        emailField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.addKeyListener(loginEvent2);
        emailField.requestFocusInWindow();
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(emailField, gbc);

        JLabel passwLabel = new JLabel("Password");
        passwLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(passwLabel, gbc);

        passwField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        passwField.setPreferredSize(new Dimension(200, 30));
        passwField.addKeyListener(loginEvent2);
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(passwField, gbc);

        JCheckBox rememberMe = new JCheckBox("Remember me");
        rememberMe.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(rememberMe, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(backButton);

        // Signup Link
        JLabel signupLabel = new JLabel("Do not have an account? SIGN UP");
        signupLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        signupLabel.setForeground(Color.BLUE);
        signupLabel.addMouseListener(signupClick);
        gbc.gridy = 4;
        gbc.gridx = 0;
        centerPanel.add(signupLabel, gbc);

        // action listener for back button
        backButton.addActionListener(backButtonEvent);

        JButton loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.setPreferredSize(new Dimension(100, 35));
        loginButton.addActionListener(loginEvent);
        buttonPanel.add(loginButton);

        // adding button panel to center panel
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(buttonPanel, gbc);

        JPanel combineLoginComponents = new JPanel();
        combineLoginComponents.setLayout(new BorderLayout());
        combineLoginComponents.add(topPanel, BorderLayout.NORTH);
        combineLoginComponents.add(centerPanel, BorderLayout.CENTER);

        // Right panel for the image
        JPanel imagePanel = new JPanel();
        ImageIcon signupCarImage = new ImageIcon("src/Images/LoginCarImage.png");
        Image adjustionSignupCarImage = signupCarImage.getImage().getScaledInstance(screenSize.width / 2, screenSize.height, Image.SCALE_SMOOTH);
        signupCarImage.setImage(adjustionSignupCarImage);
        JLabel imageLabel = new JLabel();// Replace with your image path
        imageLabel.setIcon(signupCarImage);
        imagePanel.add(imageLabel);

        // adding panels to the frame
        frameLoginPage.setLayout(new GridLayout(1, 2));
        frameLoginPage.add(combineLoginComponents);
        frameLoginPage.add(imagePanel);
        frameLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLoginPage.pack();
        frameLoginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameLoginPage.setVisible(true);
    }

    MouseListener signupClick = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            frameLoginPage.setVisible(false);
            SignUp signup = new SignUp();
            signup.showSignup();
        }
    };

    ActionListener backButtonEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frameLoginPage.setVisible(false);

            WelcomePage welcomepage = new WelcomePage();
            welcomepage.createWelcomePage();
        }
    };

    KeyListener loginEvent2 = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                boolean email = false, pass = false;

                try (BufferedReader reader = new BufferedReader(new FileReader("src/UserInfoFile.txt"))) {

                    int x = 0;
                    String[] line = new String[1000];

                    while ((line[x] = reader.readLine()) != null) {
                        x++;
                    }

                    x = x / 6;

                    for (int i = 0; x > 0; x--, i += 6) {
                        email = pass = false;
                        if (line[i + 2].equals(emailField.getText())) {
                            email = true;
                        }

                        if (line[i + 4].equals(passwField.getText())) {
                            pass = true;
                        }

                        if (email) {
                            emailUser = line[i + 2];
                            break;
                        }
                    }

                    if (email && pass) {

                        JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULLY!", "LOGIN SUCCESS",
                                JOptionPane.INFORMATION_MESSAGE);

                        frameLoginPage.setVisible(false);

                        Description descriptionPage = new Description();
                        descriptionPage.showDescriptionPage(emailUser);

                    } else if (!email) {
                        JOptionPane.showMessageDialog(null, "Email " + emailField.getText() + " not found", "EMAIL NOT FOUND",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (email && !pass) {
                        JOptionPane.showMessageDialog(null, "WRONG PASSWORD! PLEASE TRY AGAIN", "PASSWORD WRONG",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (IOException fileException) {
                    JOptionPane.showMessageDialog(null, "Login Failed! Please try to create an account!", "File not found", JOptionPane.WARNING_MESSAGE);
                }

            }
        }
    };

    ActionListener loginEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            boolean email = false, pass = false;

            try (BufferedReader reader = new BufferedReader(new FileReader("src/UserInfoFile.txt"))) {

                int x = 0;
                String[] line = new String[1000];

                while ((line[x] = reader.readLine()) != null) {
                    x++;
                }

                x = x / 6;

                for (int i = 0 ; x > 0; x--, i+=6) {
                    email = pass = false;
                    if (line[i + 2].equals(emailField.getText())) {
                        email = true;
                    }

                    if (line[i + 4].equals(passwField.getText())) {
                        pass = true;
                    }

                    if (email) {
                        emailUser = line[i + 2];
                        break;
                    }
                }

                if (email && pass) {

                    JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULLY!", "LOGIN SUCCESS",
                            JOptionPane.INFORMATION_MESSAGE);

                    frameLoginPage.setVisible(false);

                    Description descriptionPage = new Description();
                    descriptionPage.showDescriptionPage(emailUser);

                } else if (!email) {
                    JOptionPane.showMessageDialog(null, "Email " + emailField.getText() + " not found", "EMAIL NOT FOUND",
                            JOptionPane.WARNING_MESSAGE);
                } else if (email && !pass) {
                    JOptionPane.showMessageDialog(null, "WRONG PASSWORD! PLEASE TRY AGAIN", "PASSWORD WRONG",
                            JOptionPane.WARNING_MESSAGE);
                }

            } catch (IOException fileException) {
                JOptionPane.showMessageDialog(null, "Login Failed! Please try to create an account!", "File not found", JOptionPane.WARNING_MESSAGE);
            }

        }
    };

}
