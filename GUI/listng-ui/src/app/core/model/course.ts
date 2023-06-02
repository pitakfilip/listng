import {MultiLang} from './multilang';

export interface CourseBase {
    id: number;
    periodId: number;
    name: MultiLang;
    abbreviation: MultiLang;
}
