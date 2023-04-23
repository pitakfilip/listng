import {Injectable} from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { catchError } from 'rxjs/operators';
import {AuthenticationService} from '../service/authentication.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private authService: AuthenticationService) {
    }

    /**
     * Handle errors from requests
     * @param request
     * @param next
     */
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError((res) => this.errorHandler(res, request.url)));
    }

    private errorHandler(response: any, url: string): Observable<any> {
        const status = response?.status;
        if (status === 401 || status === 403) {
            this.authService.error(status, url);
        }
        const error = response.error;
        let message = response.message;
        if (typeof error === 'object') {
            const keys = Object.keys(error);
            if (keys.some(item => item === 'message')) {
                message = error.message;
            }
        } else if (typeof error === 'string') {
            message = error;
        }
        // TODO not gucci, call error service s modalnym oknom
        // 500
        return throwError(() => new Error(message));
    }
}
