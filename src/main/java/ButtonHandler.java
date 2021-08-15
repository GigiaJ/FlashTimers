import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class ButtonHandler {

    static Button[] buttons = new Button[5];

    public static void assignMessage() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < Role.values().length; i++) {
            System.out.println("Press a button to assign for " + Role.values()[i].getName());
            //Insert a wait here/assignment
            if (scanner.hasNext())
                buttons[i] = new Button(Role.values()[i], scanner.nextLine().charAt(0));
        }
    }

    public static void updateButton() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("To update a buttons value enter the button here and then select the modifier.");
            System.out.println("1. Top");
            System.out.println("2. Jungle");
            System.out.println("3. Middle");
            System.out.println("4. Bot");
            System.out.println("5. Support");
            if (scanner.hasNext()) {
                int position = scanner.nextInt() - 1; //Adjust for the array offset
                if (position <= buttons.length) {
                    System.out.println("1. Insight");
                    System.out.println("2. Lucidity");
                    System.out.println("3. Both");
                    if (scanner.hasNext()) {
                        buttons[position].updateAdapter(scanner.nextInt());
                        System.out.println(buttons[position].getRole() + "'s value has been updated.");
                    }
                }
            }
        }
    }

    public static void gameStart() {
        Listeners.setKeyboardListener(new GlobalKeyAdapter() {
            @Override
            public void keyPressed(GlobalKeyEvent event) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(Main.START_KEY);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                    throw new RuntimeException(
                            "Key code not found for character '" + Main.START_KEY + "'");
                }
                if (event.getVirtualKeyCode() == keyCode) {
                    Main.counter.reset();
                }
            }
        });
    }
}
