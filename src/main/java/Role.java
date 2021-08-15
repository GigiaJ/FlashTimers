public enum Role {
    TOP("Top"), JUN("Jungle"), MID("Mid"), BOT("Bot"), SUP("Support");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
