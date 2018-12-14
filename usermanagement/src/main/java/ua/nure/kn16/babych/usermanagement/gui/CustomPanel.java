package ua.nure.kn16.babych.usermanagement.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class CustomPanel extends JPanel implements ActionListener {

    protected JButton makeButton(JButton button, String text, String function) {
        if (button == null) {
            button = new JButton();
            button.setText(text);
            button.setName(function + "Button");
            button.setActionCommand(function);
            button.addActionListener(this);
        }
        return button;
    }

    protected JTextField makeField(JTextField field, String name) {
        if (field == null) {
            field = new JTextField();
            field.setName(name + "Field");
        }
        return field;
    }

    protected void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }
}
