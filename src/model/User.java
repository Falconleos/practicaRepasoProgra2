package model;

import exceptions.LoanLimitExceededException;

import java.util.List;

public class User {

    private static Integer counter = 1;
    private Integer id;
    private String name;
    private List<Loan>loanList;

    public User(String name, List<Loan> loanList) {
        this.id = counter++;
        this.name = name;
        this.loanList = loanList;
    }

    public User() {
        this.id = counter++;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        User.counter = counter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    @Override
    public String toString() {
        return "User:" +
                "\nid=" + id +
                "\nname='" + name +
                "\nloanList=" + loanList +
                "\n";
    }

    public void addLoan(Loan loan){
        if(loanList.size()>=3){
            throw new LoanLimitExceededException("Loan list over limit");
        }
        loanList.add(loan);
    }

    public void removeLoan(Loan loan){
        loanList.remove(loan);
    }

}
