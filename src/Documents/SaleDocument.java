package Documents;

import Partner.Partner;
import Product.Product;

public class SaleDocument implements Document {
    private static final int MAX_PRODUCTS = 10;

    private Product[] products;
    private Partner[] customers;
    private Track track;
    private Partner customer;
    private int currentEntry;
    int totalSum;
    private Product[] entry = new Product[MAX_PRODUCTS];

    public SaleDocument() {
    }

    public SaleDocument(Product[] products, Partner[] customers) {
        this.products = products;
        this.customers = customers;
        this.track = Track.FIRST;
    }

    @Override
    public void nextTrack() {

    }

    @Override
    public void showProducts() {

    }

    @Override
    public void showPartners() {

    }

    @Override
    public void choicePartners(int iPartner) {

    }

    @Override
    public void createEntry(int iProduct, int amount, int price) {

    }

    @Override
    public void showDocument() {

    }

    @Override
    public void currentPosition() {

    }

    @Override
    public void toTotalSum() {

    }

    @Override
    public Partner getPartner() {
        return null;
    }

    @Override
    public int getTotalSum() {
        return 0;
    }

    @Override
    public Track getTrack() {
        return null;
    }
}
