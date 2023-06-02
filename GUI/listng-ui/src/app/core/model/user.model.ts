export interface UserBase {
    id: number;
    name: string;
    email: string;
}

export interface User extends UserBase {
    role: SystemRole;
}

export enum SystemRole {
    ROOT = 'ROOT',
    TEACHER = 'TEACHER',
    STUDENT = 'STUDENT'
}
