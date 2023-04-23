export interface TokenUser {
    id: number;
    email: string;
    name: string;
    role: SystemRole;
    permissions: CoursePermission[];
}

export enum SystemRole {
    ROOT,
    TEACHER,
    STUDENT
}

export interface CoursePermission {
    courseId: number;
    permission: CourseRole;
}

export enum CourseRole {
    VIEWER,
    ATTENDEE,
    EVALUATOR,
    OWNER
}

export enum CourseState {
    PENDING,
    ACCEPTED,
    DENIED
}
