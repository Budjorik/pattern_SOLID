package Partner;

import java.util.Objects;

public class Supplier implements Partner {
    private String inn;
    private String name;
    private String address;
    private int creditBalance;

    public Supplier(String inn, String name, String address) {
        this.inn = inn;
        this.name = name;
        this.address = address;
    }

    public void setCreditBalance(int creditBalance) {
        this.creditBalance = creditBalance;
    }

    public int getCreditBalance() {
        return this.creditBalance;
    }

    @Override
    public String toString() {
        return "Поставщик { " + "наименование: " + name + ", ИНН: " + inn + ", адрес: " + address + " }";
    }

    @Override
    public void setBalance(int sum) {
        this.creditBalance -= sum;
    }

    @Override
    public void showBalance() {
        System.out.println("Долг перед поставщиком " + name
                + " инн (" + inn + " ) составляет "
                + (- creditBalance) + "руб.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !obj.getClass().equals(Supplier.class)) return false;
        Supplier altSuplier = (Supplier) obj;
        return this.inn == altSuplier.inn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.inn);
    }
}
