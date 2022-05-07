package Product;

import java.util.Objects;

public class Product {
    static final int ROUNDING = 2;
    static final double SCALE = Math.pow(10, ROUNDING);
    static final int REDUCTION_OF_PERCENT = 100;
    static final double FRACTION = 0.5;

    private String name;
    private Packing packing;
    private int percentMargin;

    private int amountRang;
    private int sumRang;
    private Rating rating;

    private int comingAmount;
    private double comingCost;

    private int saleAmount;
    private double revenue;
    private double saleCost;

    private int stockAmount;
    private double stockCost;
    private double stockComingPrice;
    private double stockSalePrice;

    public Product(String name, Packing packing, int percentMargin) {
        this.name = name;
        this.packing = packing;
        this.percentMargin = percentMargin;
    }

    public void setPercentMargin(int percentMargin) {
        this.percentMargin = percentMargin;
    }

    public void setRating(Rating rating) {
        this.sumRang += rating.getRang();
        this.amountRang += 1;
        double preRang = (double) (this.sumRang / this.amountRang);
        int corrRang = (preRang % 1) >= FRACTION ? 1 : 0;
        int rang = (int) (Math.floor(preRang) + corrRang);
        this.rating = Rating.getRating(rang);
    }

    public void comingProduct(int comingAmount, double comingPrice) {
        this.comingAmount += comingAmount;
        double addComingCost = Math.ceil(comingAmount * comingPrice * SCALE) / SCALE;
        this.comingCost += addComingCost;
        this.stockAmount += comingAmount;
        this.stockCost += addComingCost;
        this.stockComingPrice = Math.ceil(this.stockCost / this.stockAmount * SCALE) / SCALE;
        this.stockSalePrice = Math.ceil(this.stockComingPrice * (1
                + (this.percentMargin / REDUCTION_OF_PERCENT)) * SCALE) / SCALE;
    }

    public void saleProduct(int saleAmount) {
        this.saleAmount += saleAmount;
        double addSaleCost = Math.ceil(saleAmount * this.stockComingPrice * SCALE) / SCALE;
        this.saleCost += addSaleCost;
        double addRevenue = Math.ceil(saleAmount * this.stockSalePrice * SCALE) / SCALE;
        this.revenue += addRevenue;
        this.stockAmount -= saleAmount;
        this.stockCost -= addSaleCost;
    }

    public void returnProduct(int returnAmount, double comingPrice, double salePrice) {
        this.saleAmount -= returnAmount;
        double addSaleCost = Math.ceil(returnAmount * comingPrice * SCALE) / SCALE;
        this.saleCost -= addSaleCost;
        double addRevenue = Math.ceil(returnAmount * salePrice * SCALE) / SCALE;
        this.revenue -= addRevenue;
        this.stockAmount += returnAmount;
        this.stockCost += addSaleCost;
    }

    public String getName() {
        return this.name;
    }

    public Packing getPacking() {
        return this.packing;
    }

    public int getPercentMargin() {
        return this.percentMargin;
    }

    public int getAmountRang() {
        return this.amountRang;
    }

    public int getSumRang() {
        return this.sumRang;
    }

    public Rating getRating() {
        return this.rating;
    }

    public int getComingAmount() {
        return this.comingAmount;
    }

    public double getComingCost() {
        return this.comingCost;
    }

    public int getSaleAmount() {
        return this.saleAmount;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public double getSaleCost() {
        return this.saleCost;
    }

    public int getStockAmount() {
        return this.stockAmount;
    }

    public double getStockCost() {
        return this.stockCost;
    }

    public double getStockComingPrice() {
        return this.stockComingPrice;
    }

    public double getStockSalePrice() {
        return this.stockSalePrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !obj.getClass().equals(Product.class)) return false;
        Product altProduct = (Product) obj;
        return this.name.equals(altProduct.name)
                &&this.packing.equals(altProduct.packing)
                &&this.percentMargin == altProduct.percentMargin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

}
