package ui;

import enums.MaterialStatus;
import exceptions.EmptyListException;
import exceptions.InvalidIdException;
import exceptions.InvalidUserName;
import model.Book;
import model.Material;
import model.User;
import service.LoanServiceImpl;
import service.MaterialServiceImpl;
import service.UserServiceImpl;
import utils.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoanMenu {

    public static void loanMenu(Scanner scanner, UserServiceImpl userService, MaterialServiceImpl materialService,LoanServiceImpl loanService){

        while (true){

            System.out.println("Loan menu:");
            System.out.println("1.-New Loan");
            System.out.println("2.-Return Item");
            System.out.println("3.-Back");

            int option = Validation.option(scanner,"Select option");

            switch (option){



                case 3 ->{
                    return;
                }
                default -> System.out.println("Invalid option");

            }

        }
    }

    public static void newLoan(Scanner sc,LoanServiceImpl loanService, UserServiceImpl userService, MaterialServiceImpl materialService)throws EmptyListException, InvalidIdException, InvalidUserName {

        if(userService.getUserService().getList().isEmpty()){
            throw new EmptyListException("No users yet");
        }

        List<Book>availableBookList = new ArrayList<>();
        for(Material material : materialService.getMaterialService().getList()){
            if(material instanceof Book && material.getMaterialStatus().equals(MaterialStatus.AVAILABLE)){
                availableBookList.add(material);
            }
        }
        if(availableBookList.isEmpty()){
            throw new EmptyListException("No available material");
        }

        System.out.println("User name");
        String name = sc.nextLine();

        List<User>userMatches = new ArrayList<>();
        userMatches = userService.findByName(name);

        if(userMatches.isEmpty()){
            throw new InvalidUserName("No matches...");
        }

        for(User user : userMatches){
            System.out.println(user);
        }

        Integer userId = Validation.option(sc,"Select user id");

        User selectedUser = userService.findById(userMatches,userId);

        if(selectedUser == null){
            throw new InvalidIdException("Invalid id");
        }


        for(Book book : availableBookList){
            System.out.println(book);
        }

        System.out.println("select title name");
        String bookTitle = sc.nextLine();

        List<Material> filtered = new ArrayList<>();
        filtered =  materialService.findByTitle(bookTitle);

        if(!availableBookList.contains(filtered){
            throw new EmptyListException("That title is not available");
        }

    }

}
