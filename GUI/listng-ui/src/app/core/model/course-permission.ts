import {SystemRole} from './user.model';

export interface CoursePermission {
    id: number;
    role: UserCourseRole | undefined;
    status: UserCourseStatus | undefined;
    groupId: number;
    courseId: number;
    userId: number;
}

export enum UserCourseStatus {
    PENDING = 'PENDING',
    ACTIVE = 'ACTIVE',
    DENIED = 'DENIED'
}

export enum UserCourseRole {
    NOT_ASSIGNED = 'NOT_ASSIGNED',
    VIEWER = 'VIEWER',
    ATTENDEE = 'ATTENDEE',
    EVALUATOR = 'EVALUATOR',
    OWNER = 'OWNER'
}

export const DEFAULT_STUDENT_PERMISSION = {
    role: UserCourseRole.NOT_ASSIGNED,
    status: undefined,
    groupId: -1
} as CoursePermission;

export function studentPermissionFactory(courseId: number) {
    return {
        role: UserCourseRole.ATTENDEE,
        status: UserCourseStatus.ACTIVE,
        groupId: -1,
        courseId: courseId
    } as CoursePermission;
}

export function teacherPermissionFactory(courseId: number) {
    return {
        courseId: courseId,
        role: UserCourseRole.VIEWER
    } as CoursePermission;
}

export const STUDENT = SystemRole.STUDENT;
export const TEACHER = SystemRole.TEACHER;
export const ROOT = SystemRole.ROOT;
export const ACTIVE = UserCourseStatus.ACTIVE;
export const PENDING = UserCourseStatus.PENDING;
export const DENIED = UserCourseStatus.DENIED;
export const VIEWER = UserCourseRole.VIEWER;
export const ATTENDEE = UserCourseRole.ATTENDEE;
export const EVALUATOR = UserCourseRole.EVALUATOR;
export const OWNER = UserCourseRole.OWNER;
