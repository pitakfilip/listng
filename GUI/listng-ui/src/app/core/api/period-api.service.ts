import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PERIOD_API} from '../consts/api-path.consts';
import {RestReposnse} from '../model/restReposnse';

@Injectable({
    providedIn: 'root'
})
export class PeriodApiService {

    constructor(private http: HttpClient) {
    }

    getPeriods() {
        return this.http.get<RestReposnse>(PERIOD_API + '/periods');
    }
}
