package sk.fmfi.listng.rest.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.infrastructure.common.error.ErrorResponse;

public class BaseController {
    
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<Response> handleException(BindException ex) {
        System.out.println("BindException error " + ex);
        return error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Success response bez navratoveho payloadu.
     *
     * @return prazdny response
     */
    protected final <T> Response<T> success() {
        return new Response<>();
    }

    /**
     * Success response s payloadom lubovolneho typu.
     *
     * @param payload payload, ktory sa bude transformovat do JSONu.
     * @return response s JSON
     */
    protected final <T> Response<T> success(T payload) {
        return new Response<>(payload);
    }

    /**
     * Neuspesny response.
     *
     * @return Prazdny chybovy response.
     */
    protected final <T> Response<T> error() {
        return new Response<T>().error();
    }

    /**
     * Neuspesny response s message.
     *
     * @param message message, ktora sa pribali do response
     * @return Neuspesny response s message
     */
    protected final ResponseEntity<Response<String>> errorResponse(String message) {
        return new ResponseEntity<>(error(message), HttpStatus.OK);
    }

    protected Response<String> error(String message) {
        return new Response<String>().withErrorMessage(message);
    }

    /**
     * Neuspesny response s error objektom.
     *
     * @param errorResponse objekt, ktory obsahuje informacie o poliach
     * @return Neuspesny response s message
     */
    protected final ResponseEntity<ErrorResponse> error(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    protected final ResponseEntity<Response> error(String message, HttpStatus status) {
        return new ResponseEntity<>(error(message), status);
    }
}
