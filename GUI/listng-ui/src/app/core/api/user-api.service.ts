import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PUBLIC_USER_API, USER_API} from '../consts/api-path.consts';
import {PagingParams} from '../model/paging.model';
import {RestReposnse} from '../model/restReposnse';
import {SystemRole, User, UserBase} from '../model/user.model';
import {CoursePermission} from '../model/course-permission';

@Injectable({
    providedIn: 'root'
})
export class UserApiService {

    constructor(private http: HttpClient) {
    }

    // PUBLIC
    requestCourseEntry(courseId: number) {
        return this.http.get<any>(PUBLIC_USER_API + `/${courseId}/request`);
    }

    isAdmin() {
        return this.http.get<RestReposnse>(PUBLIC_USER_API + '/isAdmin');
    }

    // ADMIN
    getUsersPage(paging: PagingParams, students: boolean) {
        let type = 'STUDENT';
        if (!students) {
            type = 'TEACHER';
        }
        return this.http.post<RestReposnse>( USER_API + `/${type}/page`, paging);
    }

    getUser(userId: number) {
        return this.http.get<RestReposnse>(USER_API + `/${userId}/user`);
    }

    getUsers(userIds: number[]) {
        return this.http.post<RestReposnse>(USER_API + '/id/bulk', userIds);
    }

    createUsers(role: SystemRole, users: UserBase[], permissions: CoursePermission[]) {
        return this.http.post<RestReposnse>(USER_API + '/create', { role: role, users: users, permissions: permissions});
    }

    updateUser(user: User, permissions: CoursePermission[]) {
        return this.http.post<RestReposnse>(USER_API + '/update', { user: user, permissions: permissions});
    }

    bulkUpdateUsers(role: SystemRole, users: UserBase[], permissions: CoursePermission[]) {
        return this.http.post<RestReposnse>(USER_API + '/update/bulk', { role: role, users: users, permissions: permissions});
    }

    deleteUsers(ids: number[]) {
        return this.http.post<RestReposnse>(USER_API + '/delete', ids);
    }
}
