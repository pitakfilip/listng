package sk.fmfi.listng.infrastructure.common;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.infrastructure.common.error.ErrorResponse;

import java.util.Collection;

public class Response<T>  implements BaseDto {

    private T payload;
    private boolean success;
    private String errorMessage;
    private ErrorResponse error;
    private Integer length;

    public Response() {
        this.success = true;
    }

    public Response(T payload) {
        this();
        this.payload = payload;
        if (payload instanceof Collection) {
            this.length = ((Collection) payload).size();
        }
    }

    public Response(T payload, Integer totalLength ) {
        this();
        this.payload = payload;
        this.length = totalLength;
    }

    public Response<T> withErrorMessage(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
        return this;
    }

    public Response<T> error() {
        this.success = false;
        return this;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public Response<T> withError(ErrorResponse error) {
        this.success = false;
        this.error = error;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getLength() {
        return length;
    }

    public T getPayload() {
        return payload;
    }
}
