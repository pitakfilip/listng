import {SortDirection, SortParams} from '../model/paging.model';

export class Utils {

    static exists(value: any): boolean {
        return typeof value !== 'undefined' && value !== null && value !== undefined;
    }

    static updateSort(sort: SortParams, field: string) {
        if (this.exists(sort.field) && sort.field === field) {
            if (sort.direction === SortDirection.DESC) {
                sort.direction = SortDirection.ASC;
            } else {
                sort.direction = SortDirection.DESC;
            }
        } else {
            sort.field = field;
            sort.direction = SortDirection.DESC;
        }

        return sort;
    }
}
