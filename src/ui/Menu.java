package ui;

import model.Material;
import model.User;
import service.LoanServiceImpl;
import service.MaterialServiceImpl;
import service.UserServiceImpl;
import utils.Validation;

import java.util.ArrayList;
import java.util.Scanner;

import static ui.UserMenu.userMenu;

public class Menu {

    public static void menuInicial(UserServiceImpl userService,MaterialServiceImpl materialService){

        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (true){
            System.out.println("MenuIncial:");
            System.out.println("1.-User menu");
            System.out.println("2.-Materials menu");
            System.out.println("3.-Loan menu");
            System.out.println("4.-Exit");

            option =  Validation.option(sc,"Select the option:");

            switch (option){

                case 1 -> UserMenu.userMenu(sc,userService);
                case 2 -> MaterialMenu.materialMenu(sc,materialService);
                default -> {
                    System.out.println("saliendo...");
                    return;
                }
            }


        }
    }



}
