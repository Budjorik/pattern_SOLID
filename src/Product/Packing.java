package Product;

public enum Packing {
    LITER ("литр"),
    DOZEN ("десяток"),
    KILO ("килограмм"),
    PACK ("пачка"),
    THING ("штука");

    String title;

    Packing(String title) {
        this.title = title;
    }

    public String getPacking() {
        return title;
    }
}
