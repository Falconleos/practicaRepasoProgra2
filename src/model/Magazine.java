package model;

import enums.MaterialStatus;

public class Magazine extends Material{

    private Integer editionNumber;

    public Magazine(String title, Integer publishedYear, MaterialStatus materialStatus, Integer editionNumber) {
        super(title, publishedYear, materialStatus);
        this.editionNumber = editionNumber;
    }

    public Magazine() {
    }

    public Integer getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
    }

    @Override
    public String toString() {
        return "Magazine:" +
                "\neditionNumber=" + editionNumber +
                "\n";
    }
}
