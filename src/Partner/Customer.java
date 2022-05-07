package Partner;

import Product.Product;

import java.util.Objects;

public class Customer implements Partner {
    private String inn;
    private String name;
    private String address;
    private int debitBalance;

    public Customer(String inn, String name, String address) {
        this.inn = inn;
        this.name = name;
        this.address = address;
    }

    public void setDebitBalance(int debitBalance) {
        this.debitBalance = debitBalance;
    }

    public int getDebitBalance() {
        return this.debitBalance;
    }

    @Override
    public String toString() {
        return "Покупатель { " + "наименование: " + name + ", ИНН: " + inn + ", адрес: " + address + " }";
    }

    @Override
    public void setBalance(int sum) {
        this.debitBalance += sum;
    }

    @Override
    public void showBalance() {
        System.out.println("Долг покупателя " + name
                + " инн (" + inn + " ) составляет "
                + debitBalance + "руб.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !obj.getClass().equals(Customer.class)) return false;
        Customer altCustomer = (Customer) obj;
        return this.inn == altCustomer.inn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.inn);
    }
}
