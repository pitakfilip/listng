import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {COURSE_API, PUBLIC_COURSE_API, ROOM_API} from '../consts/api-path.consts';
import {RestReposnse} from '../model/restReposnse';
import {PagingParams} from '../model/paging.model';
import {Course} from '../model/course';

@Injectable({
    providedIn: 'root'
})
export class CourseApiService {

    constructor(private http: HttpClient) {
    }

    getOfPeriod(periodId: number) {
        return this.http.get<RestReposnse>(PUBLIC_COURSE_API + `/${periodId}/period`);
    }

    getCoursePage(paging: PagingParams) {
        return this.http.post<RestReposnse>(COURSE_API + '/page', paging);
    }

    saveCourse(course: Course) {
        return this.http.post<RestReposnse>(COURSE_API + '/save', course);
    }

    copyCourses(courseIds: number[], periodId: number) {
        const params = new HttpParams().set('periodId', periodId);

        return this.http.post<RestReposnse>(COURSE_API + '/copy', courseIds, {params});
    }

    deleteCourse(courseId: number) {
        return this.http.delete<RestReposnse>(COURSE_API + `/${courseId}/delete`);
    }

    deleteCourses(courseIds: number[]) {
        return this.http.post<RestReposnse>(COURSE_API + '/bulk/delete', courseIds);
    }
}
