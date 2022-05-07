package Documents;

public enum Track {
    FIRST ("принят", 1),
    SECOND ("собирается", 2),
    THIRD ("доставляется", 3),
    FOURTH ("выполнен", 4);

    String title;
    int stage;

    Track(String title, int stage) {
        this.title = title;
        this.stage = stage;
    }

    public String getTitle() {
        return this.title;
    }

    public int getStage() {
        return this.stage;
    }

    public static Track getTrack(int stage) {
        switch (stage) {
            case 1:
                return FIRST;
            case 2:
                return SECOND;
            case 3:
                return THIRD;
            case 4:
                return FOURTH;
            default:
                return null;
        }
    }
}
