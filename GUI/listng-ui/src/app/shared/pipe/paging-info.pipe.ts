import {Pipe, PipeTransform} from '@angular/core';
import {Paging} from '../model/paging.model';

@Pipe({
    name: 'pagingInfo'
})
export class PagingInfoPipe implements PipeTransform {

    transform(paging: Paging, entryCount: number): string {
        const from = (paging.page - 1) * paging.pageSize + 1;
        const to = Math.min(paging.page * paging.pageSize, entryCount);
        return `${from} - ${to}`;
    }

}
