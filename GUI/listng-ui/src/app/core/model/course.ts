import {EMPTY_MULTILANG, MultiLang, multiLangOf} from './multilang';
import {Room} from './room';
import {Time} from '@angular/common';

export interface CourseBase {
    id: number;
    periodId: number;
    name: MultiLang;
    abbreviation: MultiLang;
}

export interface Course extends CourseBase {
    groups: Group[];
}

export interface Class {
    id: number;
    room: Room;
    day: number;
    time: Time;
    duration: number;
}

export interface Group {
    id: number;
    name: MultiLang;
    courseId: number;
    isDefault: boolean;
    capacity: number;
    classes: Class[];
}

export function groupFactory() {
    return {
        name: {...EMPTY_MULTILANG},
        isDefault: false,
        classes: []
    } as Group
}

export function courseFactory() {
    const defaultGroup = {
        name: multiLangOf('Predvolená', 'Default'),
        isDefault: true,
        classes: []
    } as Group;

    return {
        name: {...EMPTY_MULTILANG},
        abbreviation: {...EMPTY_MULTILANG},
        groups: [defaultGroup]
    } as Course;
}
