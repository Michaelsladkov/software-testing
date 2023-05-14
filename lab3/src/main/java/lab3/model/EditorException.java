package lab3.model;

import lombok.Getter;

public class EditorException extends Exception{
    public enum Cause {
        TOO_LOW_IMAGE_RESOLUTION
    }

    protected String message;

    @Getter
    private final Cause editorCause;

    public EditorException(Cause cause, String message) {
        this.editorCause = cause;
        this.message = message;
    }
}
