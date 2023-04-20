package Engine.input;

/**
 * a generic listener for Keyboard events
 */
public interface KeyListener {

    int KEY_RELEASED = -2;

    /**
     * @param key the action key
     * @param action the action of the key
     * @param mod auxiliary keys that are pressed as well (e.g. ctrl, alt, etc.)
     */
    void onKeyEvent(int key, int action, int mod);

}
