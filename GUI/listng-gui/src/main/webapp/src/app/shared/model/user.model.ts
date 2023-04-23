import {Course} from './course';

export interface UserBase {
    id: number;
    name: string;
    email: string;
}

export interface User extends UserBase{
    courses: Course[];
    lastLogin: Date;
}
