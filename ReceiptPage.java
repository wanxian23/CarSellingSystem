import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.Random;
import java.text.DecimalFormat;

public class ReceiptPage {

    private String nameUser;
    private String emailUser;
    private String phoneUser;

    private JButton downloadButton = new JButton("Download Receipt");
    private JButton confirmButton = new JButton("Confirm");

    private JPanel combineReceiptComponents = new JPanel();

    private JFrame frameReceiptPage = new JFrame("EcoMotion Car Receipt");

    public void showReceiptPage(String nameUser, String emailUser, String phoneUser, String addressFirstHalfUser, String addressSecondHalfUser, String paymentMethod, String carCategoryChoose, String carModelChoose,
                                String variantChoose, double basePrice, String colorChoose, int motorPowerChoose, double downPayment,
                                int roadTax, double monthlyInstalmentEMI, double totalAmount) {

        this.nameUser = nameUser;
        this.emailUser= emailUser;
        this.phoneUser = phoneUser;


        // Get the screen size using Toolkit
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width / 1.5;

        Random random = new Random();
        DecimalFormat df = new DecimalFormat("#,##0.00");


        // Title
        JPanel titlelPanel = new JPanel();
        JLabel brandName = new JLabel("EcoMotion Car Receipt");
        brandName.setFont(new Font("Arial", Font.BOLD, 40));
        titlelPanel.add(brandName);
        titlelPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));


        // Header details
        String ecomotionLogoLink = "src/Images/EcoMotionLogo.png";
        ImageIcon logoImage = new ImageIcon(ecomotionLogoLink);
        Image adjustionLogo = logoImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logoImage.setImage(adjustionLogo);
        JLabel logoImageLabel = new JLabel(logoImage);

        JLabel receiptTitleLabel = new JLabel("EcoMotionCar Sales Receipt", SwingConstants.CENTER);
        receiptTitleLabel.setForeground(Color.white);
        receiptTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        JLabel lineLabel = new JLabel("--------------------------------------------------------------------------------------------------------------------------------", SwingConstants.CENTER);
        lineLabel.setForeground(Color.white);
        JLabel addressEcoMotionLabel = new JLabel("<html>122 EcoMotion, SkyRover Venue, 560000, Kuala Lumpur<br>012-345 6789 | info@ecomotion.com | www.ecomotion.com</html>", SwingConstants.CENTER);
        addressEcoMotionLabel.setForeground(Color.white);
        addressEcoMotionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel ecomotionPersonalDetailsPanel = new JPanel();
        ecomotionPersonalDetailsPanel.setLayout(new GridLayout(3, 1, 10, 10));
        ecomotionPersonalDetailsPanel.setBackground(Color.DARK_GRAY);
        ecomotionPersonalDetailsPanel.add(receiptTitleLabel);
        ecomotionPersonalDetailsPanel.add(lineLabel);
        ecomotionPersonalDetailsPanel.add(addressEcoMotionLabel);

        JPanel headerReceiptPanel = new JPanel();
        headerReceiptPanel.setBackground(Color.DARK_GRAY);
        headerReceiptPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));
        headerReceiptPanel.setPreferredSize(new Dimension((int) width, 260));
        headerReceiptPanel.add(logoImageLabel);
        headerReceiptPanel.add(ecomotionPersonalDetailsPanel);


        // Client Details
        JPanel clientDetialsLeftPanel = new JPanel();
        clientDetialsLeftPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        clientDetialsLeftPanel.setLayout(new GridLayout(3, 1, 10, 20));
        JLabel clientName = new JLabel("<html><b>Name:</b> <br>" + nameUser + "</html>");
        clientName.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel clientPhone = new JLabel("<html><b>Phone Number:</b> <br>" + phoneUser + "</html>");
        clientPhone.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel clientEmail = new JLabel("<html><b>Email:</b> <br>" + emailUser + "</html>");
        clientEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        clientDetialsLeftPanel.add(clientName);
        clientDetialsLeftPanel.add(clientPhone);
        clientDetialsLeftPanel.add(clientEmail);

        JPanel clientDetailsRightPanel = new JPanel();
        clientDetailsRightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        clientDetailsRightPanel.setLayout(new GridLayout(3, 1, 10, 20));
        LocalDate currentDate = LocalDate.now();
        JLabel addressUserLabel = new JLabel("<html><b>Address:</b> <br>" + addressFirstHalfUser + "<br>" + addressSecondHalfUser + "</html>");
        addressUserLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel cientReceiptNum = new JLabel("<html><b>Receipt Number:</b> <br>EUB " + String.valueOf(random.nextInt(9000000) + 1000000));
        cientReceiptNum.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel datePurchase = new JLabel("<html><b>Date of Purchase:</b> <br>" + String.valueOf(currentDate));
        datePurchase.setFont(new Font("Arial", Font.PLAIN, 20));
        clientDetailsRightPanel.add(addressUserLabel);
        clientDetailsRightPanel.add(cientReceiptNum);
        clientDetailsRightPanel.add(datePurchase);

        JPanel clientDetailsCombinePanel = new JPanel();
        clientDetailsCombinePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        clientDetailsCombinePanel.setPreferredSize(new Dimension((int) width, 300));
        clientDetailsCombinePanel.setLayout(new GridLayout(1, 2));
        clientDetailsCombinePanel.add(clientDetialsLeftPanel);
        clientDetailsCombinePanel.add(clientDetailsRightPanel);


        // Vehicle Info
        JPanel vehicleInfoTitlePanel = new JPanel();
        JLabel vehicleInfoTitleLabel = new JLabel("Vehicle Information");
        vehicleInfoTitleLabel.setForeground(Color.white);
        vehicleInfoTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        vehicleInfoTitlePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        vehicleInfoTitlePanel.setPreferredSize(new Dimension((int) width, 55));
        vehicleInfoTitlePanel.setBackground(Color.DARK_GRAY);
        vehicleInfoTitlePanel.add(vehicleInfoTitleLabel);

        JPanel vehicleInfoLeftPanel = new JPanel();
        vehicleInfoLeftPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        vehicleInfoLeftPanel.setLayout(new GridLayout(4, 1, 10, 20));
        JLabel carBrandName = new JLabel("<html><b>Brand:</b> <br>EcoMotion</html>");
        carBrandName.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel carCategory = new JLabel("<html><b>Category:</b> <br>" + carCategoryChoose + "</html>");
        carCategory.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel carModel = new JLabel("<html><b>Model:</b> <br>" + carModelChoose + "</html>");
        carModel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel engineCapacity = new JLabel("<html><b>Motor Power:</b> <br>" + motorPowerChoose + " kW</html>");
        engineCapacity.setFont(new Font("Arial", Font.PLAIN, 20));
        vehicleInfoLeftPanel.add(carBrandName);
        vehicleInfoLeftPanel.add(carCategory);
        vehicleInfoLeftPanel.add(carModel);
        vehicleInfoLeftPanel.add(engineCapacity);

        JPanel vehicleInfoRightPanel = new JPanel();
        vehicleInfoRightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        vehicleInfoRightPanel.setLayout(new GridLayout(4, 1, 10, 20));
        JLabel carColor = new JLabel("<html><b>Color:</b> <br>" + colorChoose + "</html>");
        carColor.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel carVariant = new JLabel("<html><b>Variants:</b> <br>" + variantChoose + "</html>");
        carVariant.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel carChassisNum = new JLabel("<html><b>Chassis Number:</b> <br>EXM " + String.valueOf(random.nextInt(9000000) + 1000000));
        carChassisNum.setFont(new Font("Arial", Font.PLAIN, 20));
        vehicleInfoRightPanel.add(carColor);
        vehicleInfoRightPanel.add(carVariant);
        vehicleInfoRightPanel.add(carChassisNum);

        JPanel carDetailsCombinePanel = new JPanel();
        carDetailsCombinePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        carDetailsCombinePanel.setPreferredSize(new Dimension((int) width, 400));
        carDetailsCombinePanel.setLayout(new GridLayout(1, 2));
        carDetailsCombinePanel.add(vehicleInfoLeftPanel);
        carDetailsCombinePanel.add(vehicleInfoRightPanel);


        // Payment Breakdown Details
        JPanel paymentBreakDownTitlePanel = new JPanel();
        JLabel paymentBreakDownTitleLabel = new JLabel("Payment Breakdown");
        paymentBreakDownTitleLabel.setForeground(Color.white);
        paymentBreakDownTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        paymentBreakDownTitlePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        paymentBreakDownTitlePanel.setPreferredSize(new Dimension((int) width, 55));
        paymentBreakDownTitlePanel.setBackground(Color.DARK_GRAY);
        paymentBreakDownTitlePanel.add(paymentBreakDownTitleLabel);

        JPanel paymentBreakdownLeftPanel = new JPanel();
        paymentBreakdownLeftPanel.setLayout(new GridLayout(5, 1));
        JLabel basePriceNameLabel = new JLabel(" Base Price");
        basePriceNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        basePriceNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel downPaymentNameLabel = new JLabel(" Down Payment (10%)");
        downPaymentNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        downPaymentNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel roadTaxNameLabel = new JLabel(" Road Tax");
        roadTaxNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        roadTaxNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel loanNameLabel = new JLabel(" Loan Amount (Monthly Instalment)");
        loanNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        loanNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel emptyLabel1 = new JLabel(" ");
        emptyLabel1.setBorder(new MatteBorder(1, 0,0,0, Color.gray));
        paymentBreakdownLeftPanel.add(basePriceNameLabel);
        paymentBreakdownLeftPanel.add(downPaymentNameLabel);
        paymentBreakdownLeftPanel.add(roadTaxNameLabel);
        paymentBreakdownLeftPanel.add(loanNameLabel);
        paymentBreakdownLeftPanel.add(emptyLabel1);

        JPanel paymentBreakdownCenterPanel = new JPanel();
        paymentBreakdownCenterPanel.setLayout(new GridLayout(5, 1));
        JLabel basePriceLabel = new JLabel("RM " + String.valueOf(df.format(basePrice)));
        basePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        basePriceLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        basePriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        paymentBreakdownCenterPanel.add(basePriceLabel);
        JLabel[] emptyLabel = new JLabel[4];
        for (int x = 0; x < 3; x++) {
            emptyLabel[x] = new JLabel(" ");
            emptyLabel[x].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            paymentBreakdownCenterPanel.add(emptyLabel[x]);
        }
        JLabel totalNameLabel = new JLabel("Total   ");
        totalNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalNameLabel.setBorder(new MatteBorder(1, 0,0,1, Color.gray));
        totalNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        paymentBreakdownCenterPanel.add(totalNameLabel);

        JPanel paymentBreakdownRightPanel = new JPanel();
        paymentBreakdownRightPanel.setLayout(new GridLayout(5, 1));
        emptyLabel[3] = new JLabel(" ");
        emptyLabel[3].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        JLabel downPaymentLabel = new JLabel(" RM " + String.valueOf(df.format(downPayment)));
        downPaymentLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        downPaymentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        downPaymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel roadTaxLabel = new JLabel(" RM " + String.valueOf(roadTax) + " per annual");
        roadTaxLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        roadTaxLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        roadTaxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel loanLabel = new JLabel(" RM " +String.valueOf(df.format(monthlyInstalmentEMI)));
        loanLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        loanLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        loanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel totalLabel = new JLabel(" RM " + String.valueOf(df.format(totalAmount)));
        totalLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paymentBreakdownRightPanel.add(emptyLabel[3]);
        paymentBreakdownRightPanel.add(downPaymentLabel);
        paymentBreakdownRightPanel.add(roadTaxLabel);
        paymentBreakdownRightPanel.add(loanLabel);
        paymentBreakdownRightPanel.add(totalLabel);

        JPanel combinePaymentBreakDownCenterRight = new JPanel();
        combinePaymentBreakDownCenterRight.setLayout(new GridLayout(1, 2));
        combinePaymentBreakDownCenterRight.add(paymentBreakdownCenterPanel);
        combinePaymentBreakDownCenterRight.add(paymentBreakdownRightPanel);

        JPanel paymentBreakdownCombinePanel = new JPanel();
        paymentBreakdownCombinePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        paymentBreakdownCombinePanel.setPreferredSize(new Dimension((int) width, 400));
        paymentBreakdownCombinePanel.setLayout(new GridLayout(1, 2));
        paymentBreakdownCombinePanel.add(paymentBreakdownLeftPanel);
        paymentBreakdownCombinePanel.add(combinePaymentBreakDownCenterRight);


        // User Payment Details (Balance)
        double totalPaidAmount = totalAmount;
        double balance = totalPaidAmount - totalAmount;

        JPanel paymentDetailsTitlePanel = new JPanel();
        JLabel paymentDetailsTitleLabel = new JLabel("Payment Details");
        paymentDetailsTitleLabel.setForeground(Color.white);
        paymentDetailsTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        paymentDetailsTitlePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        paymentDetailsTitlePanel.setPreferredSize(new Dimension((int) width, 55));
        paymentDetailsTitlePanel.setBackground(Color.DARK_GRAY);
        paymentDetailsTitlePanel.add(paymentDetailsTitleLabel);

        JPanel paymentDetailsLeftPanel = new JPanel();
        paymentDetailsLeftPanel.setLayout(new GridLayout(4, 1));
        JLabel paymentMethodNameLabel = new JLabel(" Payment Method");
        paymentMethodNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        paymentMethodNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel totalPaymentNameLabel = new JLabel(" Total Payment");
        totalPaymentNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        totalPaymentNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel amountMadeNameLabel = new JLabel(" Amount Paid");
        amountMadeNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        amountMadeNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel balanceNameLabel = new JLabel(" Balance");
        balanceNameLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        balanceNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        paymentDetailsLeftPanel.add(paymentMethodNameLabel);
        paymentDetailsLeftPanel.add(totalPaymentNameLabel);
        paymentDetailsLeftPanel.add(amountMadeNameLabel);
        paymentDetailsLeftPanel.add(balanceNameLabel);

        JPanel paymentDetailsCenterPanel = new JPanel();
        paymentDetailsCenterPanel.setLayout(new GridLayout(4, 1));
        JLabel paymentMethodLabel = new JLabel(paymentMethod);
        paymentMethodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paymentMethodLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        paymentMethodLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel totalPaymentLabel = new JLabel("RM " + String.valueOf(df.format(totalAmount)));
        totalPaymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalPaymentLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        totalPaymentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel amountPaidLabel = new JLabel("RM " + String.valueOf(df.format(totalPaidAmount)));
        amountPaidLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountPaidLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        amountPaidLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel[] emptyLabel2 = new JLabel[4];
        emptyLabel2[0] = new JLabel(" ");
        emptyLabel2[0].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        paymentDetailsCenterPanel.add(paymentMethodLabel);
        paymentDetailsCenterPanel.add(totalPaymentLabel);
        paymentDetailsCenterPanel.add(amountPaidLabel);
        paymentDetailsCenterPanel.add(emptyLabel2[0]);

        JPanel paymentDetailsRightPanel = new JPanel();
        paymentDetailsRightPanel.setLayout(new GridLayout(4, 1));
        for (int x = 1; x < emptyLabel2.length; x++) {
            emptyLabel2[x] = new JLabel(" ");
            emptyLabel2[x].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            paymentDetailsRightPanel.add(emptyLabel2[x]);
        }
        JLabel balanceLabel = new JLabel("RM " + String.valueOf(df.format(balance)));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        paymentDetailsRightPanel.add(balanceLabel);

        JPanel combinePaymentDetailsCenterRight = new JPanel();
        combinePaymentDetailsCenterRight.setLayout(new GridLayout(1, 2));
        combinePaymentDetailsCenterRight.add(paymentDetailsCenterPanel);
        combinePaymentDetailsCenterRight.add(paymentDetailsRightPanel);

        JPanel paymentDetailsCombinePanel = new JPanel();
        paymentDetailsCombinePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        paymentDetailsCombinePanel.setPreferredSize(new Dimension((int) width, 400));
        paymentDetailsCombinePanel.setLayout(new GridLayout(1, 2));
        paymentDetailsCombinePanel.add(paymentDetailsLeftPanel);
        paymentDetailsCombinePanel.add(combinePaymentDetailsCenterRight);


        // Signature Details
        JPanel signatureTitlePanel = new JPanel();
        JLabel signatureTitleLabel = new JLabel("Signature Details");
        signatureTitleLabel.setForeground(Color.white);
        signatureTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        signatureTitlePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        signatureTitlePanel.setPreferredSize(new Dimension((int) width, 55));
        signatureTitlePanel.setBackground(Color.DARK_GRAY);
        signatureTitlePanel.add(signatureTitleLabel);

        JPanel signaturePanel = new JPanel();
        signaturePanel.setLayout(new GridLayout(3, 2));
        signaturePanel.setPreferredSize(new Dimension((int) width, 200));
        signaturePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel[] emptyLabel3 = new JLabel[4];
        for (int x = 0; x < emptyLabel3.length; x++) {
            emptyLabel3[x] = new JLabel(" ");
            signaturePanel.add(emptyLabel3[x]);
        }
        JLabel sellerSignature = new JLabel("<html>&emsp;&emsp;__________________&emsp;&emsp;<br>" +
                                                 "&emsp;&emsp;&emsp;Seller Signature&emsp;&emsp;</html>");
        sellerSignature.setFont(new Font("Arial", Font.PLAIN, 20));
        sellerSignature.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel buyerSignature = new JLabel("<html>&emsp;&emsp;__________________&emsp;&emsp;<br>" +
                "&emsp;&emsp;&emsp;&emsp;Buyer Signature&emsp;&emsp;</html>");
        buyerSignature.setFont(new Font("Arial", Font.PLAIN, 20));
        buyerSignature.setHorizontalAlignment(SwingConstants.CENTER);
        signaturePanel.add(sellerSignature);
        signaturePanel.add(buyerSignature);


        // Button Back and button confirm
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
        downloadButton.setForeground(Color.WHITE);
        downloadButton.setBackground(Color.BLACK);
        downloadButton.setFont(new Font("Arial", Font.BOLD, 20));
        downloadButton.setPreferredSize(new Dimension(250, 50));
        downloadButton.addActionListener(printButtonEvent);

        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
        confirmButton.setPreferredSize(new Dimension(200, 50));
        confirmButton.addActionListener(confirmButtonEvent);
        buttonPanel.add(downloadButton);
        buttonPanel.add(confirmButton);


        // Combine Panel for all receipt components
        combineReceiptComponents.setLayout(new FlowLayout(FlowLayout.CENTER));
        combineReceiptComponents.setPreferredSize(new Dimension((int) width, 2275));
        combineReceiptComponents.add(headerReceiptPanel);
        combineReceiptComponents.add(clientDetailsCombinePanel);
        combineReceiptComponents.add(vehicleInfoTitlePanel);
        combineReceiptComponents.add(carDetailsCombinePanel);
        combineReceiptComponents.add(paymentBreakDownTitlePanel);
        combineReceiptComponents.add(paymentBreakdownCombinePanel);
        combineReceiptComponents.add(paymentDetailsTitlePanel);
        combineReceiptComponents.add(paymentDetailsCombinePanel);
        combineReceiptComponents.add(signatureTitlePanel);
        combineReceiptComponents.add(signaturePanel);


        // Combine Panel for all receipt components and buttons
        JPanel combineReceiptComponentsButtonPanel = new JPanel();
        combineReceiptComponentsButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        combineReceiptComponentsButtonPanel.setPreferredSize(new Dimension((int) width, 2450));
        combineReceiptComponentsButtonPanel.add(combineReceiptComponents);
        combineReceiptComponentsButtonPanel.add(buttonPanel);


        JScrollPane scrollPane = new JScrollPane(combineReceiptComponentsButtonPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); // Set the sensitivity of the scroll bar


        // Frame part
        frameReceiptPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameReceiptPage.pack();
        frameReceiptPage.add(titlelPanel, BorderLayout.NORTH);
        frameReceiptPage.add(scrollPane, BorderLayout.CENTER);
        frameReceiptPage.setSize(screenSize.width, screenSize.height);
        frameReceiptPage.setResizable(true);
        frameReceiptPage.setVisible(true);

    }

    ActionListener confirmButtonEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            String[] optionAfterConfirm = { "Main Page", "Overview Page (Before Login)", "Quit System" };

            int optionChoose = 0;
            optionChoose = JOptionPane.showOptionDialog(null, "Please choose an option below!\nBack to main page (Description Page) or Overview Page (Before Login) or Quit the System", "Choose an page to go!", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionAfterConfirm, "default");

            switch (optionChoose) {
                case -1:
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Main Page (Description) is choose, we are jumping to that page.....", "Jumping To Main Page", JOptionPane.INFORMATION_MESSAGE);
                    frameReceiptPage.setVisible(false);
                    Description descriptionpage = new Description();
                    descriptionpage.showDescriptionPage(emailUser);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Overview Page (Before Login) is choose, we are jumping to that page.....", "Jumping To Overview Page", JOptionPane.INFORMATION_MESSAGE);
                    frameReceiptPage.setVisible(false);
                    OverviewPage overviewpage = new OverviewPage();
                    overviewpage.showOverviewPage();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Thanks For Using Our System!\nSee you next time! ByeBye!", "THANKS! BYEBYE!", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                    break;
            }

        }
    };

    ActionListener printButtonEvent = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            try {

                // Dynamically find the Downloads folder based on each user PC
                String downloadsFolder = System.getProperty("user.home") + "\\Downloads";

                // Setup the name of the file
                String filePath = downloadsFolder + "\\CarReceipt.png";

                // Save the JPanel as an image
                savePanelAsImage(combineReceiptComponents, filePath);

                // Prompt success if download success
                JOptionPane.showMessageDialog(null, "Car Receipt Image saved successfully in Downloads Folder!", "Image Downloaded Successfully!", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "Failed to save the Car Receipt Image", "Error! Failed to Save Car Receipt!", JOptionPane.ERROR_MESSAGE);

            }

        }
    };

    // Setup the image
    private static void savePanelAsImage(JPanel panel, String filePath) throws Exception {

        // Create a BufferedImage of the panel based the panel we want to save (Its width and height)
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Make the panel into graphic form so that it can be save as image
        Graphics2D g2d = image.createGraphics();
        panel.printAll(g2d); // Render the panel to the image
        g2d.dispose();

        // Save the BufferedImage to a file
        File outputFile = new File(filePath);
        ImageIO.write(image, "PNG", outputFile);

    }
    
}
