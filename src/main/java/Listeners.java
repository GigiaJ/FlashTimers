import com.sun.jna.platform.win32.WinReg;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Listeners {
    public static GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // use false here to switch to hook instead of raw input

    public static void setKeyboardListener(GlobalKeyAdapter adapter) {
        keyboardHook.addKeyListener(adapter);
    }

    public static void removeKeyboardListener(GlobalKeyAdapter adapter) {
        keyboardHook.removeKeyListener(adapter);
    }

}
