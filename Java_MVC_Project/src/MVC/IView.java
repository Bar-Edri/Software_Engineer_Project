package MVC;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * this interface represents the View in the MVC architecture.
 * this will be the class that will show data on the screen/window
 */
public interface IView {
    void showItems(DefaultTableModel items);
    void showMessage(MVC.Message message);
    void setIViewModel(IController vm);
    void init(String userName);
    void initCustomer(String userName);
    void start(boolean X);
    void showCategories(List<String> finalCategoryData);
    void showCategories2(List<String> finalCategoryData);

}
