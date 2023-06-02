import {Component, Injectable, NgModule} from '@angular/core';
import {MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {Subject} from 'rxjs';
import {TranslateService} from '@ngx-translate/core';

@Injectable()
export class ListPaginator implements MatPaginatorIntl {

    changes = new Subject<void>();

    firstPageLabel: string;
    itemsPerPageLabel: string;
    lastPageLabel: string;
    nextPageLabel: string;
    previousPageLabel: string;
    ofLabel: string;

    constructor(private translate: TranslateService) {
        this.firstPageLabel = translate.instant("paging.page.first");
        this.itemsPerPageLabel = translate.instant("paging.itemsPerPage");
        this.lastPageLabel = translate.instant("paging.page.last");
        this.nextPageLabel = translate.instant("paging.page.next");
        this.previousPageLabel = translate.instant("paging.page.previous");
        const of = translate.instant("paging.of");
        const page = translate.instant("paging.page.label");
        this.ofLabel = `${page} ${of}`
    }

    getRangeLabel(page: number, pageSize: number, length: number): string {
        if (length === 0) {
            return `1 ${this.ofLabel} 1`;
        }
        const amountPages = Math.ceil(length / pageSize);
        return `${page + 1} ${this.ofLabel} ${amountPages}`;
    }
}
