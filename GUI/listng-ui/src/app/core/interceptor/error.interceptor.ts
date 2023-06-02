import {Injectable} from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {Observable, tap, throwError} from 'rxjs';
import { catchError } from 'rxjs/operators';
import {AuthenticationService} from '../service/authentication.service';
import {ErrorService} from '../service/error.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private authService: AuthenticationService,
                private errorService: ErrorService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError((error: HttpErrorResponse) => this.errorHandler(error, request.url)));
    }

    private errorHandler(response: HttpErrorResponse, url: string): Observable<any> {
        const status = response?.status;
        if (status === 401) {
            this.errorService.noLogin()
        } else if (status === 403) {
            this.errorService.insufficientPermissions(false);
        } else if (status === 500) {
            this.errorService.failedRequest();
        } else {
            this.errorService.errorMessage(response.message)
        }

        const error = response.error;
        let message = error.message;
        if (typeof error === 'object') {
            const keys = Object.keys(error);
            if (keys.some(item => item === 'message')) {
                message = error.message;
            }
        } else if (typeof error === 'string') {
            message = error;
        }
        return throwError(() => new Error(message));
    }
}
