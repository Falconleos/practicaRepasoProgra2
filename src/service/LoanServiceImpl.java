package service;

import exceptions.EmptyListException;
import exceptions.InvalidDaysException;
import exceptions.InvalidIdException;
import model.Loan;
import model.Material;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class LoanServiceImpl {

    private final Service<Loan> loanService;
    private final UserServiceImpl userService;
    private final MaterialServiceImpl materialService;

    public LoanServiceImpl(UserServiceImpl userService, MaterialServiceImpl materialService) {
        this.loanService = new Service<>();
        this.userService = userService;
        this.materialService = materialService;
    }

    public List<Loan> findAll()throws EmptyListException {
        return loanService.getList();
    }

    public Loan save(Loan loan){
        loanService.save(loan);
        return loan;
    }

    public Loan findById(List<Loan>loanList,Integer id)throws EmptyListException,InvalidIdException{
        if(loanList.isEmpty()){
            throw new EmptyListException("No match");
        }
        for(Loan loan : loanList){
            if (loan.getId().equals(id)){
                return loan;
            }
        }
        throw new InvalidIdException("Invalid id");
    }

    public List<Loan> findByUser(User user)throws EmptyListException{
        List<Loan>filtered = new ArrayList<>();
        for(Loan loan : loanService.getList()){
            if(loan.getUser().equals(user)){
                filtered.add(loan);
            }
        }
        return filtered;
    }

    public void delete(Loan loan){
        loanService.delete(loan);
    }

    public Loan addDays(Loan loan,Integer days)throws InvalidDaysException {
        if(days<=0){
            throw new InvalidDaysException("Days number must be bigger than 0");
        }
        loan.setDays(loan.getDays()+days);
        return loan;
    }




}
