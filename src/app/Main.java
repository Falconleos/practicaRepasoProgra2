package app;

import model.Loan;
import model.Material;
import model.User;
import service.LoanServiceImpl;
import service.MaterialServiceImpl;
import service.Service;
import service.UserServiceImpl;
import ui.Menu;

public class Main {

    static void main() {

        UserServiceImpl userService = new UserServiceImpl();
        MaterialServiceImpl materialService = new MaterialServiceImpl();
        LoanServiceImpl loanService = new LoanServiceImpl(userService,materialService);

        Menu.menuInicial(userService,materialService);

    }

}
