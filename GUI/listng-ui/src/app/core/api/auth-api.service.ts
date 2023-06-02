import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RestReposnse} from '../model/restReposnse';
import {LOGIN_API, LOGOUT_API} from '../consts/security.consts';

@Injectable({
    providedIn: 'root'
})
export class AuthApiService {

    constructor(private http: HttpClient) {
    }

    verifyLogin(username: string, password: string){
        return this.http.post<RestReposnse>(LOGIN_API,
            { username: username, password: password});
    }

    logout() {
        return this.http.post(LOGOUT_API, {});
    }
}
