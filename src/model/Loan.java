package model;

import exceptions.InvalidLoanException;

public class Loan {

    private static Integer counter = 1;
    private Integer id;
    private Material material;
    private User user;
    private Integer days;

    public Loan(Material material, User user, Integer days) {
        this.id = counter++;
        this.material = material;
        this.user = user;
        this.days = days;
    }

    public Loan() {
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        Loan.counter = counter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Loan:" +
                "\nid=" + id +
                "\nmaterial=" + material +
                "\nuser=" + user +
                "\ndays=" + days +
                "\n";
    }
}
