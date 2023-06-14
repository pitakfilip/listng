import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PERIOD_API, PUBLIC_PERIOD_API} from '../consts/api-path.consts';
import {RestReposnse} from '../model/restReposnse';
import {PagingParams} from '../model/paging.model';
import {Period} from '../model/period';

@Injectable({
    providedIn: 'root'
})
export class PeriodApiService {

    constructor(private http: HttpClient) {
    }

    getPeriods() {
        return this.http.get<RestReposnse>(PUBLIC_PERIOD_API + '/periods');
    }

    getPeriodPage(paging: PagingParams) {
        return this.http.post<RestReposnse>(PERIOD_API + '/page', paging);
    }

    deletePeriod(periodId: number) {
        return this.http.get<RestReposnse>(PERIOD_API + `/${periodId}/delete`)
    }


    setActive(periodId: number, state: boolean) {
        return this.http.get<RestReposnse>(PERIOD_API + `/${periodId}/active/${state}`)
    }

    savePeriod(period: Period) {
        return this.http.post<RestReposnse>(PERIOD_API + '/save', period);
    }
}
