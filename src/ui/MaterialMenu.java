package ui;

import enums.MaterialStatus;
import exceptions.EmptyListException;
import exceptions.InvalidIdException;
import model.Book;
import model.Magazine;
import model.Material;
import service.MaterialServiceImpl;
import utils.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaterialMenu {

    public static void materialMenu(Scanner scanner, MaterialServiceImpl materialService){

        while (true){
            System.out.println("Material menu");
            System.out.println("1.-New Material");
            System.out.println("2.-List Materials");
            System.out.println("3.-List available Materials");
            System.out.println("4.-Update material");
            System.out.println("5.-Delete material");
            System.out.println("6.-Back");

            int option = Validation.option(scanner,"Select option");

            switch (option){
                case 1 -> newMaterial(scanner,materialService);

                case 2 -> {
                    try {
                        for(Material material : materialService.finAll()){
                            System.out.println(material);
                        }
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        for(Material material : materialService.availableMaterial()){
                            System.out.println(material);
                        }
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 4 ->{
                    try {
                        updateMaterial(scanner,materialService);
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }catch (InvalidIdException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 5 -> {
                    try {
                        deleteMaterial(scanner,materialService);
                    }catch (EmptyListException e){
                        System.out.println(e.getMessage());
                    }catch (InvalidIdException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 6 ->{
                    return;
                }

                default -> System.out.println("Invalid option");

            }

        }

    }

    public static void newMaterial(Scanner scanner,MaterialServiceImpl materialService){

        System.out.println("title:");
        String title = scanner.nextLine();

        Integer publisherYear = Validation.option(scanner,"Published Year");

        int option = -1;
        while (option == -1){
            System.out.println("Type:");
                System.out.println("1.-Book");
                System.out.println("2.-Magazine");

                option = Validation.option(scanner,"select option:");

                switch (option){

                    case 1 -> {
                        System.out.println("author: ");
                        String author = scanner.nextLine();

                        materialService.save(new Book(title,publisherYear, MaterialStatus.AVAILABLE,author));
                        System.out.println("Book added!");
                    }

                    case 2 ->{
                        Integer editionNumber = Validation.option(scanner,"Edition number:");
                        materialService.save(new Magazine(title,publisherYear,MaterialStatus.AVAILABLE,editionNumber));
                        System.out.println("Magazine added!");
                    }

                    default ->
                            System.out.println("Invalid option");
                }
        }
    }

    public static void updateMaterial(Scanner scanner,MaterialServiceImpl materialService)throws EmptyListException, InvalidIdException {

        System.out.println("title:");
        String title = scanner.nextLine();

        List<Material>matches = new ArrayList<>();
        matches = materialService.findByTitle(title);

        if(matches.isEmpty()){
            throw new EmptyListException("No matches");
        }else{

            for(Material material : matches){
                System.out.println(material);
            }

            Integer id = Validation.option(scanner,"Select material id");
            Material material = materialService.findById(matches,id);

            System.out.println(material);
            System.out.println("New title");
            String newTitle = scanner.nextLine();
            Integer newPublishedYear = Validation.option(scanner,"New published year");

            if(matches instanceof Book){
                System.out.println("new Author:");
                String newAuthor = scanner.nextLine();

                materialService.updateBook((Book) material,newTitle,newAuthor,newPublishedYear);
                System.out.println("book update succeed");
            }else if(matches instanceof Magazine){

                Integer newEditionNumber = Validation.option(scanner,"New edition number");
                materialService.updateMagazine((Magazine) material,newTitle,newPublishedYear,newEditionNumber);
                System.out.println("magazine update succeed");
            }
        }
    }

    public static void deleteMaterial(Scanner scanner,MaterialServiceImpl materialService)throws EmptyListException,InvalidIdException{
        System.out.println("title:");
        String title = scanner.nextLine();

        List<Material>matches = new ArrayList<>();
        matches = materialService.findByTitle(title);

        if(matches.isEmpty()){
            throw new EmptyListException("No matches");
        }

        for(Material material : matches){
            System.out.println(material);
        }

        Integer id = Validation.option(scanner,"Select id to delete");

        Material materialToDelete = materialService.findById(matches,id);

        materialService.delete(materialToDelete);
        System.out.println("Material deleted");
    }

}
