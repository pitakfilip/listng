import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {LOGIN_API, TOKEN_HEADER_NAME} from '../consts/security.consts';
import {Utils} from '../util/utils';
import {AuthenticationService} from '../service/authentication.service';

@Injectable()
export class TokenInboundInterceptor implements HttpInterceptor {

    constructor(private authService: AuthenticationService) {}


    /**
     * Intercept all inbound responses from backend server and send
     * token from header to AuthenticationService to handle.
     * @param request
     * @param next
     */
    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        return next.handle(request)
            .pipe(tap(event => {
                if (event instanceof HttpResponse) {
                    const tokenRaw = event.headers.get(TOKEN_HEADER_NAME);

                    if (Utils.exists(tokenRaw) && request.url === LOGIN_API){
                        if (request.url === LOGIN_API) {
                            this.authService.login(tokenRaw);
                            this.authService.redirectHome();
                        } else {
                            this.authService.update(tokenRaw);
                        }
                    } else {
                        this.authService.logout();
                    }
                }
            }));
    }
}
