export interface UserCoursePermission {
    state: UserCourseState | undefined;
    role: UserCourseRole | undefined;
}

export enum UserCourseState {
    PENDING,
    ACCEPTED,
    DENIED
}

export enum UserCourseRole {
    VIEWER,
    ATTENDEE,
    EVALUATOR,
    OWNER
}
