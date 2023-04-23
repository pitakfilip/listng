import {UserCoursePermission} from './user-course-permission';

export interface CourseBase {
    id: number;
    name: string;
    short: string;
    students: number;
}

export interface Course extends CourseBase {

    students: number; // TODO vymazat z base!!
}

export interface CoursePermission {
    id: number,
    permission: UserCoursePermission;
}
