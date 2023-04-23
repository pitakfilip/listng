import {Injectable} from '@angular/core';
import {Utils} from '../util/utils';
import {JwtToken} from '../model/security/jwt-token';

@Injectable({
    providedIn: 'root'
})
export class TokenStorageService {

    _afterChangeToken = (token) => {};

    private storageKey = 'auth-token';

    constructor() {}

    registerOnChangeToken(cb) {
        this._afterChangeToken = cb || function () {};
        const token = this.retrieve();
        if (Utils.exists(token)) {
            cb(token);
        }
    }

    store(token: JwtToken) {
        this._afterChangeToken(token);
        return sessionStorage.setItem(this.storageKey, JSON.stringify(token));
    }

    retrieve() {
        return JSON.parse(sessionStorage.getItem(this.storageKey));
    }

    clear() {
        this._afterChangeToken(undefined);
        return sessionStorage.removeItem(this.storageKey);
    }
}
