package ui;

import exceptions.EmptyListException;
import exceptions.InvalidIdException;
import exceptions.InvalidUserName;
import model.Loan;
import model.User;
import service.UserServiceImpl;
import utils.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMenu {


    //-------------------user----------------------//

    public static void userMenu(Scanner sc, UserServiceImpl userService){

        int option = -1;
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1.-new user");
            System.out.println("2.-loans per user");
            System.out.println("3.-user max loans");
            System.out.println("4.-update user");
            System.out.println("5.-delete user");
            System.out.println("6.-Exit");

            option = Validation.option(sc, "Select the option:");

            switch (option) {

                case 1 -> newUser(sc,userService);

                case 2 -> {
                    try {
                        loanPerUser(sc,userService);
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }catch (InvalidUserName e){
                        System.out.println(e.getMessage());
                    }catch (InvalidIdException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 3 ->{
                    try {
                        userMaxLoans(sc,userService);
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 4 ->{
                    try{
                        updateUser(sc,userService);
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }catch (InvalidUserName e){
                        System.out.println(e.getMessage());
                    }catch (InvalidIdException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 5 ->{
                    try {
                        userDelete(sc,userService);
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }catch (InvalidUserName e){
                        e.getMessage();
                    }catch (InvalidIdException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 6-> {
                    return;
                }

                default -> {
                    System.out.println("Invalid option...");
                    return;
                }
            }
        }
    }

    public static void newUser(Scanner sc,UserServiceImpl userService){
        System.out.println("name:");
        String name = sc.nextLine();
        userService.save(new User(name,new ArrayList<>()));
        System.out.println("User " + name + " created succesfuly");
    }

    public static void loanPerUser(Scanner sc,UserServiceImpl userService) throws EmptyListException, InvalidIdException,InvalidUserName {
        System.out.println("user name");
        String name = sc.nextLine();

        List<User>matches = new ArrayList<>();
        matches = userService.findByName(name);

        if(matches.isEmpty()){
            System.out.println("No matches");
        }else{

            for(User user : matches){
                System.out.println(user);
            }

           int id = Validation.option(sc,"select user id");
           User user = userService.findById(matches,id);

            System.out.println("Loans list from " + user.getName());
            int count = 0;
            for(Loan loan : user.getLoanList()){
                System.out.println(loan);
                System.out.println("==========================");
                count++;
            }
            System.out.println("Result: " + count);
        }
    }

    public static void userMaxLoans(Scanner sc,UserServiceImpl userService)throws EmptyListException{
        User maxUser = userService.findAll().getFirst();
        for(User user : userService.findAll()){
            if(user.getLoanList().size()>maxUser.getLoanList().size()){
               maxUser = user;
            }
        }
        int loanCount = maxUser.getLoanList().size();
        System.out.println("Max loans from " + maxUser.getName() + " with " + loanCount+" loans");
    }

    public static void updateUser(Scanner sc,UserServiceImpl userService)throws EmptyListException,InvalidIdException,InvalidUserName{
        System.out.println("name");
        String name = sc.nextLine();

        List<User>matches = new ArrayList<>();
        matches = userService.findByName(name);
        if(matches.isEmpty()){
            throw new EmptyListException("No matches...");
        }else{
            for(User user : matches){
                System.out.println(user);
            }
          Integer id = Validation.option(sc,"select user id:");
          User user = userService.findById(matches,id);
            System.out.println(user);
            System.out.println("new name:");
            String newName = sc.nextLine();

            userService.update(user,newName);
            System.out.println(user);
            System.out.println("Update complete!");
        }
    }

    public static void userDelete(Scanner sc,UserServiceImpl userService)throws EmptyListException,InvalidUserName,InvalidIdException{
        System.out.println("name to delete:");
        String name = sc.nextLine();

        List<User>matches = new ArrayList<>();
        matches = userService.findByName(name);

        if(matches.isEmpty()){
            throw new EmptyListException("No matches");
        }else{
            for(User user : matches){
                System.out.println(user);
            }
            Integer id = Validation.option(sc,"Select user id:");
            User user = userService.findById(matches,id);

            userService.delete(user);
            System.out.println("User deleted...");

        }
    }



}
