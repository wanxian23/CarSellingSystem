import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

public class CarSellingSystem implements ItemListener {

    JPanel titlelPanel = new JPanel();
    JLabel brandName = new JLabel("EcoMotion");
    Font fontBold = new Font("Agency FB", Font.BOLD, 30);



    private String carPictureLink = "/Images/Preview_n_Model/Car1.png";
    JPanel pictureCarPanel = new JPanel();
    ImageIcon carPictures = new ImageIcon(getClass().getResource(carPictureLink));
    JLabel labelContainPicture = new JLabel();

    JLabel leftButton = new JLabel("<");
    JLabel rightButton = new JLabel(">");



    JPanel titleFilterPanel = new JPanel();
    JLabel titleFilter = new JLabel("Filter The Info of Car");
    Font filterFont = new Font("Arial", Font.BOLD, 25);



    JPanel filterTypePanel = new JPanel();
    String[] modelType = {"Aurora", "TerraVolt", "Stratos", "Imperial", "PwerHaul"};
    String[] colorType = {"Pearl White Multi-Coat", "Deep Blue Metallic", "Stealth Grey", "Quicksilver", "Ultra Red"};
    String[] priceType = {"RM10000 - RM200000", "RM500000 - RM10000000"};
    JComboBox<String>[] filterType = new JComboBox[3];

    private String colorCarPic = "";
    ImageIcon filterPictures = new ImageIcon(getClass().getResource(carPictureLink));
    JLabel[] imgFilterType = new JLabel[2];


    JPanel buttonPanel = new JPanel();
    JButton filterButton = new JButton("FILTER");



    JPanel mainPagePanel = new JPanel();



    JFrame frame = new JFrame("EcoMotion Main Page");
    // Get the screen size using Toolkit
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public void mainPage() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String fonts[] = ge.getAvailableFontFamilyNames();

        // Getting the font family names
        for (String i : fonts) {
            System.out.println(i + " ");
        }



        brandName.setFont(fontBold);
        titlelPanel.add(brandName);



        Image adjustionCarPicture = carPictures.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
        carPictures.setImage(adjustionCarPicture);

        labelContainPicture.setIcon(carPictures);
        labelContainPicture.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        leftButton.setFont(fontBold);
        rightButton.setFont(fontBold);
        leftButton.addMouseListener(leftRightButton);
        rightButton.addMouseListener(leftRightButton);

        pictureCarPanel.setBackground(Color.decode("#e3e0e3"));
        pictureCarPanel.add(leftButton);
        pictureCarPanel.add(labelContainPicture);
        pictureCarPanel.add(rightButton);
        pictureCarPanel.setLayout(new FlowLayout());



        titleFilter.setFont(filterFont);
        titleFilterPanel.add(titleFilter);
        titleFilterPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        Border blackLine = BorderFactory.createLineBorder(Color.black);
        JPanel[] filterTypeNamePanel = new JPanel[3];
        Font filterTypeFont = new Font("Agency FB", Font.BOLD, 15);
        for (int x = 0; x < 3; x++) {
            filterTypeNamePanel[x] = new JPanel();
        }

        Image adjustionFilterPic = carPictures.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
        filterPictures.setImage(adjustionFilterPic);
        for (int x = 0; x < 2; x++) {
            imgFilterType[x] = new JLabel();
            imgFilterType[x].setIcon(filterPictures);
            imgFilterType[x].setBorder(BorderFactory.createEmptyBorder(0, 0, 20 , 0));
        }

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
        filterType[2] = new JComboBox<String>(priceType);

        filterTypePanel.setLayout(new GridLayout(1,3,100, 0));
        filterTypeNamePanel[0].add(imgFilterType[0]);
        filterTypeNamePanel[1].add(imgFilterType[1]);
        for (int x = 0; x < 3; x++) {
            filterType[x].setSelectedIndex(0);
            filterType[x].setPreferredSize(new Dimension(300, 25));
            filterTypeNamePanel[x].setLayout(new FlowLayout());
            filterTypeNamePanel[x].setPreferredSize(new Dimension(0, 150));
            filterTypeNamePanel[x].add(filterType[x]);
            filterTypePanel.add(filterTypeNamePanel[x]);
        }
        filterType[0].addItemListener(this);
        filterTypePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));



        Font fontButton = new Font("Agency FB", Font.BOLD, 25);
        filterButton.setFont(fontButton);
        filterButton.setForeground(Color.WHITE);
        filterButton.setBackground(Color.black);
        buttonPanel.add(filterButton);



        mainPagePanel.add(titlelPanel);
        mainPagePanel.add(pictureCarPanel);
        mainPagePanel.add(titleFilterPanel);
        mainPagePanel.add(filterTypePanel);
        mainPagePanel.add(buttonPanel);
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(mainPagePanel);
        frame.setSize(screenSize.width, screenSize.height);
        frame.setResizable(true);
        frame.setVisible(true);

    }

    int trackingNum = 0;
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
            System.out.println(randomNum);

            switch (randomNum) {
                case 1:
                    carPictureLink = "Images/Preview_n_Model/Car1.png";
                    break;
                case 2:
                    carPictureLink = "Images/Preview_n_Model/Car2.png";
                    break;
                case 3:
                    carPictureLink = "Images/Preview_n_Model/Car3.png";
                    break;
                case 4:
                    carPictureLink = "Images/Preview_n_Model/Car4.png";
                    break;
                case 5:
                    carPictureLink = "Images/Preview_n_Model/Car5.png";
                    break;
            }

            ImageIcon updatePicture = new ImageIcon(getClass().getResource(carPictureLink));
            Image adjustionApdatedPic = updatePicture.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
            updatePicture.setImage(adjustionApdatedPic);

            labelContainPicture.setIcon(updatePicture);
        }
    };

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {

            int selectedModelIndex = filterType[0].getSelectedIndex();
            System.out.println(selectedModelIndex);
            switch (selectedModelIndex) {
                case 0:
                    carPictureLink = "Images/Preview_n_Model/Car1.png";
                    break;
                case 1:
                    carPictureLink = "Images/Preview_n_Model/Car2.png";
                    break;
                case 2:
                    carPictureLink = "Images/Preview_n_Model/Car3.png";
                    break;
                case 3:
                    carPictureLink = "Images/Preview_n_Model/Car4.png";
                    break;
                case 4:
                    carPictureLink = "Images/Preview_n_Model/Car5.png";
                    break;
            }

            ImageIcon selectedCarPicture = new ImageIcon(getClass().getResource(carPictureLink));
            Image adjustionApdatedPic = selectedCarPicture.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
            selectedCarPicture.setImage(adjustionApdatedPic);
            imgFilterType[0].setIcon(selectedCarPicture);
        }
    }

    public static void main(String[] agrs) {

        CarSellingSystem car = new CarSellingSystem();
        car.mainPage();

    }

}
