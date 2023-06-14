import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Column} from '../column';
import {Paging} from '../../../../core/model/paging.model';

@Component({
    selector: 'app-table',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './table.component.html'
})
export class TableComponent<T> {

    @Input() public rows: any[];

    @Input() public columns: Column<T>[];

    @Input() public paging: Paging;
}
