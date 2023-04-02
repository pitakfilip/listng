import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'paging'
})
export class PagingPipe implements PipeTransform {

    transform(value: unknown, ...args: unknown[]): unknown {
        return null;
    }

}
