import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {COURSE_API} from '../consts/api-path.consts';
import {RestReposnse} from '../model/restReposnse';

@Injectable({
    providedIn: 'root'
})
export class CourseApiService {

    constructor(private http: HttpClient) {}

    getOfPeriod(periodId: number) {
        return this.http.get<RestReposnse>(COURSE_API + `/${periodId}/period`);
    }

    getAll() {
        return this.http.get<RestReposnse>(COURSE_API + '/courses');
    }
}
