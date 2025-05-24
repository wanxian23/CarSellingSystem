import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

public class OverviewPage {

    // Preview Car with Image Part
    private String carPictureLink = "src/Images/Preview_n_Model/Car1.png";

    // ImageIcon = Get image from source file
    private ImageIcon carPictures = new ImageIcon(carPictureLink);
    private JLabel labelContainPicture = new JLabel();



    // Filter Title Part
    private JPanel titleOverviewPanel = new JPanel();
    private JLabel titleOverview = new JLabel("Overview of Cars");



    // Filter Part
    private int index;
    private String[] modelType = {"Aurora", "Imperial", "PowerHaul", "Stratos", "TerraVolt"};
    private String[] colorType = {"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"};
    private JComboBox<String>[] overviewType = new JComboBox[2];
    private JSlider priceSlider = new JSlider(0, 1000000, (1000000) / 2);
    private JLabel RMLabelFrom = new JLabel("RM100,000");
    private JLabel RMLabelTo = new JLabel("RM300,000");

    private String colorCarPicLink = "src/Images/Color/Aurora/PearlWhiteMulti-Coat.png";
    private JLabel[] imgOverviewType = new JLabel[2];


    JFrame frameOverviewPage = new JFrame("EcoMotion Main Page");


    // Constructor
    public OverviewPage() {
        index = 0;
    }


    public void showOverviewPage() {

        // Title
        JPanel titlePanel = new JPanel();
        JLabel brandName = new JLabel("EcoMotion");
        brandName.setFont(new Font("Agency FB", Font.BOLD, 50));
        titlePanel.add(brandName);



        // Preview Car with Image part
        JPanel pictureCarPanel = new JPanel();

        // Image use to adjust the size of image
        Image adjustionCarPicture = carPictures.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
        // Get the latest adjust image
        carPictures.setImage(adjustionCarPicture);

        // Label use to print/ show the image
        labelContainPicture.setIcon(carPictures);
        labelContainPicture.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        JLabel leftButton = new JLabel("<");
        JLabel rightButton = new JLabel(">");
        leftButton.setFont(new Font("Arial", Font.BOLD, 25));
        rightButton.setFont(new Font("Arial", Font.BOLD, 25));
        leftButton.addMouseListener(leftRightButton); // Listener
        rightButton.addMouseListener(leftRightButton);

        // Set color using code
        // the # take from HTML colorcode website
        pictureCarPanel.setBackground(Color.decode("#e3e0e3"));
        pictureCarPanel.add(leftButton);
        pictureCarPanel.add(labelContainPicture);
        pictureCarPanel.add(rightButton);
        pictureCarPanel.setLayout(new FlowLayout());



        // Title Filter
        titleOverview.setFont(new Font("Arial", Font.BOLD, 28));
        titleOverviewPanel.add(titleOverview);
        titleOverviewPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleOverviewPanel.setPreferredSize(new Dimension(200, 45));


        // Black Line Border for Each filter part (Model, Color and Price)
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        JPanel[] overviewTypeNamePanel = new JPanel[3];
        Font overviewTypeFont = new Font("Arial", Font.BOLD, 15);
        // that 3 kotak (model, color, price)
        for (int x = 0; x < 3; x++) {
            overviewTypeNamePanel[x] = new JPanel();
            overviewTypeNamePanel[x].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            overviewTypeNamePanel[x].setPreferredSize(new Dimension(200, 170));
        }

        ImageIcon overviewPictureModel = new ImageIcon(carPictureLink);
        Image adjustionOverviewPicModel = carPictures.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
        ImageIcon overviewPictureColor = new ImageIcon(colorCarPicLink);
        Image adjustionOverviewPicColor = overviewPictureColor.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
        overviewPictureModel.setImage(adjustionOverviewPicModel);
        overviewPictureColor.setImage(adjustionOverviewPicColor);
        // Create 2 label for print image (Model & Color)
        for (int x = 0; x < 2; x++) {
            imgOverviewType[x] = new JLabel();
            // Setup the margin (Space) of the kotak
            imgOverviewType[x].setBorder(BorderFactory.createEmptyBorder(0, 0, 20 , 0));
        }
        imgOverviewType[0].setIcon(overviewPictureModel);
        imgOverviewType[1].setIcon(overviewPictureColor);

        // For model
        // Create title for 3 kotak with blackline
        TitledBorder modelTitleBorder = BorderFactory.createTitledBorder(blackLine, "MODEL");
        // Set/ Assign the titleBorder
        modelTitleBorder.setTitleFont(overviewTypeFont);
        overviewTypeNamePanel[0].setBorder(modelTitleBorder);
        overviewType[0] = new JComboBox<String>(modelType); // Create Combo Box

        // For color
        TitledBorder coLorTitleBorder = BorderFactory.createTitledBorder(blackLine, "COLOR");
        coLorTitleBorder.setTitleFont(overviewTypeFont);
        overviewTypeNamePanel[1].setBorder(coLorTitleBorder);
        overviewType[1] = new JComboBox<String>(colorType); // Create Combo Box

        // For price
        TitledBorder priceTitleBorder = BorderFactory.createTitledBorder(blackLine, "PRICE");
        priceTitleBorder.setTitleFont(overviewTypeFont);
        overviewTypeNamePanel[2].setBorder(priceTitleBorder);

        // From RMXX To RMXXX (Price Range's Label)
        JLabel priceStatementLabel = new JLabel("From");
        JLabel toLabel = new JLabel("To");

        // Combine those 4 labels ("From", "MIN RMXXX", "To", "MAX RMXXX")
        JPanel combineRM = new JPanel();
        combineRM.setLayout(new GridLayout(4, 1, 0, 2));

        priceStatementLabel.setFont(new Font("Arial", Font.BOLD, 20));
        RMLabelFrom.setFont(new Font("Arial", Font.BOLD, 30));
        toLabel.setFont(new Font("Arial", Font.BOLD, 20));
        RMLabelTo.setFont(new Font("Arial", Font.BOLD, 30));


        // like word alligment (sideleft , center, sideright)
        priceStatementLabel.setHorizontalAlignment(SwingConstants.CENTER);
        RMLabelFrom.setHorizontalAlignment(SwingConstants.CENTER);
        toLabel.setHorizontalAlignment(SwingConstants.CENTER);
        RMLabelTo.setHorizontalAlignment(SwingConstants.CENTER);

        combineRM.add(priceStatementLabel);
        combineRM.add(RMLabelFrom);
        combineRM.add(toLabel);
        combineRM.add(RMLabelTo);
        overviewTypeNamePanel[2].add(combineRM, BorderLayout.CENTER);


        JPanel overviewTypePanel = new JPanel();
        overviewTypePanel.setLayout(new GridLayout(1,3,100, 0));
        overviewTypeNamePanel[0].add(imgOverviewType[0]);
        overviewTypeNamePanel[1].add(imgOverviewType[1]);
        // Setup for each bawah part
        for (int x = 0; x < overviewType.length; x++) {
            overviewType[x].setSelectedIndex(0);
            overviewType[x].setPreferredSize(new Dimension(200, 25));
            overviewTypeNamePanel[x].add(overviewType[x]);
            overviewTypePanel.add(overviewTypeNamePanel[x]);
        }
        overviewTypePanel.add(overviewTypeNamePanel[2]);
        overviewType[0].addItemListener(modelEvent);
        overviewType[1].addItemListener(colorEvent);
        overviewTypePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));


        // Button filter part
        JButton overviewButton = new JButton("Order Now");
        overviewButton.setFont(new Font("Arial", Font.BOLD, 25));
        overviewButton.setForeground(Color.WHITE);
        overviewButton.setBackground(Color.black);
        overviewButton.addActionListener(nextPageButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(overviewButton);



        // Panel to combine all the bawah part components
        JPanel overviewPanel = new JPanel();
        overviewPanel.add(titlePanel);
        overviewPanel.add(pictureCarPanel);
        overviewPanel.add(titleOverviewPanel);
        overviewPanel.add(overviewTypePanel);
        overviewPanel.add(buttonPanel);
        overviewPanel.setLayout(new BoxLayout(overviewPanel, BoxLayout.Y_AXIS));



        // Get the screen size using Toolkit
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Frame part
        frameOverviewPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameOverviewPage.pack();
        frameOverviewPage.add(overviewPanel);
        frameOverviewPage.setSize(screenSize.width, screenSize.height);
        frameOverviewPage.setResizable(true);
        frameOverviewPage.setVisible(true);

    }

    // Listener for random picture (< and >) (When mouse click)
    private int trackingNum = 0;
    private MouseListener leftRightButton = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            Random randNum = new Random();
            int randomNum = randNum.nextInt(5) + 1;
            if (trackingNum == randomNum) {
                if (randomNum != 5) {
                    randomNum += 1;
                } else {
                    randomNum = 1;
                }
            }

            trackingNum = randomNum;

            switch (randomNum) {
                case 1:
                    carPictureLink = "src/Images/Preview_n_Model/Car1.png";
                    break;
                case 2:
                    carPictureLink = "src/Images/Preview_n_Model/Car2.png";
                    break;
                case 3:
                    carPictureLink = "src/Images/Preview_n_Model/Car3.png";
                    break;
                case 4:
                    carPictureLink = "src/Images/Preview_n_Model/Car4.png";
                    break;
                case 5:
                    carPictureLink = "src/Images/Preview_n_Model/Car5.png";
                    break;
            }

            ImageIcon updatePicture = new ImageIcon(carPictureLink);
            Image adjustionApdatedPic = updatePicture.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
            updatePicture.setImage(adjustionApdatedPic);

            labelContainPicture.setIcon(updatePicture);
        }
    };

    // Listener for model overview part
    // Control model image, color image (Corresponding color for that model) and relevant price
    private ItemListener modelEvent = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                int selectedModelIndex = overviewType[0].getSelectedIndex();

                overviewType[1].removeAllItems();

                // Combo box's item (from 0 to 4)
                switch (selectedModelIndex) {
                    case 0:
                        index = 0;
                        carPictureLink = "src/Images/Preview_n_Model/Car1.png";
                        colorCarPicLink = "src/Images/Color/Aurora/PearlWhiteMulti-Coat.png";
                        colorType = new String[]{"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"};
                        RMLabelFrom.setText("RM100,000"); // Min Price
                        RMLabelTo.setText("RM300,000"); // Max Price
                        break;
                    case 1:
                        index = 1;
                        carPictureLink = "src/Images/Preview_n_Model/Car2.png";
                        colorCarPicLink = "src/Images/Color/Imperial/PearlWhiteMulti-Coat.png";
                        colorType = new String[]{"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Solid Black", "Ultra Red"};
                        RMLabelFrom.setText("RM500,000");
                        RMLabelTo.setText("RM1100,000");
                        break;
                    case 2:
                        index = 2;
                        carPictureLink = "src/Images/Preview_n_Model/Car3.png";
                        colorCarPicLink = "src/Images/Color/PowerHaul/PearlWhiteMulti-Coat.png";
                        colorType = new String[]{"Quicksilver"};
                        RMLabelFrom.setText("RM200,000");
                        RMLabelTo.setText("RM500,000");
                        break;
                    case 3:
                        index = 3;
                        carPictureLink = "src/Images/Preview_n_Model/Car4.png";
                        colorCarPicLink = "src/Images/Color/Stratos/PearlWhiteMulti-Coat.png";
                        colorType = new String[]{"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"};
                        RMLabelFrom.setText("RM1000,000");
                        RMLabelTo.setText("RM1000,000");
                        break;
                    case 4:
                        index = 4;
                        carPictureLink = "src/Images/Preview_n_Model/Car5.png";
                        colorCarPicLink = "src/Images/Color/TerraVolt/QuickSilver.png";
                        colorType = new String[]{"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Solid Black", "Ultra Red"};
                        RMLabelFrom.setText("RM400,000");
                        RMLabelTo.setText("RM600,000");
                        break;
                }

                for (int i = 0; i < colorType.length; i++) {
                    overviewType[1].addItem(colorType[i]);
                }

                // Setup image and print
                // Setup price
                ImageIcon selectedCarPicture = new ImageIcon(carPictureLink);
                ImageIcon selectedCarColorPicture = new ImageIcon(colorCarPicLink);
                Image adjustionUpdatedPic = selectedCarPicture.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                Image adjustionColorUpdatedPic = selectedCarColorPicture.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                selectedCarPicture.setImage(adjustionUpdatedPic);
                selectedCarColorPicture.setImage(adjustionColorUpdatedPic);
                imgOverviewType[0].setIcon(selectedCarPicture);
                imgOverviewType[1].setIcon(selectedCarColorPicture);

            }
        }
    };

    // Listener for color overview part
    // Only control color
    private ItemListener colorEvent = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {

                int selectedColorIndex = overviewType[1].getSelectedIndex();

                // Color combo box (When white click, show white)
                if (index == 0) {
                    switch (selectedColorIndex) {
                        case 0:
                            colorCarPicLink = "src/Images/Color/Aurora/PearlWhiteMulti-Coat.png";
                            break;
                        case 1:
                            colorCarPicLink = "src/Images/Color/Aurora/DeepBlueMetallic.png";
                            break;
                        case 2:
                            colorCarPicLink = "src/Images/Color/Aurora/StealthGrey.png";
                            break;
                        case 3:
                            colorCarPicLink = "src/Images/Color/Aurora/Quicksilver.png";
                            break;
                        case 4:
                            colorCarPicLink = "src/Images/Color/Aurora/UltraRed.png";
                            break;
                    }
                } else if (index == 1) {
                    switch (selectedColorIndex) {
                        case 0:
                            colorCarPicLink = "src/Images/Color/Imperial/PearlWhiteMulti-Coat.png";
                            break;
                        case 1:
                            colorCarPicLink = "src/Images/Color/Imperial/DeepBlueMetallic.png";
                            break;
                        case 2:
                            colorCarPicLink = "src/Images/Color/Imperial/StealthGrey.png";
                            break;
                        case 3:
                            colorCarPicLink = "src/Images/Color/Imperial/SolidBlack.png";
                            break;
                        case 4:
                            colorCarPicLink = "src/Images/Color/Imperial/UltraRed.png";
                            break;
                    }
                } else if (index == 2) {
                    colorCarPicLink = "src/Images/Color/PowerHaul/Quicksilver.png";
                } else if (index == 3) {
                    switch (selectedColorIndex) {
                        case 0:
                            colorCarPicLink = "src/Images/Color/Stratos/PearlWhiteMulti-Coat.png";
                            break;
                        case 1:
                            colorCarPicLink = "src/Images/Color/Stratos/DeepBlueMetallic.png";
                            break;
                        case 2:
                            colorCarPicLink = "src/Images/Color/Stratos/StealthGrey.png";
                            break;
                        case 3:
                            colorCarPicLink = "src/Images/Color/Stratos/QuickSilver.png";
                            break;
                        case 4:
                            colorCarPicLink = "src/Images/Color/Stratos/UltraRed.png";
                            break;
                    }
                } else if (index == 4) {
                    switch (selectedColorIndex) {
                        case 0:
                            colorCarPicLink = "src/Images/Color/TerraVolt/PearlWhiteMulti-Coat.png";
                            break;
                        case 1:
                            colorCarPicLink = "src/Images/Color/TerraVolt/DeepBlueMetallic.png";
                            break;
                        case 2:
                            colorCarPicLink = "src/Images/Color/TerraVolt/StealthGrey.png";
                            break;
                        case 3:
                            colorCarPicLink = "src/Images/Color/TerraVolt/SolidBlack.png";
                            break;
                        case 4:
                            colorCarPicLink = "src/Images/Color/TerraVolt/UltraRed.png";
                            break;
                    }
                }

                // Show the color
                ImageIcon selectedCarColorPicture = new ImageIcon(colorCarPicLink);
                Image adjustionColorUpdatedPic = selectedCarColorPicture.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                selectedCarColorPicture.setImage(adjustionColorUpdatedPic);
                imgOverviewType[1].setIcon(selectedCarColorPicture);
            }
        }
    };

    // click Order now button to next page (Welcome page)
    ActionListener nextPageButton = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frameOverviewPage.setVisible(false);
            WelcomePage welcomepage = new WelcomePage();
            welcomepage.createWelcomePage();
        }
    };


    // Main
    public static void main(String[] agrs) {

        OverviewPage car = new OverviewPage();
        car.showOverviewPage();

    }

}