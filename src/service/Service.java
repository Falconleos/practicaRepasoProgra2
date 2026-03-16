package service;

import exceptions.EmptyListException;

import java.util.ArrayList;
import java.util.List;

public class Service<T> {

    private final List<T> list;

    public Service() {
        this.list = new ArrayList<>();
    }

    public List<T> getList() throws EmptyListException {
        if (list.isEmpty()){
            throw new EmptyListException("The list is empty");
        }
        return list;
    }

    public T save(T t){
        list.add(t);
        return t;
    }

    public void delete(T t){
        list.remove(t);
    }

}
