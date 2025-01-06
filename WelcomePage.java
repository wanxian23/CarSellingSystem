import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JFrame frameWelcomePage = new JFrame("EcoMotion Welcome Page");

    // create welcome page
    public void createWelcomePage()
    {

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
        LogInButton.addActionListener(buttonLoginEvent);
        SignUpButton.addActionListener(buttonSignupEvent);
        ExitButton.addActionListener(e -> System.exit(0));

        // adding components to welcome panel
        WelcomePanel.add(WelcomeText);
        WelcomePanel.add(Box.createVerticalStrut(30));
        WelcomePanel.add(LogInButton);
        WelcomePanel.add(Box.createVerticalStrut(15));
        WelcomePanel.add(SignUpButton);
        WelcomePanel.add(Box.createVerticalStrut(20));
        WelcomePanel.add(ExitButton);

        WelcomePanel.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));

        frameWelcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWelcomePage.pack();
        frameWelcomePage.setLayout(new FlowLayout());
        frameWelcomePage.add(WelcomePanel);
        frameWelcomePage.setSize(screenSize.width, screenSize.height);
        frameWelcomePage.setVisible(true);

    }

    ActionListener buttonLoginEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frameWelcomePage.setVisible(false);
            LogInPage login = new LogInPage();
            login.createLogInPage();
        }
    };

    ActionListener buttonSignupEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frameWelcomePage.setVisible(false);
            SignUp signup = new SignUp();
            signup.showSignup();
        }
    };

    public static void main(String[] args) {
        WelcomePage welcomepage = new WelcomePage();
        welcomepage.createWelcomePage();
    }

}
