package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempMainPanel extends JPanel {
    private TempMainFrame mainFrame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public TempMainPanel(TempMainFrame mainFrame){
        this.mainFrame = mainFrame;

        initComponents();
        initGUI();
        registerListeners();
    }

    private void initComponents() {
        txtUsername = new JTextField();
        txtUsername.setSize(new Dimension(200, 25));
        txtUsername.setPreferredSize(new Dimension(200, 25));
        txtUsername.setMinimumSize(new Dimension(200, 25));

        txtPassword = new JPasswordField();
        txtPassword.setSize(new Dimension(200, 25));
        txtPassword.setPreferredSize(new Dimension(200, 25));
        txtPassword.setMinimumSize(new Dimension(200, 25));

        btnLogin = new JButton("Login");
    }

    private void initGUI() {
        setSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(txtUsername, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(txtPassword, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(btnLogin, gbc);
    }

    private void registerListeners() {
        btnLogin.addActionListener(new BtnLoginListener());
    }

    public void updateGUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel lblLoggedIn = new JLabel("Logged in");

        remove(txtUsername);
        remove(txtPassword);
        remove(btnLogin);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(lblLoggedIn);

        repaint();
        revalidate();
    }

    private class BtnLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = txtUsername.getText();
            char[] password = txtPassword.getPassword();

            mainFrame.setUser(username, password);
        }
    }
}
