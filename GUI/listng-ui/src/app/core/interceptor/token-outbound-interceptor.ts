import {Injectable} from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from '../service/token-storage.service';
import {TOKEN_HEADER_NAME, WHITELISTED_PATHS} from '../consts/security.consts';

@Injectable()
export class TokenOutboundInterceptor implements HttpInterceptor {

    constructor(private tokenStorage: TokenStorageService) {}

    /**
     * Intercept outbound requests to append authentification token
     * @param request
     * @param next
     */
    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

        if (this.isWhitelisted(request.url)){
            return next.handle(request);
        }

        const authToken = this.tokenStorage.retrieve();

        const authReq = request.clone({
            headers: request.headers.set(TOKEN_HEADER_NAME, authToken),
            withCredentials: true
        });

        return next.handle(authReq);
    }

    private isWhitelisted(url: string) {
        return WHITELISTED_PATHS.includes(url);
    }
}
