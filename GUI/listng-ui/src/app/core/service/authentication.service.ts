import {Injectable} from '@angular/core';
import {TokenStorageService} from './token-storage.service';
import {Utils} from '../util/utils';
import {JwtUtilsService} from './jwt-utils.service';
import {Router} from '@angular/router';
import {AuthApiService} from '../api/auth-api.service';
import {User} from '../model/user.model';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private jwt: string;
    private loggedUser: User;

    constructor(private tokenStorage: TokenStorageService,
                private jwtUtils: JwtUtilsService,
                private router: Router,
                private authApi: AuthApiService) {
        this.refreshJwt();
    }

    private refreshJwt(){
        const fetch = this.tokenStorage.retrieve();
        if (Utils.exists(fetch)) {
            this.jwt = fetch;
            this.loggedUser = this.jwtUtils.getUser(this.jwt);
        }
    }

    handleLogin(tokenRaw: string) {
        const token = this.jwtUtils.parseJwtBody(tokenRaw);
        if (this.jwtUtils.isValid(token)) {
            this.tokenStorage.store(token);
        } else {
            this.tokenStorage.clear();
        }
        this.refreshJwt();
        this.redirectHome();
    }

    handleLogout() {
        this.tokenStorage.clear();
        this.jwt = null;
        this.authApi.logout().subscribe();
        return this.router.navigate(['/login']);
    }

    canAccess() {
        return Utils.exists(this.jwt) && this.jwtUtils.isValid(this.jwt);
    }

    redirectHome() {
        return this.router.navigate(['']);
    }

    error(status: number, requestUrl: string) {
        if (status === 403) {
            this.handleLogout();
        }
    }
}
