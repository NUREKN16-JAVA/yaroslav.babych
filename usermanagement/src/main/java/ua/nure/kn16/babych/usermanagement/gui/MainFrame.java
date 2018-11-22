package ua.nure.kn16.babych.usermanagement.gui;

import ua.nure.kn16.babych.usermanagement.db.DaoFactory;
import ua.nure.kn16.babych.usermanagement.db.UserDAO;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static final int FRAME_WIDTH = 800, FRAME_HEIGHT = 600;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private  UserDAO dao;


    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Usermanagement"); // TODO Localize
        this.setContentPane(getContentPanel());
    }

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel();
        }
        return browsePanel;
    }

    public MainFrame() {
        super();
        dao = DaoFactory.getInstance().getUserDao();

        initialize();
    }

    public static void main(String[] arg) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
