public class Main {

    public static final char START_KEY = '=';
    public static Counter counter = new Counter();

    public static void main(String[] args) {
        System.out.println("Welcome to the Flash Counter Tracker.");
        System.out.println("You will now be prompted for the bindings you wish to use for the game.");
        System.out.println("When the game starts press " + START_KEY);
        ButtonHandler.assignMessage();
        ButtonHandler.gameStart();
        ButtonHandler.updateButton();
    }
}
