package Product;

public enum Rating {
    ONE ("ужасно", 1),
    TWO ("плохо", 2),
    THREE ("удовлетворительно", 3),
    FOUR ("хорошо", 4),
    FIVE ("отлично", 5);

    String title;
    int rang;

    Rating(String title, int rang) {
        this.title = title;
        this.rang = rang;
    }

    public String getTitle() {
        return this.title;
    }

    public int getRang() {
        return this.rang;
    }

    public static Rating getRating(int grade) {
        switch (grade) {
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            default:
                return null;
        }
    }
}
