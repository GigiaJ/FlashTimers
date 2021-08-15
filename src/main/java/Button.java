import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Button {
    private Role role;
    private char button;
    private GlobalKeyAdapter adapter;


    Button(Role role, char button) {
        this.role = role;
        this.button = button;
        setAdapter(button, getPartialMessage(), Cooldown.BASE.getDuration());
        Listeners.setKeyboardListener(adapter);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public char getButton() {
        return button;
    }

    public void setButton(char button) {
        this.button = button;
    }

    private String getPartialMessage() {
        return role.getName() + " Flash + ";
    }

    public void updateAdapter(int val) {
        Listeners.removeKeyboardListener(adapter);
        switch (val) {
            case 1:
                setAdapter(button, getPartialMessage(), Cooldown.INSIGHT.getDuration());
                break;
            case 2:
                setAdapter(button, getPartialMessage(), Cooldown.LUCIDITY.getDuration());
                break;
            case 3:
                setAdapter(button, getPartialMessage(), Cooldown.BOTH.getDuration());
                break;
            default:
                setAdapter(button, getPartialMessage(), Cooldown.BASE.getDuration());
                break;
        }
        Listeners.setKeyboardListener(adapter);
    }

    private void setAdapter(char c, String message, long time) {
        adapter = new GlobalKeyAdapter() {
            @Override
            public void keyPressed(GlobalKeyEvent event) {
                String output = message + Counter.format(Main.counter.getElapsed() + (time * 1000));
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                    throw new RuntimeException(
                            "Key code not found for character '" + c + "'");
                }
                if (event.getVirtualKeyCode() == keyCode) {
                    //Places the message onto the system clipboard
                    StringSelection selection = new StringSelection(output);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                }
            }
        };
    }

}
