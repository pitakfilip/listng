import {Injectable} from '@angular/core';
import * as CryptoJS from 'crypto-js';

@Injectable({
    providedIn: 'root'
})
export class CryptoService {

    private encryptionKey = 'EncryptionKey_626c0a490e30e34e0e07b9fb45ef7e59';

    constructor() {}

    // TODO pouzit angular btoa() atob() na encode/decode (porovnat a ci nebude lepsie/krajsie toto pouzit)

    encrypt(obj: string) {
        return encodeURIComponent(CryptoJS.AES.encrypt(obj,
            this.encryptionKey).toString());
    }

    decrypt(hash: string) {
        return CryptoJS.AES.decrypt(decodeURIComponent(hash),
            this.encryptionKey).toString(CryptoJS.enc.Utf8);
    }
}
