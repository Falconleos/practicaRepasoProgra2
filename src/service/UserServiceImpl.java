package service;

import exceptions.EmptyListException;
import exceptions.InvalidIdException;
import exceptions.InvalidUserName;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl {

    private final Service<User> userService;

    public UserServiceImpl() {
        this.userService = new Service<>();
    }

    //getter//
    public Service<User> getUserService() {
        return userService;
    }

    public List<User> findAll()throws EmptyListException{
        if(!userService.getList().isEmpty()){
            return userService.getList();
        }
        throw new EmptyListException("No users yet");
    }

    public User save(User user){
        userService.save(user);
        return user;
    }

    public User findById(List<User>list,Integer id)throws InvalidIdException{
        for(User user : list){
            if(user.getId().equals(id)){
                return user;
            }
        }
        throw new InvalidIdException("Invalid Id");
    }

    public List<User> findByName(String name)throws EmptyListException{
        List<User>filter = new ArrayList<>();
        for(User user : userService.getList()){
            if(user.getName().toLowerCase().contains(name.toLowerCase())){
                filter.add(user);
            }
        }
        return filter;
    }

    public void delete(User user){
        userService.delete(user);
    }

    public User update(User user,String newName){
        user.setName(newName);
        return user;
    }

    public int loansPerUser(User user){
        return user.getLoanList().size();
    }

    public User userMostLoans()throws EmptyListException {
        User user = userService.getList().getFirst();
        for(User u:userService.getList()){
            if(u.getLoanList().size() > user.getLoanList().size()){
                user = u;
            }
        }
        return user;
    }



}
