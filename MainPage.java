import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainPage {

    // Preview Car with Image Part
    private String carPictureLink = "src/Images/Preview_n_Model/Car1.png";
    private ImageIcon carPictures = new ImageIcon(carPictureLink);
    private JLabel labelContainPicture = new JLabel();



    // Filter Title Part
    private JPanel titleFilterPanel = new JPanel();
    private JLabel titleFilter = new JLabel("Overview of Cars");



    // Filter Part
    private int index;
    private String[] modelType = {"Aurora", "Imperial", "PowerHaul", "Stratos", "TerraVolt"};
    private String[] colorType = {"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"};
    private JComboBox<String>[] filterType = new JComboBox[2];
    private JSlider priceSlider = new JSlider(0, 1000000, (1000000) / 2);
    private JLabel RMLabelFrom = new JLabel("RM100,000");
    private JLabel RMLabelTo = new JLabel("RM300,000");

    private String colorCarPicLink = "src/Images/Color/Aurora/PearlWhiteMulti-Coat.png";
    private JLabel[] imgFilterType = new JLabel[2];


    JFrame frameMainPage = new JFrame("EcoMotion Main Page");


    // Constructor
    public MainPage() {
        index = 0;
    }


    public void showMainPage() {

        // Title
        JPanel titlelPanel = new JPanel();
        JLabel brandName = new JLabel("EcoMotion");
        brandName.setFont(new Font("Agency FB", Font.BOLD, 50));
        titlelPanel.add(brandName);



        // Preview Car with Image part
        JPanel pictureCarPanel = new JPanel();
        Image adjustionCarPicture = carPictures.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
        carPictures.setImage(adjustionCarPicture);

        labelContainPicture.setIcon(carPictures);
        labelContainPicture.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        JLabel leftButton = new JLabel("<");
        JLabel rightButton = new JLabel(">");
        leftButton.setFont(new Font("Arial", Font.BOLD, 25));
        rightButton.setFont(new Font("Arial", Font.BOLD, 25));
        leftButton.addMouseListener(leftRightButton);
        rightButton.addMouseListener(leftRightButton);

        pictureCarPanel.setBackground(Color.decode("#e3e0e3"));
        pictureCarPanel.add(leftButton);
        pictureCarPanel.add(labelContainPicture);
        pictureCarPanel.add(rightButton);
        pictureCarPanel.setLayout(new FlowLayout());



        // Title Filter
        titleFilter.setFont(new Font("Arial", Font.BOLD, 28));
        titleFilterPanel.add(titleFilter);
        titleFilterPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleFilterPanel.setPreferredSize(new Dimension(200, 45));


        // Each filter part (Model, Color and Price)
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        JPanel[] filterTypeNamePanel = new JPanel[3];
        Font filterTypeFont = new Font("Arial", Font.BOLD, 15);
        for (int x = 0; x < 3; x++) {
            filterTypeNamePanel[x] = new JPanel();
            filterTypeNamePanel[x].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            filterTypeNamePanel[x].setPreferredSize(new Dimension(200, 170));
        }

        ImageIcon filterPictureModel = new ImageIcon(carPictureLink);
        Image adjustionFilterPicModel = carPictures.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
        ImageIcon filterPictureColor = new ImageIcon(colorCarPicLink);
        Image adjustionFilterPicColor = filterPictureColor.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
        filterPictureModel.setImage(adjustionFilterPicModel);
        filterPictureColor.setImage(adjustionFilterPicColor);
        for (int x = 0; x < 2; x++) {
            imgFilterType[x] = new JLabel();
            imgFilterType[x].setBorder(BorderFactory.createEmptyBorder(0, 0, 20 , 0));
        }
        imgFilterType[0].setIcon(filterPictureModel);
        imgFilterType[1].setIcon(filterPictureColor);

        TitledBorder modelTitleBorder = BorderFactory.createTitledBorder(blackLine, "MODEL");
        modelTitleBorder.setTitleFont(filterTypeFont);
        filterTypeNamePanel[0].setBorder(modelTitleBorder);
        filterType[0] = new JComboBox<String>(modelType);

        TitledBorder coLorTitleBorder = BorderFactory.createTitledBorder(blackLine, "COLOR");
        coLorTitleBorder.setTitleFont(filterTypeFont);
        filterTypeNamePanel[1].setBorder(coLorTitleBorder);
        filterType[1] = new JComboBox<String>(colorType);

        TitledBorder priceTitleBorder = BorderFactory.createTitledBorder(blackLine, "PRICE");
        priceTitleBorder.setTitleFont(filterTypeFont);
        filterTypeNamePanel[2].setBorder(priceTitleBorder);

        JLabel priceStatementLabel = new JLabel("From");
        JLabel toLabel = new JLabel("To");

        JPanel combineRM = new JPanel();
        combineRM.setLayout(new GridLayout(4, 1, 0, 2));

        priceStatementLabel.setFont(new Font("Arial", Font.BOLD, 20));
        RMLabelFrom.setFont(new Font("Arial", Font.BOLD, 30));
        toLabel.setFont(new Font("Arial", Font.BOLD, 20));
        RMLabelTo.setFont(new Font("Arial", Font.BOLD, 30));

        priceStatementLabel.setHorizontalAlignment(SwingConstants.CENTER);
        RMLabelFrom.setHorizontalAlignment(SwingConstants.CENTER);
        toLabel.setHorizontalAlignment(SwingConstants.CENTER);
        RMLabelTo.setHorizontalAlignment(SwingConstants.CENTER);

        combineRM.add(priceStatementLabel);
        combineRM.add(RMLabelFrom);
        combineRM.add(toLabel);
        combineRM.add(RMLabelTo);
        filterTypeNamePanel[2].add(combineRM, BorderLayout.CENTER);

        JPanel filterTypePanel = new JPanel();
        filterTypePanel.setLayout(new GridLayout(1,3,100, 0));
        filterTypeNamePanel[0].add(imgFilterType[0]);
        filterTypeNamePanel[1].add(imgFilterType[1]);
        for (int x = 0; x < filterType.length; x++) {
            filterType[x].setSelectedIndex(0);
            filterType[x].setPreferredSize(new Dimension(200, 25));
            filterTypeNamePanel[x].add(filterType[x]);
            filterTypePanel.add(filterTypeNamePanel[x]);
        }
        filterTypePanel.add(filterTypeNamePanel[2]);
        filterType[0].addItemListener(modelEvent);
        filterType[1].addItemListener(colorEvent);
        filterTypePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));


        // Button filter part
        JButton filterButton = new JButton("Order Now");
        filterButton.setFont(new Font("Arial", Font.BOLD, 25));
        filterButton.setForeground(Color.WHITE);
        filterButton.setBackground(Color.black);
        filterButton.addActionListener(nextPageButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(filterButton);



        // Panel to combine all the filter part components
        JPanel mainPagePanel = new JPanel();
        mainPagePanel.add(titlelPanel);
        mainPagePanel.add(pictureCarPanel);
        mainPagePanel.add(titleFilterPanel);
        mainPagePanel.add(filterTypePanel);
        mainPagePanel.add(buttonPanel);
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));



        // Get the screen size using Toolkit
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Frame part
        frameMainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMainPage.pack();
        frameMainPage.add(mainPagePanel);
        frameMainPage.setSize(screenSize.width, screenSize.height);
        frameMainPage.setResizable(true);
        frameMainPage.setVisible(true);

    }

    // Listener for random picture
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

    // Listener for model filter part
    private ItemListener modelEvent = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                int selectedModelIndex = filterType[0].getSelectedIndex();

                filterType[1].removeAllItems();

                switch (selectedModelIndex) {
                    case 0:
                        index = 0;
                        carPictureLink = "src/Images/Preview_n_Model/Car1.png";
                        colorCarPicLink = "src/Images/Color/Aurora/PearlWhiteMulti-Coat.png";
                        colorType = new String[]{"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"};
                        RMLabelFrom.setText("RM100,000");
                        RMLabelTo.setText("RM300,000");
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
                    filterType[1].addItem(colorType[i]);
                }

                ImageIcon selectedCarPicture = new ImageIcon(carPictureLink);
                ImageIcon selectedCarColorPicture = new ImageIcon(colorCarPicLink);
                Image adjustionUpdatedPic = selectedCarPicture.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                Image adjustionColorUpdatedPic = selectedCarColorPicture.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                selectedCarPicture.setImage(adjustionUpdatedPic);
                selectedCarColorPicture.setImage(adjustionColorUpdatedPic);
                imgFilterType[0].setIcon(selectedCarPicture);
                imgFilterType[1].setIcon(selectedCarColorPicture);

            }
        }
    };

    // Listener for color filter part
    private ItemListener colorEvent = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                int selectedColorIndex = filterType[1].getSelectedIndex();

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

                ImageIcon selectedCarColorPicture = new ImageIcon(colorCarPicLink);
                Image adjustionColorUpdatedPic = selectedCarColorPicture.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                selectedCarColorPicture.setImage(adjustionColorUpdatedPic);
                imgFilterType[1].setIcon(selectedCarColorPicture);
            }
        }
    };

    // Listener for price filter part
    ChangeListener priceEvent = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            RMLabelFrom.setText("RM " + priceSlider.getValue());
        }
    };

    ActionListener nextPageButton = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frameMainPage.setVisible(false);
            WelcomePage welcomepage = new WelcomePage();
            welcomepage.createWelcomePage();
        }
    };


    // Main
    public static void main(String[] agrs) {

        MainPage car = new MainPage();
        car.showMainPage();

    }

}
