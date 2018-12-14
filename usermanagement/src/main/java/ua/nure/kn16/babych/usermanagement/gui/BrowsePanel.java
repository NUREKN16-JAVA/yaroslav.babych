package ua.nure.kn16.babych.usermanagement.gui;

import ua.nure.kn16.babych.usermanagement.db.DBException;
import ua.nure.kn16.babych.usermanagement.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class BrowsePanel extends CustomPanel {

    private JScrollPane tablePanel;
    private JTable userTable;
    private JPanel buttonsPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton detailsButton;
    private MainFrame parent;

    public BrowsePanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        this.setName("browsePanel");
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(), BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            buttonsPanel = new JPanel();

            buttonsPanel.add(addButton = makeButton(addButton, Message.getString("add"), "add"), null);
            buttonsPanel.add(editButton = makeButton(editButton, Message.getString("edit"), "edit"), null);
            buttonsPanel.add(deleteButton = makeButton(deleteButton, Message.getString("delete"), "delete"), null);
            buttonsPanel.add(detailsButton = makeButton(detailsButton, Message.getString("details"), "details"), null);
        }
        return buttonsPanel;
    }

    private JScrollPane getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName("userTable");
        }
        return userTable;
    }

    public void initTable() {
        try {
            userTable.setModel(new UserTableModel(parent.getDao().findAll()));
        } catch (DBException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), Message.getString("error"), JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int row = getUserTable().getSelectedRow();
        Long userid;
        switch (actionEvent.getActionCommand()) {
            case "add":
                this.setVisible(false);
                parent.showAddPanel();
                break;
            case "edit":
                if(row >= 0) {
                    userid = (Long) getUserTable().getValueAt(row, 0);
                    parent.showEditPanel(userid);
                    this.setVisible(false);
                }
                break;
            case "details":
                if(row >= 0) {
                    userid = (Long) getUserTable().getValueAt(row, 0);
                    parent.showDetailsPanel(userid);
                    this.setVisible(false);
                }
                break;
            case "delete":
                if(row >= 0) {
                    userid = (Long) getUserTable().getValueAt(row, 0);
                    int answer = JOptionPane.showConfirmDialog(this, Message.getString("deleteuser"), Message.getString("confirmdeleting"), JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        try {
                            parent.getDao().delete(userid);

                        } catch (DBException e) {
                            JOptionPane.showMessageDialog(this, e.getMessage(), Message.getString("error"), JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                break;
        }

    }
}
