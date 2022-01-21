package tests;

import MVC.*;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleControllerTest {

    @Test
    void addItem() throws eCommerceException {
        Controller vm = new Controller();
        ProgramView programView = new ProgramView();
        DBModel model = new DBModel();
        vm.setModel(model);
        vm.setView(programView);
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(false);
        String temp = "'Name', 'category', '777'";
        Item item = new Item(temp);
        vm.addItem(item);
        DefaultTableModel data = model.getItems("username");
        StringBuffer actualItem = new StringBuffer();
        for (int i = 1; i < 5; i++) {
            actualItem.append(data.getValueAt(data.getRowCount()-1, i).toString());
            if (i != 4) {
                actualItem.append(", ");
            }
        }
        String actualItem1 = actualItem.toString();
        System.out.println("actual: " + actualItem);
        String expectedItem = "username, Name, category, 777";
        System.out.println("expected: " + expectedItem);

        assertEquals(expectedItem, actualItem1);
    }

    @Test
    void getItemBetween() throws eCommerceException {
        Controller vm = new Controller();
        ProgramView programView = new ProgramView();
        DBModel model = new DBModel();
        vm.setModel(model);
        vm.setView(programView);
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(false);
        String dates = "'2021-12-14' AND '2021-12-14'";
        Item item = new Item(dates);
        vm.getItemBetween(item, "username");
        String actualDates = vm.getLatestDates();
        assertEquals(dates, actualDates);


    }

    @Test
    void addUser() throws eCommerceException, SQLException {
        Controller vm = new Controller();
        ProgramView programView = new ProgramView();
        DBModel model = new DBModel();
        vm.setModel(model);
        vm.setView(programView);
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(false);
        String username = "eli";
        Item item = new Item(username);
        assertEquals(false, vm.addUser(item));
        //we know that 'eli' exists in the users DB, so we expect false.
    }
}
