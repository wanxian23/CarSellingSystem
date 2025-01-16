import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentSummaryPage {

    private String emailUser;

    private String carCategoryChoose;
    private String carModelChoose;
    private String variantChoose;
    private double basePrice;
    private String colorChoose;
    private int motorPowerChoose;


    // Declaration of variables
    private double downPayment;
    private int roadTax;
    private double monthlyInstalmentEMI;
    private double totalAmount;


    private JButton backButton = new JButton("Back");
    private JButton confirmButton = new JButton("Confirm");

    private JFrame framePaymentSummary = new JFrame("EcoMotion Payment Summary");

    public void showPaymentSummaryPage(String emailUser, String carCategoryChoose, String carModelChoose,
                                       String variantChoose, double basePrice, String colorChoose, int motorPowerChoose) {

        this.emailUser= emailUser;

        this.carCategoryChoose = carCategoryChoose;
        this.carModelChoose = carModelChoose;
        this.variantChoose = variantChoose;
        this.basePrice = basePrice;
        this.motorPowerChoose = motorPowerChoose;
        this.colorChoose = colorChoose;


        // Get the screen size using Toolkit
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width / 1.25;


        // Title
        JPanel titlelPanel = new JPanel();
        JLabel brandName = new JLabel("EcoMotion Summary of Payment");
        brandName.setFont(new Font("Arial", Font.BOLD, 40));
        titlelPanel.add(brandName);
        titlelPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));


        // Header of table
        JPanel headerTablePanel = new JPanel();
        headerTablePanel.setLayout(new BorderLayout());
        headerTablePanel.setBackground(Color.DARK_GRAY);
        headerTablePanel.setPreferredSize(new Dimension((int) width, 55));
        JLabel noNameLabel = new JLabel("  No");
        noNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        noNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        noNameLabel.setForeground(Color.WHITE);
        noNameLabel.setPreferredSize(new Dimension(100, 55));
        JLabel carDetailsNameLabel = new JLabel("  Car Details");
        carDetailsNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        carDetailsNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        carDetailsNameLabel.setForeground(Color.WHITE);
        JLabel priceNameLabel = new JLabel("  Price                                       ");
        priceNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        priceNameLabel.setForeground(Color.WHITE);
        priceNameLabel.setPreferredSize(new Dimension(300, 55));
        headerTablePanel.add(noNameLabel, BorderLayout.WEST);
        headerTablePanel.add(carDetailsNameLabel, BorderLayout.CENTER);
        headerTablePanel.add(priceNameLabel, BorderLayout.EAST);


        // Calculation of car detail
        downPayment = basePrice * 0.1;
        double loanAmount = basePrice - downPayment;
        double annualInterestRate = 3.4;
        double monthlyInterestRate = annualInterestRate / (12 * 100);
        int loanTenureYear = 7;
        int loanTenureMonth = loanTenureYear * 12;
        monthlyInstalmentEMI = (loanAmount * monthlyInterestRate * Math.pow(( 1 + monthlyInterestRate), loanTenureMonth)) / Math.pow(( 1 + monthlyInterestRate), loanTenureMonth - 1);

        if (motorPowerChoose >= 0 && motorPowerChoose <= 50) {
            roadTax = 20;
        } else if (motorPowerChoose >= 51 && motorPowerChoose <= 100) {
            roadTax = 40;
        } else if (motorPowerChoose >= 101 && motorPowerChoose <= 150) {
            roadTax = 80;
        } else if (motorPowerChoose >= 151 && motorPowerChoose <= 200) {
            roadTax = 160;
        } else if (motorPowerChoose >= 201 && motorPowerChoose <= 250) {
            roadTax = 320;
        } else if (motorPowerChoose >= 250) {
            roadTax = (motorPowerChoose - 250) + 320;
        }
        totalAmount = downPayment + roadTax + monthlyInstalmentEMI;


        // Body of table (Car details)
        JPanel carDetailsPanel = new JPanel();
        carDetailsPanel.setLayout(new BorderLayout());
        carDetailsPanel.setPreferredSize(new Dimension((int) width, 230));
        JLabel no1Label = new JLabel("<html><br><b>&ensp;&ensp;1</b></html>");
        no1Label.setFont(new Font("Arial", Font.PLAIN, 20));
        no1Label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        no1Label.setPreferredSize(new Dimension(100, 200));
        no1Label.setVerticalAlignment(SwingConstants.TOP);
        JLabel carDetailsLabel = new JLabel("<html><br><b>&ensp;&ensp;EcoMotion</b><br>" +
                                            "<br><b>&ensp;&ensp;&ensp;- Category: </b>" + carCategoryChoose +
                                            "<br><b>&ensp;&ensp;&ensp;- Model: </b>" + carModelChoose +
                                            "<br><b>&ensp;&ensp;&ensp;- Variant: </b>" + variantChoose +
                                            "<br><b>&ensp;&ensp;&ensp;- Colour: </b>" + colorChoose +
                                            "<br><b>&ensp;&ensp;&ensp;- Base Price: </b>" + String.valueOf(String.format("%.2f", basePrice)) + "</html>");
        carDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        carDetailsLabel.setBorder(new MatteBorder(1, 0, 1, 0, Color.GRAY));
        carDetailsLabel.setVerticalAlignment(SwingConstants.TOP);
        JLabel downPaymentLabel = new JLabel("<html><br>&ensp;&ensp;<b>Down Payment (10%)<br></b>                                       " +
                                            "<br>&ensp;&ensp;&ensp;- RM " + String.valueOf(String.format("%.2f", downPayment)));
        downPaymentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        downPaymentLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        downPaymentLabel.setPreferredSize(new Dimension(300, 200));
        downPaymentLabel.setVerticalAlignment(SwingConstants.TOP);
        carDetailsPanel.add(no1Label, BorderLayout.WEST);
        carDetailsPanel.add(carDetailsLabel, BorderLayout.CENTER);
        carDetailsPanel.add(downPaymentLabel, BorderLayout.EAST);


        // Body of table (Tax details)
        JPanel roadTaxPanel = new JPanel();
        roadTaxPanel.setLayout(new BorderLayout());
        roadTaxPanel.setPreferredSize(new Dimension((int) width, 180));
        JLabel no2Label = new JLabel("<html><br><b>&ensp;&ensp;2</b></html>");
        no2Label.setFont(new Font("Arial", Font.PLAIN, 20));
        no2Label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        no2Label.setPreferredSize(new Dimension(100, 200));
        no2Label.setVerticalAlignment(SwingConstants.TOP);
        JLabel roadTaxDetailsLabel = new JLabel("<html><br><b>&ensp;&ensp;Road Tax</b><br>" +
                "<br><b>&ensp;&ensp;&ensp;- Motor Power: </b>" + String.valueOf(motorPowerChoose) +
                "<br><b>&ensp;&ensp;&ensp;- Region:</b> Penisular Malaysia" +
                "<br><b>&ensp;&ensp;&ensp;- Fuel Type:</b> EV" + "</html>");
        roadTaxDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        roadTaxDetailsLabel.setBorder(new MatteBorder(1, 0, 1, 0, Color.GRAY));
        roadTaxDetailsLabel.setVerticalAlignment(SwingConstants.TOP);
        JLabel roadTaxPrice = new JLabel("<html><br><b>&ensp;&ensp;RM " + String.valueOf(roadTax) + "</b> per annual</html>");
        roadTaxPrice.setFont(new Font("Arial", Font.PLAIN, 20));
        roadTaxPrice.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        roadTaxPrice.setPreferredSize(new Dimension(300, 200));
        roadTaxPrice.setVerticalAlignment(SwingConstants.TOP);
        roadTaxPanel.add(no2Label, BorderLayout.WEST);
        roadTaxPanel.add(roadTaxDetailsLabel, BorderLayout.CENTER);
        roadTaxPanel.add(roadTaxPrice, BorderLayout.EAST);


        // Body of table (Loan details)
        JPanel loanDetailsPanel = new JPanel();
        loanDetailsPanel.setLayout(new BorderLayout());
        loanDetailsPanel.setPreferredSize(new Dimension((int) width, 180));
        JLabel no3Label = new JLabel("<html><br><b>&ensp;&ensp;3</b></html>");
        no3Label.setFont(new Font("Arial", Font.PLAIN, 20));
        no3Label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        no3Label.setPreferredSize(new Dimension(100, 200));
        no3Label.setVerticalAlignment(SwingConstants.TOP);
        JLabel loanDetailsLabel = new JLabel("<html><br><b>&ensp;&ensp;Loan Details</b><br>" +
                "<br><b>&ensp;&ensp;&ensp;- Interest Rate:</b> 3.4% p.a." + String.valueOf(motorPowerChoose) +
                "<br><b>&ensp;&ensp;&ensp;- Loan Tenure:</b> 7 Years" +
                "<br><b>&ensp;&ensp;&ensp;- Loan Amount (90% From Base Price):</b> RM " + String.valueOf(String.format("%.2f", loanAmount)) + "</html>");
        loanDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        loanDetailsLabel.setBorder(new MatteBorder(1, 0, 1, 0, Color.GRAY));
        loanDetailsLabel.setVerticalAlignment(SwingConstants.TOP);
        JLabel monthlyInstalmentLabel = new JLabel("<html><br>&ensp;&ensp;<b>Monthly Instalment<br></b>                                       " +
                "<br>&ensp;&ensp;&ensp;- RM " + String.valueOf(String.format("%.2f", monthlyInstalmentEMI)));
        monthlyInstalmentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        monthlyInstalmentLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        monthlyInstalmentLabel.setPreferredSize(new Dimension(300, 200));
        monthlyInstalmentLabel.setVerticalAlignment(SwingConstants.TOP);
        loanDetailsPanel.add(no3Label, BorderLayout.WEST);
        loanDetailsPanel.add(loanDetailsLabel, BorderLayout.CENTER);
        loanDetailsPanel.add(monthlyInstalmentLabel, BorderLayout.EAST);


        // Body of table (Total = Last row)
        JPanel totalRowPanel = new JPanel();
        totalRowPanel.setLayout(new BorderLayout());
        totalRowPanel.setPreferredSize(new Dimension((int) width, 70));
        JLabel emptyLabel = new JLabel(" ");
        emptyLabel.setBorder(new MatteBorder(1,1 ,1, 0, Color.GRAY));
        emptyLabel.setPreferredSize(new Dimension(100, 200));
        JLabel totalNameLabel = new JLabel("Total   ");
        totalNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalNameLabel.setBorder(new MatteBorder(1, 0, 1, 0, Color.GRAY));
        totalNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel totalPriceLabel = new JLabel("    RM " + String.valueOf(String.format("%.2f", totalAmount)));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalPriceLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        totalPriceLabel.setPreferredSize(new Dimension(300, 200));
        totalRowPanel.add(emptyLabel, BorderLayout.WEST);
        totalRowPanel.add(totalNameLabel, BorderLayout.CENTER);
        totalRowPanel.add(totalPriceLabel, BorderLayout.EAST);


        // Button Back and button confirm
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(1, 100, 30));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.addActionListener(backButtonEvent);

        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
        confirmButton.setPreferredSize(new Dimension(200, 50));
        confirmButton.addActionListener(confirmButtonEvent);
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);


        // Combine panel for all the components of table
        JPanel combinePaymentSummaryComponents = new JPanel();
        combinePaymentSummaryComponents.setLayout(new FlowLayout(FlowLayout.CENTER));
        combinePaymentSummaryComponents.setPreferredSize(new Dimension((int) width, 900));
        combinePaymentSummaryComponents.add(headerTablePanel);
        combinePaymentSummaryComponents.add(carDetailsPanel);
        combinePaymentSummaryComponents.add(roadTaxPanel);
        combinePaymentSummaryComponents.add(loanDetailsPanel);
        combinePaymentSummaryComponents.add(totalRowPanel);
        combinePaymentSummaryComponents.add(buttonPanel);


        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(combinePaymentSummaryComponents);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); // Set the sensitivity of the scroll bar


        // Frame part
        framePaymentSummary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePaymentSummary.pack();
        framePaymentSummary.add(titlelPanel, BorderLayout.NORTH);
        framePaymentSummary.add(scrollPane, BorderLayout.CENTER);
        framePaymentSummary.setSize(screenSize.width, screenSize.height);
        framePaymentSummary.setResizable(true);
        framePaymentSummary.setVisible(true);

    }

    ActionListener backButtonEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            framePaymentSummary.setVisible(false);
            Description descriptionpage = new Description();
            descriptionpage.showDescriptionPage(emailUser);
        }
    };

    ActionListener confirmButtonEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            framePaymentSummary.setVisible(false);
            TransactionPage transactionpage = new TransactionPage();
            transactionpage.createTransactionPage(emailUser, carCategoryChoose, carModelChoose,
                    variantChoose, basePrice, colorChoose, motorPowerChoose, downPayment, roadTax, monthlyInstalmentEMI, totalAmount);
        }
    };

}
