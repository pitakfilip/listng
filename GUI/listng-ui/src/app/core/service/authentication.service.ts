import {Injectable} from '@angular/core';
import {TokenStorageService} from './token-storage.service';
import {JwtToken} from '../model/security/jwt-token';
import {Utils} from '../util/utils';
import {JwtUtilsService} from './jwt-utils.service';
import {Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private jwt: JwtToken;

    constructor(private tokenStorage: TokenStorageService,
                private jwtUtils: JwtUtilsService,
                private router: Router) {
        this.refreshJwt();
    }

    private refreshJwt(){
        if (!Utils.exists(this.jwt)) {
            this.jwt = this.tokenStorage.retrieve();
        }
    }

    login(tokenRaw: string) {
        const token = this.jwtUtils.parseJwt(tokenRaw);
        if (this.jwtUtils.isValid(token)) {
            this.tokenStorage.store(token);
        } else {
            this.tokenStorage.clear();
        }
        this.refreshJwt();
    }

    update(tokenRaw: string) {
        const token = this.jwtUtils.parseJwt(tokenRaw);
        if (this.jwtUtils.isValid(token)) {
            this.tokenStorage.store(token);
        } else {
            this.tokenStorage.clear();
            this.router.navigate(['/login']);
        }
        this.refreshJwt();
    }

    logout() {
        this.tokenStorage.clear();
        this.jwt = null;
        return this.router.navigate(['/login']);
    }

    canAccess() {

        return Utils.exists(this.jwt) && this.jwtUtils.isValid(this.jwt);
    }

    redirectHome() {
        return this.router.navigate(['']);
    }

    error(status: number, requestUrl: string) {

    }
}
