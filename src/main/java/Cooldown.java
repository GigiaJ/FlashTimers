public enum Cooldown {
    BASE(300), LUCIDITY(268), INSIGHT(254), BOTH(231);

    int duration;

    Cooldown(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
