import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class TokenStorageService {

    _afterChangeToken = (token) => {};

    private storageKey = 'auth-token';

    constructor() {}


    store(token: string) {
        this._afterChangeToken(token);
        return sessionStorage.setItem(this.storageKey, token);
    }

    retrieve() : string {
        return sessionStorage.getItem(this.storageKey);
    }

    clear() {
        this._afterChangeToken(undefined);
        return sessionStorage.removeItem(this.storageKey);
    }
}
