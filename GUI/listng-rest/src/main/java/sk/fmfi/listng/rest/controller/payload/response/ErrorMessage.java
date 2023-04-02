package sk.fmfi.listng.rest.controller.payload.response;

import sk.fmfi.listng.rest.dto.BaseDto;

import java.util.List;

public class ErrorMessage implements BaseDto {
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
}
