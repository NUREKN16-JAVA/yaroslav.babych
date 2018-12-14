package ua.nure.kn16.babych.usermanagement.gui;

import ua.nure.kn16.babych.usermanagement.User;
import ua.nure.kn16.babych.usermanagement.util.Message;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private List<User> users;
    private static final String[] COLUMN_NAMES = {Message.getString("id"), Message.getString("firstname"), Message.getString("lastname")};
    private static final  Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};

    UserTableModel(Collection<User> users) {
        super();
        this.users = new ArrayList(users);
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
        User user = (User) users.get(row);
        switch(column){
            case 0:
                return user.getId();
            case 1:
                return user.getFirstName();
            case 2:
                return user.getLastName();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return COLUMN_CLASSES[i];
    }

    @Override
    public String getColumnName(int i) {
        return COLUMN_NAMES[i];
    }
}
