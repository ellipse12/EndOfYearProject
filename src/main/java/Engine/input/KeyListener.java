package Engine.input;

public interface KeyListener {

    int KEY_RELEASED = -2;

    void onKeyEvent(int key, int action, int mod);

}
