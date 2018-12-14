package ua.nure.kn16.babych.usermanagement.gui;

import ua.nure.kn16.babych.usermanagement.User;
import ua.nure.kn16.babych.usermanagement.db.DBException;
import ua.nure.kn16.babych.usermanagement.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditPanel extends CustomPanel {


    private JPanel fieldsPanel;
    private JPanel buttonsPanel;

    private JButton okButton;
    private JButton cancelButton;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;

    private MainFrame parent;

    private Long userId;

    EditPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        this.setName("editPanel");
        this.setLayout(new BorderLayout());
        this.add(getFieldsPanel(), BorderLayout.NORTH);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    private  JPanel getFieldsPanel() {
        if (fieldsPanel == null) {
            fieldsPanel = new JPanel();
            fieldsPanel.setLayout(new GridLayout(3, 2));

            firstNameField = makeField(firstNameField, "firstName");
            addLabeledField(fieldsPanel, Message.getString("firstname"), firstNameField);
            lastNameField = makeField(lastNameField, "lastName");
            addLabeledField(fieldsPanel, Message.getString("lastname"), lastNameField);
            dateOfBirthField = makeField(dateOfBirthField, "dateOfBirth");
            addLabeledField(fieldsPanel, Message.getString("dateofbirth"), dateOfBirthField);
        }
        return fieldsPanel;
    }

    private JPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            buttonsPanel = new JPanel();
            buttonsPanel.add(okButton = makeButton(okButton, Message.getString("edit"), "ok"), null);
            buttonsPanel.add(cancelButton = makeButton(cancelButton, Message.getString("cancel"), "cancel"), null);
        }
        return buttonsPanel;
    }


    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        dateOfBirthField.setText("");
        dateOfBirthField.setBackground(Color.gray);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "ok":
                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
                try {
                    User user = new User(userId,
                            firstNameField.getText(),
                            lastNameField.getText(),
                            df.parse(dateOfBirthField.getText()));
                    parent.getDao().update(user);
                    this.setVisible(false);
                    parent.showBrowsePanel();
                    clearFields();
                } catch (ParseException e) {
                    dateOfBirthField.setBackground(Color.RED);
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), Message.getString("error"), JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "cancel":
                this.setVisible(false);
                parent.showBrowsePanel();
                clearFields();
                break;
        }
    }
}
