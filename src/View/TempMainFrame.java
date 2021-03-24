package View;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class TempMainFrame extends JFrame {
    private TempMainPanel mainPanel;
    private MainController mainController;

    public TempMainFrame(MainController mainController){
        this.mainController = mainController;
        this.mainPanel = new TempMainPanel(this);

        initComponents();
        initGUI();
    }

    private void initComponents() {
        setTitle("Temp");
        setSize(new Dimension(400,400));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400,400));
        setVisible(true);
        setResizable(false);
        setLayout(new GridBagLayout());
    }

    private void initGUI() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(mainPanel, gbc);
    }

    public void setUser(String username, char[] password) {
        mainController.setUser(username, password);
    }


    public void updateLogin() {
        mainPanel.updateGUI();
    }

    public void showMsg(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
}
