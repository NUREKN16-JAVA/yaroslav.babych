package ua.nure.kn16.babych.usermanagement.gui;

import ua.nure.kn16.babych.usermanagement.User;
import ua.nure.kn16.babych.usermanagement.db.DBException;
import ua.nure.kn16.babych.usermanagement.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DetailsPanel extends CustomPanel {

    JPanel infoPanel;
    JPanel buttonsPanel;

    JButton okButton;

    MainFrame parent;

    Long userId;

    DetailsPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    public void setId(Long userId) {
        this.userId = userId;
        this.add(getInfoPanel(), BorderLayout.NORTH);
    }

    private void initialize() {
        this.setName("detailsPanel");
        this.setLayout(new BorderLayout());
        //this.add(getInfoPanel(), BorderLayout.NORTH);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    private JPanel getInfoPanel() {
        if (infoPanel == null) {
            infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(4, 2));

            try {
                User user = parent.getDao().find(userId);
                infoPanel.add(new JLabel(Message.getString("id")));
                infoPanel.add(new JLabel(user.getId().toString()));
                infoPanel.add(new JLabel(Message.getString("firstname")));
                infoPanel.add(new JLabel(user.getFirstName()));
                infoPanel.add(new JLabel(Message.getString("lastname")));
                infoPanel.add(new JLabel(user.getLastName()));
                infoPanel.add(new JLabel(Message.getString("dateofbirth")));
                infoPanel.add(new JLabel(user.getDateOfBirth().toString()));

            } catch (DBException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), Message.getString("error"), JOptionPane.ERROR_MESSAGE);
            }

        }
        return infoPanel;
    }

    private JPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            buttonsPanel = new JPanel();
            buttonsPanel.add(okButton = makeButton(okButton, Message.getString("ok"), "ok"));
        }
        return buttonsPanel;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("ok")) {
            this.setVisible(false);
            parent.showBrowsePanel();
        }
    }
}
