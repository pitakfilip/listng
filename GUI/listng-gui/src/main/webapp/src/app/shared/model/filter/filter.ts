import {FilterType} from './filter-type';

export interface Filter {
    field: string;
    label: string;
    type: FilterType;
    value: any;
    default: any;
    data: any;
}
