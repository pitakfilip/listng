import {Course} from './course';

export interface StudentBase {
    id: number;
    name: string;
    email: string;
    courseCount: number;
}

export interface Student extends StudentBase{
    courses: Course[];
    lastLogin: Date;
}
