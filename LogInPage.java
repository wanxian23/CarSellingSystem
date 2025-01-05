import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogInPage {

    JFrame frameWelcomePage = new JFrame("EcoMotion Welcome Page");
    JFrame frameLoginPage = new JFrame("EcoMotion Log In Page");

    // create welcome page
    public void createWelcomePage()
    {

        frameWelcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //panel for welcome page
        JPanel WelcomePanel = new JPanel();
        WelcomePanel.setLayout(new BoxLayout(WelcomePanel, BoxLayout.Y_AXIS));

        // welcome label
        JLabel WelcomeText = new JLabel("Welcome to Eco Motion");
        WelcomeText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        WelcomeText.setFont(new Font("Century Gothic", Font.BOLD, 70));

        // log in button
        JButton LogInButton = new JButton("LOG IN");
        LogInButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        LogInButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        LogInButton.setBackground(Color.BLACK);
        LogInButton.setForeground(Color.WHITE);
        LogInButton.setMaximumSize(new Dimension(130, 50));  // Enforce max size
        LogInButton.setMinimumSize(new Dimension(0, 0));
        LogInButton.addActionListener(buttonLoginEvent);

        //sign up button
        JButton SignUpButton = new JButton("SIGN UP");
        SignUpButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        SignUpButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        SignUpButton.setBackground(Color.BLACK);
        SignUpButton.setForeground(Color.WHITE);
        SignUpButton.setMaximumSize(new Dimension(130, 50));  // Enforce max size
        SignUpButton.setMinimumSize(new Dimension(0, 0));

        // exit button
        JButton ExitButton = new JButton("EXIT");
        ExitButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        ExitButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        ExitButton.setBackground(Color.GRAY);
        ExitButton.setForeground(Color.WHITE);

        // action listeneres for buttons
        LogInButton.addActionListener(e -> System.out.println("Log In Clicked"));
        SignUpButton.addActionListener(e -> System.out.println("Sign Up Clicked"));
        ExitButton.addActionListener(e -> System.exit(0));

        // adding components to welcome panel
        WelcomePanel.add(WelcomeText);
        WelcomePanel.add(Box.createVerticalStrut(30));
        WelcomePanel.add(LogInButton);
        WelcomePanel.add(Box.createVerticalStrut(15));
        WelcomePanel.add(SignUpButton);
        WelcomePanel.add(Box.createVerticalStrut(20));
        WelcomePanel.add(ExitButton);

        WelcomePanel.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));

        frameWelcomePage.pack();
        frameWelcomePage.setLayout(new FlowLayout());
        frameWelcomePage.add(WelcomePanel);
        frameWelcomePage.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameWelcomePage.setSize(screenSize.width, screenSize.height);

    }

    ActionListener buttonLoginEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frameWelcomePage.setVisible(false);
            createLogInPage();
        }
    };

    JTextField usernameField = new JTextField(20);
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

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(usernameLabel, gbc);

        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(usernameField, gbc);

        JLabel passwLabel = new JLabel("Password");
        passwLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(passwLabel, gbc);

        passwField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        passwField.setPreferredSize(new Dimension(200, 30));
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

        // action listener for back button
        backButton.addActionListener(e -> {
            frameLoginPage.setVisible(false);
            frameWelcomePage.setVisible(true);
        });

        JButton loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.setPreferredSize(new Dimension(100, 35));
        loginButton.addActionListener(loginEvent);
        buttonPanel.add(loginButton);

        // adding button panel to center panel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(buttonPanel, gbc);

        // adding panels to the frame
        frameLoginPage.add(topPanel, BorderLayout.NORTH);
        frameLoginPage.add(centerPanel, BorderLayout.CENTER);
        frameLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLoginPage.pack();
        frameLoginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameLoginPage.setVisible(true);
    }

    ActionListener loginEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            boolean username = false, pass = false;

            try (BufferedReader reader = new BufferedReader(new FileReader("src/UserInfoFile.txt"))) {

                int x = 0;
                String[] line = new String[1000];

                while ((line[x] = reader.readLine()) != null) {
                    x++;
                }

                x = x / 6;

                for (int y = 0 ; x >= 0; x--, y+=6) {
                    username = pass = false;
                    if (line[y].equals(usernameField.getText())) {
                        username = true;
                    }

                    if (line[y + 3].equals(passwField.getText())) {
                        pass = true;
                    }

                    if (username) {
                        break;
                    }
                }

                System.out.println(username);
                System.out.println(pass);

                if (username && pass) {

                    JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULLY!", "LOGIN SUCCESS",
                            JOptionPane.INFORMATION_MESSAGE);

                    frameLoginPage.setVisible(false);

                    MainPage mainpage = new MainPage();
                    mainpage.showMainPage();

                } else if (!username) {
                    JOptionPane.showMessageDialog(null, "Username " + usernameField.getText() + " not found", "USERNAME NOT FOUND",
                            JOptionPane.WARNING_MESSAGE);
                } else if (username && !pass) {
                    JOptionPane.showMessageDialog(null, "WRONG PASSWORD! PLEASE TRY AGAIN", "PASSWORD WRONG",
                            JOptionPane.WARNING_MESSAGE);
                }

            } catch (IOException fileException) {
                JOptionPane.showMessageDialog(null, "File not found", "File not found", JOptionPane.WARNING_MESSAGE);
            }

        }
    };


    public static void main (String[] args){
        LogInPage wp = new LogInPage();
        wp.createWelcomePage();
    }
}
