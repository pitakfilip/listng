package sk.fmfi.listng.infrastructure.common.error;


import java.text.MessageFormat;
import java.util.List;

public class ErrorMessage implements Error {
    private List<String> fields;

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorMessage() {
        return MessageFormat.format("[ERROR] {1} caused by: {2}", message, String.join("\n\t,", fields));
    }
}
