import {Injectable} from '@angular/core';
import {User} from '../model/user.model';

@Injectable({
    providedIn: 'root'
})
export class JwtUtilsService {

    parseJwtBody(raw: string): string {
        return raw.split('.')[1];
    }

    isValid(jwt: string){
        try {
            const user = this.getUser(jwt);
            return true;
        } catch (e) {
            return false;
        }
    }

    getUser(jwt: string){
        return JSON.parse(atob(jwt)) as User;
    }
}
