import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'roundUp'
})
export class RoundUpPipe implements PipeTransform {

    transform(value: unknown, ...args: unknown[]): unknown {
        return null;
    }

}
