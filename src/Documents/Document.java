package Documents;

import Partner.Partner;
import Product.Product;

public interface Document {
    public void nextTrack();
    public void showProducts();
    public void showPartners();
    public void choicePartners(int iPartner);
    public void createEntry(int iProduct, int amount, int price);
    public void showDocument();
    public void currentPosition();
    public void toTotalSum();
    public Partner getPartner();
    public int getTotalSum();
    public Track getTrack();
}
