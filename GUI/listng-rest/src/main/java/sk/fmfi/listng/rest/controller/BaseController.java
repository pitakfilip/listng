package sk.fmfi.listng.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.fmfi.listng.rest.controller.payload.response.ErrorResponse;
import sk.fmfi.listng.rest.controller.payload.response.Response;

public class BaseController {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<Response> handleException(AccessDeniedException ex) {
        System.out.println("AccessDeniedException error " + ex);
        return error(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler(NotFoundException.class)
//    @ResponseBody
//    public ResponseEntity<Responsee> handleException(NotFoundException ex) {
//        System.out.println("NotFoundException error " + ex);
//        return error(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }

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
     * Success response s payloadom lubovolneho typu. S manualne definovanym atributom legth.
     *
     * @param payload payload, ktory sa bude transformovat do JSONu.
     * @param totalLength pocet zaznamov
     * @return response s JSON
     */
    protected final <T> Response<T> success(T payload, Integer totalLength) {
        return new Response<>(payload, totalLength);
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
