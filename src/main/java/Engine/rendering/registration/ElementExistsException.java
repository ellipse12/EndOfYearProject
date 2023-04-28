package Engine.rendering.registration;

import java.io.IOException;

public class ElementExistsException extends Exception {

    public ElementExistsException() {
    }

    public ElementExistsException(String message) {
        super(message);
    }

    public ElementExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementExistsException(Throwable cause) {
        super(cause);
    }


}
