package enums;

public enum MaterialStatus {

    AVAILABLE("Material available"),
    LOANED("Material loaned");

    private String label;

    MaterialStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
