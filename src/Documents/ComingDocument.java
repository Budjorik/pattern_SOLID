package Documents;

import Partner.Partner;
import Partner.Supplier;
import Product.Product;

public class ComingDocument implements Document {
    private static final int MAX_PRODUCTS = 10;

    private Product[] products;
    private Partner[] suppliers;
    private Track track;
    private Partner supplier;
    private int currentEntry;
    int totalSum;
    private Product[] entry = new Product[MAX_PRODUCTS];

    public ComingDocument() {
    }

    public ComingDocument(Product[] products, Partner[] suppliers) {
        this.products = products;
        this.suppliers = suppliers;
        this.track = Track.FIRST;
    }

    @Override
    public void nextTrack() {
        Track presentTrack = this.track;
        if (!presentTrack.equals(Track.FOURTH)) {
            int stage = this.track.getStage() + 1;
            this.track = Track.getTrack(stage);
            System.out.println("Статус приходного ордера изменен на '" + this.track.getTitle() + "'.");
        } else {
            System.out.println("Приходный ордер уже имеет статус '" + this.track.getTitle() + "', изменение невозможно!");
        }
    }

    @Override
    public void showProducts() {
        System.out.println("Список доступных товаров для оприходования:");
        for (int i = 0; i < this.products.length; i++) {
            int positions = i + 1;
            System.out.println(positions + ". " + this.products[i].getName());
        }
    }

    @Override
    public void showPartners() {
        System.out.println("Список доступных поставщиков:");
        for (int i = 0; i < this.suppliers.length; i++) {
            int positions = i + 1;
            System.out.println(positions + ". " + this.suppliers[i].toString());
        }
    }

    @Override
    public void choicePartners(int iPartner) {
        if (iPartner < this.suppliers.length) {
            if (this.supplier == null) {
                this.supplier = this.suppliers[iPartner - 1];
                System.out.println("Выбран поставщик: " + this.supplier.toString());
            } else {
                System.out.println("Поставщик уже выбран, изменение невозможно!");
            }
        } else {
            System.out.println("Поставщика с указанным номером нет в списке!");
        }
    }

    @Override
    public void createEntry(int iProduct, int amount, int price) {
        if (iProduct < this.products.length) {
            Product newProduct = new Product(this.products[iProduct].getName(),
                    this.products[iProduct].getPacking(),
                    this.products[iProduct].getPercentMargin());
            this.products[iProduct].comingProduct(amount, price);
            newProduct.comingProduct(amount, price);
            this.entry[this.currentEntry] = newProduct;
            System.out.println("Информация о товаре успешно введена!");
        } else {
            System.out.println("Товара с указанным номером нет в списке!");
        }
    }

    @Override
    public void showDocument() {
        System.out.println("Приходный ордер");
        System.out.println("Текущий статус: " + this.track.getTitle());
        System.out.println(this.supplier.toString());
        System.out.println("№ стр. | Товар (наменование) | Ед.изм. | Кол-во | Цена | Сумма |");
        for (int i = 0; i < this.entry.length; i++) {
            if (this.entry[i] != null) {
                System.out.print(i + 1 + " | ");
                System.out.print(this.entry[i].getName() + " | ");
                System.out.print(this.entry[i].getPacking() + " | ");
                System.out.print(this.entry[i].getComingAmount() + " | ");
                System.out.print(this.entry[i].getStockComingPrice() + " | ");
                System.out.print(this.entry[i].getComingCost() + " |");
                System.out.println();
            } else {
                break;
            }
        }
        System.out.println("Итого сумма: " + this.totalSum);
    }

    @Override
    public void currentPosition() {
        for (int i = this.currentEntry; i < this.entry.length; i++) {
            if (this.entry[i] == null) {
                this.currentEntry = i;
                break;
            }
        }
    }

    @Override
    public void toTotalSum() {
        int preTotalSum = 0;
        for (int i = 0; i < this.entry.length; i++) {
            if (this.entry[i] != null) {
                preTotalSum += this.entry[i].getComingCost();
            }
        }
        this.totalSum = preTotalSum;
    }

    @Override
    public Partner getPartner() {
        return this.supplier;
    }

    @Override
    public int getTotalSum() {
        return this.totalSum;
    }

    @Override
    public Track getTrack() {
        return this.track;
    }

}
