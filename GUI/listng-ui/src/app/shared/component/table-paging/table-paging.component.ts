import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Paging} from '../../../core/model/paging.model';

const FILTER_PAG_REGEX = /[^0-9]/g;

@Component({
    selector: 'app-table-paging',
    templateUrl: './table-paging.component.html'
})
export class TablePagingComponent implements OnInit {

    // @ts-ignore
    @Input() paging : Paging;

    @Input() totalItems : any;

    @Input() limits?: number[];

    @Output() onChange = new EventEmitter();

    totalPages = 1;

    constructor() {}

    ngOnInit(): void {
        if (!this.limits) {
            this.limits = [50, 100, 500, 1000];
        }
        if (!this.paging) {
            this.paging = {
                page: 1,
                pageSize: 100,
                orders: []
            } as Paging;
        }
        this.totalPages = this.calcPages();
    }

    selectPage(page: string) {
        this.paging.page = parseInt(page, 10) || 1;
    }

    formatInput(input: HTMLInputElement) {
        input.value = input.value.replace(FILTER_PAG_REGEX, '');
    }

    getCurrentLabel() {
        const from = (this.paging.page - 1) * this.paging.pageSize + 1;
        const to = Math.min(this.paging.page * this.paging.pageSize, this.totalItems);
        return `${from} - ${to}`;
    }

    changeSize(value : number) {
        this.paging.pageSize = value;
        this.totalPages = this.calcPages();
        this.onChange.emit(this.paging);
    }

    calcPages() {
        return Math.ceil(this.totalItems / this.paging.pageSize);
    }
}
