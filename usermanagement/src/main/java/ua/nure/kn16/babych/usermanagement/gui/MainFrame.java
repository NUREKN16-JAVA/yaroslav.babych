package ua.nure.kn16.babych.usermanagement.gui;

import ua.nure.kn16.babych.usermanagement.db.DaoFactory;
import ua.nure.kn16.babych.usermanagement.db.UserDAO;
import ua.nure.kn16.babych.usermanagement.util.Message;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static final int FRAME_WIDTH = 800, FRAME_HEIGHT = 600;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private JPanel addPanel;
    private JPanel editPanel;
    private JPanel detailsPanel;
    private  UserDAO dao;


    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Message.getString("usermanagement"));
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
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel)browsePanel).initTable();
        return browsePanel;
    }

    private JPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }
        return addPanel;
    }

    private JPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }

    private JPanel getDetailsPanel() {
        if (detailsPanel == null) {
            detailsPanel = new DetailsPanel(this);
        }
        return detailsPanel;
    }

    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }



    public MainFrame() {
        super();
        dao = DaoFactory.getInstance().getUserDao();

        initialize();
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    public static void main(String[] arg) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }

    public void showEditPanel(Long userId) {
        showPanel(getEditPanel());
        ((EditPanel)getEditPanel()).setUserId(userId);
    }

    public void showDetailsPanel(Long userId) {
        showPanel(getDetailsPanel());
        ((DetailsPanel)getDetailsPanel()).setId(userId);
    }

    public UserDAO getDao() {
        return dao;
    }


}
