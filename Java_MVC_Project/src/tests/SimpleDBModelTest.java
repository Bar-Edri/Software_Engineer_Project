
package tests;

import MVC.*;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleDBModelTest {

    @Test
    void getItemsDates() throws eCommerceException {
        Controller vm = new Controller();
        ProgramView programView = new ProgramView();
        DBModel model = new DBModel();
        vm.setModel(model);
        vm.setView(programView);
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(false); // Seller
        String datesString = "'2022-01-01' AND '2022-02-30'";
        Item item = new Item(datesString);
        DefaultTableModel data = model.getItemsDates(item, "eli");
        ArrayList<String> dates = new ArrayList<String>();
        String[] firstDate = new String[3];
        firstDate[0] = datesString.substring(1,5); // Year
        firstDate[1] = datesString.substring(6,8); // Month
        firstDate[2] = datesString.substring(9,11); // Day
        String[] secondDate = new String[3];
        secondDate[0] = datesString.substring(18,22);
        secondDate[1] = datesString.substring(23,25);
        secondDate[2] = datesString.substring(26,28);
        boolean[] answers = new boolean[data.getRowCount()];
        Arrays.fill(answers,false);
        for(int i = 0; i<data.getRowCount();i++){
            dates.add((String) data.getValueAt(i, 5));
            String check = dates.get(i);
            String year = check.substring(0,4);
            String month = check.substring(5,7);
            String day = check.substring(check.lastIndexOf("-")+1);
            if(Integer.parseInt(year)>=Integer.parseInt(firstDate[0]) &&
                    Integer.parseInt(year) <= Integer.parseInt(secondDate[0])){
                if(Integer.parseInt(month)>=Integer.parseInt(firstDate[1]) &&
                        Integer.parseInt(month) <= Integer.parseInt(secondDate[1])){
                    if(Integer.parseInt(day)>=Integer.parseInt(firstDate[2]) &&
                            Integer.parseInt(day) <= Integer.parseInt(secondDate[2])){
                        answers[i] = true;
                    }
                }
            }
        }

        boolean finalAnswer = true;
        for(boolean i:answers){
            if(i == false){
                finalAnswer = false;
                break;
            }
        }
        assertEquals(finalAnswer,true);

    }

    @Test
    void getCatItems2() throws eCommerceException{
        Controller vm = new Controller();
        ProgramView programView = new ProgramView();
        DBModel model = new DBModel();
        vm.setModel(model);
        vm.setView(programView);
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(true); // Customer Page
        DefaultTableModel data = model.getALLItems(); // check amount of itemID's in stock
        List<String> data2=model.getCatItems2(); // check itemID Combo box list amount
        assertEquals(data.getRowCount(),data2.size()); // check if the combo box list matches the items in stock

    }
    @Test
    void addItem2() throws  eCommerceException{
        Controller vm = new Controller();
        ProgramView programView = new ProgramView();
        DBModel model = new DBModel();
        vm.setModel(model);
        vm.setView(programView);
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(true); // Customer Page
        int value=model.getCatItems3(); // amount of items in cart before adding
        model.addItem2(new Item("28"));
        assertEquals(value+1,model.getCatItems3()); // true if the itemID is not in the cart

    }

    @Test
    void checkUserPass() throws eCommerceException{
        Controller vm = new Controller();
        ProgramView programView = new ProgramView();
        DBModel model = new DBModel();
        vm.setModel(model);
        vm.setView(programView);
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(true); // Customer Page
        Item item = new Item("eli,123 ");
        assertEquals(true,model.checkUserPass(item));
    }

}
