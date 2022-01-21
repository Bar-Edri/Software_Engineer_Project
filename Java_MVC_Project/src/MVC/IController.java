package MVC;

/**
 * this interface represents the Controller in the MVC architecture.
 * this will be the connector between the view and the model.
 */
public interface IController {
    void setView(IView view);
    void setModel(IModel model);
    void addItem(Item item);
    void addItem2(Item item);
    void getItems(String userName);
    void getItems2(String userName);
    void getItemBetween(Item item, String userName);
    void getItemByName(Item item, String userName);

    void getItems3(String userName);

    boolean addUser(Item item) throws eCommerceException;
    boolean checkUserPass(Item item) throws eCommerceException;
    void addCategory(Item item);
    void addCategory2(Item item);

    void getALLItems();
}
