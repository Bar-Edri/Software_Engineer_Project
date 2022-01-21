package tests;

import MVC.Message;
import MVC.ProgramView;
import MVC.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramViewTest {

    @Test
    void showMessage() {
        ProgramView programView = new ProgramView();
        Controller vm = new Controller();
        programView.setIViewModel(vm);
        programView.init("username");
        programView.start(false);
        programView.showMessage(new Message("message test"));
        assertEquals("message test", programView.getLatestMessage());
    }

}
