package sk.fmfi.listng.infrastructure.common.error;

import sk.fmfi.listng.infrastructure.common.BaseDto;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse implements BaseDto {

    private List<Error> errors = new ArrayList<>();

    public List<Error> getErrors() {
        return errors;
    }

    public ErrorResponse addMessage(Error error) {
        errors.add(error);
        return this;
    }
}
