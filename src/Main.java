import Documents.*;
import Partner.*;
import Product.*;

import java.beans.Customizer;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final int AMOUNT_DOCUMENTS = 10;
        Product[] products = createProducts();
        Partner[] suppliers = createSuppliers();
        Partner[] customers = createCustomers();
        Document[] comingDocuments = new ComingDocument[AMOUNT_DOCUMENTS];
        boolean isDoing = true;
        while (isDoing) {
            String message = "Главное меню (выберите нужный пункт):\n"
                    + "1. Работа с приходными ордерами поставщиков\n"
                    + "2. Работа с расходными ордерами клиентов\n"
                    + "3. Работа с возвратными ордерами клиентов\n"
                    + "4. Отчеты по товару (закупки, продажи, остатки)\n"
                    + "5. Отчеты по сальдо расчетов с контрагентами\n"
                    + "0. Выход";
            int input = Integer.parseInt(toScan(message));
            switch (input) {
                case 1:
                    mainDocumentsMenu(products, suppliers, comingDocuments);
                    break;
                case 2:
                    System.out.println("Функционал работы с расходными ордерами временно недоступен.");
                    break;
                case 3:
                    System.out.println("Функционал работы с возвратными ордерами временно недоступен.");
                    break;
                case 4:
                    showAllAboutProducts(products);
                    break;
                case 5:
                    showAllPartnersBalance(suppliers, customers);
                    break;
                case 0:
                    isDoing = false;
                    System.out.println("Работа программы завершена.");
                    break;
                default:
                    System.out.println("Ошибка выбора действия. Пожалуйста повторите ввод.");
            }
        }
    }

    public static Product[] createProducts() {
        Product one = new Product("Молоко", Packing.LITER, 20);
        Product two = new Product("Хлеб", Packing.THING, 10);
        Product three = new Product("Яйцо", Packing.DOZEN, 30);
        Product four = new Product("Гречка", Packing.PACK, 15);
        Product five = new Product("Колбаса", Packing.KILO, 50);
        Product[] newProducts = new Product[5];
        newProducts[0] = one;
        newProducts[1] = two;
        newProducts[2] = three;
        newProducts[3] = four;
        newProducts[4] = five;
        return newProducts;
    }

    public static Partner[] createSuppliers() {
        Partner one = new Supplier("1111111111", "ПАО 'Газпром'", "г.Москва");
        Partner two = new Supplier("2222222222", "ООО 'Майкрософт'", "г.Нью-Йорк");
        Partner three = new Supplier("3333333333", "АО 'Магнит'", "г.Краснодар");
        Partner[] newSuppliers = new Supplier[3];
        newSuppliers[0] = one;
        newSuppliers[1] = two;
        newSuppliers[2] = three;
        return newSuppliers;
    }

    public static Partner[] createCustomers() {
        Partner one = new Customer("4444444444", "ООО 'Максим'", "г.Тюмень");
        Partner two = new Customer("5555555555", "ООО 'Рога и копыта'", "г.Владивосток");
        Partner three = new Customer("6666666666", "ИП Пупкин И.И.", "г.Иваново");
        Partner[] newCustomers = new Customer[3];
        newCustomers[0] = one;
        newCustomers[1] = two;
        newCustomers[2] = three;
        return newCustomers;
    }

    public static Document createComingDocument(Product[] products, Partner[] suppliers) {
        Document newComingDocument = new ComingDocument(products, suppliers);
        return newComingDocument;
    }

    public static void saveDocument(Document[] documents, Document document) {
        boolean isAdd = false;
        for (int i = 0; i < documents.length; i++) {
            if (documents[i] == null && !isAdd) {
                documents[i] = document;
                isAdd = true;
                System.out.println("Ордер по контрагенту " + document.getPartner().getName()
                        + " на сумму " + document.getTotalSum()
                        + " руб. успешно сохранен в базу данных!");
                break;
            }
        }
        if (!isAdd) {
            System.out.println("В базе данных нет места, ордер не сохранен!");
        }
    }

    public static String toScan(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static void mainDocumentsMenu(Product[] products, Partner[] partners, Document[] documents) {
        boolean isDoing = true;
        while (isDoing) {
            String message = "Меню работы с ордерами (приходными/расходными/возвратными) :\n"
                    + "1. Создать ордер\n"
                    + "2. Показать все сохраненные ордера\n"
                    + "3. Показать детали ордера\n"
                    + "4. Изменить текущий статус сохраненного ордера\n"
                    + "0. Возврат в главное меню";
            int input = Integer.parseInt(toScan(message));
            switch (input) {
                case 1:
                    routerDocuments(products, partners, documents);
                    break;
                case 2:
                    showAllDocuments(documents);
                    break;
                case 3:
                    showDocument(documents);
                    break;
                case 4:
                    changeTrackDocument(documents);
                    break;
                case 0:
                    isDoing = false;
                    System.out.println("Работа с ордерами завершена.");
                    break;
                default:
                    System.out.println("Ошибка выбора действия. Пожалуйста повторите ввод.");
            }
        }
    }

    public static void routerDocuments(Product[] products, Partner[] partners, Document[] documents) {
        String rightClassArray = returnClassArrayDocuments(documents);
        String comingDocumentClass = "ComingDocument";
        if (rightClassArray.equals(comingDocumentClass)) {
            comingDocumentsMenuOne(products, partners, documents);
        } else {
            System.out.println("Ошибка выбора типа ордера!");
        }
    }

    public static String returnClassArrayDocuments(Document[] documents) {
        String rightClass = documents.getClass().getName();
        rightClass = rightClass.substring(rightClass.indexOf(".") + 1);
        rightClass = rightClass.substring(0, rightClass.indexOf(";"));
        return rightClass;
    }

    public static void showAllDocuments(Document[] documents) {
        System.out.println("База данных ордеров:");
        for (int i = 0; i < documents.length; i++) {
            if (documents[i] != null) {
                System.out.println((i + 1) + ". контрагент = " + documents[i].getPartner().getName()
                        + " ; сумма = " + documents[i].getTotalSum() + " руб. ; статус = "
                        + documents[i].getTrack().getTitle());
            } else {
                System.out.println("Показаны все ордера из базы данных!");
                break;
            }
        }
    }

    public static void showDocument(Document[] documents) {
        int input = Integer.parseInt(toScan("Введите номер ордера:"));
        documents[input - 1].showDocument();
    }

    public static void comingDocumentsMenuOne(Product[] products, Partner[] suppliers, Document[] comingDocuments) {
        Document newComingDocument = new ComingDocument(products, suppliers);
        System.out.println("Шаг 1: Выбор поставщика.");
        newComingDocument.showPartners();
        int inputOne = Integer.parseInt(toScan("Введите номер поставщика из списка:"));
        newComingDocument.choicePartners(inputOne);
        System.out.println("Шаг 2: Выбор товара, ввод его количества и цены.");
        comingDocumentsMenuOneOne(products, suppliers, newComingDocument);
        boolean isDoing = true;
        while (isDoing) {
            String message = "Шаг 3: Сохранение приходного ордера (выберите нужный пункт):\n"
                    + "1. Сохранить\n"
                    + "2. Не сохранять";
            int inputTwo = Integer.parseInt(toScan(message));
            switch (inputTwo) {
                case 1:
                    newComingDocument.toTotalSum();
                    saveDocument(comingDocuments, newComingDocument);
                    suppliers[inputOne - 1].setBalance(newComingDocument.getTotalSum());
                    isDoing = false;
                    break;
                case 2:
                    System.out.println("Приходный ордер не сохранен.");
                    isDoing = false;
                    break;
                default:
                    System.out.println("Ошибка выбора действия. Пожалуйста повторите ввод.");
            }
        }
    }

    public static void comingDocumentsMenuOneOne(Product[] products, Partner[] suppliers, Document comingDocument) {
        boolean isDoing = true;
        while (isDoing) {
            String message = "Меню выбора товара (выберите нужный пункт):\n"
                    + "1. Показать доступный товар\n"
                    + "2. Выбрать товар, ввести количество и цену\n"
                    + "0. Перейти к сохранению приходного ордера";
            int input = Integer.parseInt(toScan(message));
            switch (input) {
                case 1:
                    comingDocument.showProducts();
                    break;
                case 2:
                    inputProduct(products, comingDocument);
                    break;
                case 0:
                    isDoing = false;
                    System.out.println("Работа по выбору товара завершена.");
                    break;
                default:
                    System.out.println("Ошибка выбора действия. Пожалуйста повторите ввод.");
            }
        }
    }

    public static void inputProduct(Product[] products, Document comingDocument) {
        int inputOne = Integer.parseInt(toScan("Введите номер товара из списка:")) - 1;
        int inputTwo = Integer.parseInt(toScan("Введите количество товара:"));
        int inputThree = Integer.parseInt(toScan("Введите цену товара:"));
        comingDocument.currentPosition();
        comingDocument.createEntry(inputOne, inputTwo, inputThree);
    }

    public static void changeTrackDocument(Document[] documents) {
        int inputOne = Integer.parseInt(toScan("Введите номер ордера из списка:"));
        if (inputOne <= documents.length) {
            if (documents[inputOne - 1] != null) {
                documents[inputOne - 1].nextTrack();
            } else {
                System.out.println("Ордера с таким номером не существует!");
            }
        } else {
            System.out.println("Ордера с таким номером не существует!");
        }
    }

    public static void showAllPartnersBalance(Partner[] suppliers, Partner[] partners) {
        boolean isDoing = true;
        while (isDoing) {
            String message = "Выберите тип контрагентов для вывода сальдо :\n"
                    + "1. Поставщики\n"
                    + "2. Покупатели\n"
                    + "0. Возврат в главное меню";
            int input = Integer.parseInt(toScan(message));
            switch (input) {
                case 1:
                    showPartnerBalance(suppliers);
                    break;
                case 2:
                    showPartnerBalance(partners);
                    break;
                case 0:
                    isDoing = false;
                    System.out.println("Работа с отчетами по поставщикам завершена.");
                    break;
                default:
                    System.out.println("Ошибка выбора действия. Пожалуйста повторите ввод.");
            }
        }
    }

    public static void showPartnerBalance(Partner[] partners) {
        for (int i = 0 ; i < partners.length ; i++) {
            if (partners[i] != null) {
                System.out.print((i + 1) + ". ");
                partners[i].showBalance();
            } else {
                System.out.println("Вывод информации из базы данных завершен!");
                break;
            }
        }
    }

    public static void showAllAboutProducts(Product[] products) {
        boolean isDoing = true;
        while (isDoing) {
            String message = "Выберите вариант отчета по товарам :\n"
                    + "1. Отчет по закупкам\n"
                    + "2. Отчет по продажам\n"
                    + "3. Отчет по остаткам\n"
                    + "0. Возврат в главное меню";
            int input = Integer.parseInt(toScan(message));
            switch (input) {
                case 1:
                    System.out.println("Отчет по закупкам товара временно недоступен.");
                    break;
                case 2:
                    System.out.println("Отчет по продажам товара временно недоступен.");
                    break;
                case 3:
                    showStockProducts(products);
                    break;
                case 0:
                    isDoing = false;
                    System.out.println("Работа с отчетами по товарам завершена.");
                    break;
                default:
                    System.out.println("Ошибка выбора действия. Пожалуйста повторите ввод.");
            }
        }
    }

    public static void showStockProducts(Product[] products) {
        System.out.println("№ | Товар (наменование) | Ед.изм. | Кол-во | Цена | Сумма |");
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.print(i + 1 + " | ");
                System.out.print(products[i].getName() + " | ");
                System.out.print(products[i].getPacking() + " | ");
                System.out.print(products[i].getStockAmount() + " | ");
                System.out.print(products[i].getStockComingPrice() + " | ");
                System.out.print(products[i].getStockCost() + " |");
                System.out.println();
            } else {
                System.out.println("Вывод информации из базы данных завершен!");
                break;
            }
        }
    }

}
