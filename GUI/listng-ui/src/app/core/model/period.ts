import {MultiLang} from './multilang';

export interface Period {
    id: number;
    name: MultiLang;
    start: Date;
    end: Date;
    active: boolean;
}
