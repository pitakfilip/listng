import {Injectable} from '@angular/core';
import {LocalstorageService} from './localstorage.service';

@Injectable({
    providedIn: 'root'
})
export class OauthService {

    localStorageService : LocalstorageService

    constructor(private storaageService : LocalstorageService) {
        this.localStorageService = storaageService;
    }

    verifyLogin(email: String, pass: String) {
        //TODO call rest api to verify user login
        //TODO save token into localStorage
        return true;
    }

    // TODO verify if user is logged in + token is valid
    verifySession() {
        return true;
    }

    logout() {
        //TODO delete token from localstorage
    }
}
