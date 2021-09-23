package server.messages;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 20191101L;

    /**
     * The message of the error, it should include all details which can be communicated to the client
     */
    private String errorMessage;

    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
