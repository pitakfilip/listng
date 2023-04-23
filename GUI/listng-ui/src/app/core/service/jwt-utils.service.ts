import {Injectable} from '@angular/core';
import {JwtToken} from '../model/security/jwt-token';
import {TokenUser} from '../model/security/token-user';

@Injectable({
    providedIn: 'root'
})
export class JwtUtilsService {

    parseJwt(raw: string): JwtToken{
        const data = raw.split(';');
        return {
            payload: this.parseJwtBody(data[0]),
            expires: new Date(data[3].split('=')[1])
        } as JwtToken;
    }

    private parseJwtBody(token: string) {
        const jwt = token.split('=')[1];
        return jwt.split('.')[1];
    }

    isValid(jwt: JwtToken){
        return !this.isExpired(jwt) && !this.isPayloadValid(jwt);
    }

    private isExpired(jwt: JwtToken){
        return jwt.expires < new Date();
    }

    private isPayloadValid(jwt: JwtToken){
        try {
            const user = JSON.parse(jwt.payload) as TokenUser;
        } catch(e) {
           return false;
        }
        return true;
    }
}
