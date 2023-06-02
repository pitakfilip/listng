import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PUBLIC_USER_API, USER_API} from '../consts/api-path.consts';
import {RestReposnse} from '../model/restReposnse';

@Injectable({
    providedIn: 'root'
})
export class UserPermissionApiService {

    constructor(private http: HttpClient) {
    }

    getPeriodPermissions(periodId: number) {
        return this.http.get<RestReposnse>(PUBLIC_USER_API + `/period/${periodId}/permissions`);
    }

    getUserPermissions(userId: number) {
        return this.http.get<RestReposnse>(USER_API + `/${userId}/permissions`);
    }
}
