package sk.fmfi.listng.rest.controller.payload.response;

import sk.fmfi.listng.rest.dto.BaseDto;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse implements BaseDto {

    private List<ErrorMessage> errors = new ArrayList<>();

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public ErrorResponse addMessage(ErrorMessage errorMessage) {
        errors.add(errorMessage);
        return this;
    }
}
