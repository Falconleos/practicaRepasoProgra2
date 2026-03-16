package service;

import enums.MaterialStatus;
import exceptions.EmptyListException;
import exceptions.InvalidIdException;
import model.Book;
import model.Magazine;
import model.Material;

import java.util.ArrayList;
import java.util.List;

public class MaterialServiceImpl {

    private final Service<Material>materialService;

    public MaterialServiceImpl() {
        this.materialService = new Service<>();
    }

    //getter//
    public Service<Material> getMaterialService() {
        return materialService;
    }

    public List<Material>finAll()throws EmptyListException {
        if(!materialService.getList().isEmpty()){
            return materialService.getList();
        }
        throw new EmptyListException("No materials yet");
    }

    public Material save(Material material){
        materialService.save(material);
        return material;
    }

    public Material findById(List<Material>list,Integer id)throws EmptyListException{
        if(list.isEmpty()){
            throw new EmptyListException("No match");
        }
        for(Material material : list){
            if(material.getId().equals(id)){
                return material;
            }
        }
        throw new InvalidIdException("Invalid id");
    }

    public List<Material> findByTitle(String title)throws EmptyListException{
        List<Material>filtered = new ArrayList<>();
        for(Material material : materialService.getList()){
            if(material.getTitle().toLowerCase().contains(title.toLowerCase())){
                filtered.add(material);
            }
        }
        return filtered;
    }

    public void delete(Material material){
        materialService.delete(material);
    }

    public Book updateBook(Book book,String title,String author,Integer publishedYear){
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishedYear(publishedYear);
        return book;
    }

    public Magazine updateMagazine(Magazine magazine,String title,Integer publiyedYear,Integer editionNumber){
        magazine.setTitle(title);
        magazine.setPublishedYear(publiyedYear);
        magazine.setEditionNumber(editionNumber);
        return magazine;
    }

    public List<Material> availableMaterial()throws EmptyListException{
        List<Material>filtered = new ArrayList<>();
        for(Material material : materialService.getList()){
            if(material.getMaterialStatus().equals(MaterialStatus.AVAILABLE)){
                filtered.add(material);
            }
        }
        if(filtered.isEmpty()){
            throw new EmptyListException("No materials available");
        }
        return filtered;
    }



}
