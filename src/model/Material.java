package model;

import enums.MaterialStatus;

public abstract class Material implements Comparable<Material>{

    private static Integer counter = 1;
    private Integer id;
    private String title;
    private Integer publishedYear;
    private MaterialStatus materialStatus;

    public Material(String title, Integer publishedYear, MaterialStatus materialStatus) {
        this.id = counter++;
        this.title = title;
        this.publishedYear = publishedYear;
        this.materialStatus = materialStatus;
    }

    public Material() {
        this.id = counter++;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        Material.counter = counter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public MaterialStatus getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(MaterialStatus materialStatus) {
        this.materialStatus = materialStatus;
    }

    @Override
    public String toString() {
        return "Material:" +
                "\nid=" + id +
                "\ntitle='" + title +
                "\npublishedYear=" + publishedYear +
                "\nmaterialStatus=" + materialStatus +
                "\n";
    }

    @Override
    public int compareTo(Material o) {
        return Integer.compare(o.getId(),id);
    }
}
