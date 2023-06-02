package sk.fmfi.listng.infrastructure.common.error;

import java.text.MessageFormat;

public enum OperationError implements Error {
    NOT_FOUND,
    INVALID_INPUT,
    SYSTEM_FAILURE;
    
    @Override
    public String getErrorMessage() {
        String msg;
        switch(this) {
            case NOT_FOUND -> msg = "Value not found";
            case INVALID_INPUT -> msg = "Unable to process input";
            case SYSTEM_FAILURE -> msg = "System failure";
        }
        return MessageFormat.format("Error occured while performing operations [{1}]", "msg");
    }
}
