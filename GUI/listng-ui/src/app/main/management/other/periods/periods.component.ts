import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
    PageResponse,
    pagingFactory,
    PagingParams,
    SortDirection, sortFactory,
    SortParams
} from '../../../../core/model/paging.model';
import {PeriodApiService} from '../../../../core/api/period-api.service';
import {MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import {CoreModule} from '../../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatTableModule} from '@angular/material/table';
import {Utils} from '../../../../core/util/utils';
import {ConfirmModalComponent} from '../../../../shared/component/confirm-modal/confirm-modal.component';
import {MatDialog} from '@angular/material/dialog';
import {PeriodModalComponent} from './period-modal/period-modal.component';

@Component({
    selector: 'app-periods',
    standalone: true,
    imports: [CommonModule, CoreModule, MatButtonModule, MatIconModule, MatMenuModule, MatPaginatorModule, MatTooltipModule, MatCheckboxModule, MatTableModule],
    templateUrl: './periods.component.html'
})
export class PeriodsComponent {

    paging: PagingParams;
    sort: SortParams;
    defaultSize = 10;
    pageSizes = [10, 20, 50];
    columns = ['name', 'start', 'end', 'active', 'actions'];
    $page: PageResponse;

    constructor(private periodApi: PeriodApiService,
                public dialog: MatDialog) {
        this.init();
        this.loadData();
    }

    init() {
        this.paging = pagingFactory(this.defaultSize);
        this.sort = sortFactory();
        this.sort.field = 'end';
    }

    loadData() {
        this.paging.sort = [this.sort];
        this.periodApi.getPeriodPage(this.paging).subscribe(response => {
            if (response.success) {
                this.$page = response.payload;
            }
        })
    }

    onPagingChange($event: PageEvent) {
        this.paging.page = $event.pageIndex;
        this.paging.size = $event.pageSize;
        this.loadData();
    }

    sortByColumn(field: string) {
        this.sort = Utils.updateSort(this.sort, field);
        this.loadData();
    }

    isSortedBy(field: string) {
        return this.sort.field === field;
    }

    getSortIcon(field: string) {
        if (Utils.exists(this.sort.field) && this.sort.field === field) {
            return this.sort.direction === SortDirection.DESC ? 'keyboard_arrow_down' : 'keyboard_arrow_up';
        } else {
            return 'keyboard_arrow_down';
        }
    }

    onCreate() {
        const dialogRef = this.dialog.open(PeriodModalComponent, {
            data: { period: undefined }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'success') {
                this.loadData();
            }
        })
    }

    onEdit(periodId: number) {
        const index = this.$page.data.findIndex(period => period.id === periodId);
        if (index < 0) {
            return;
        }

        const dialogRef = this.dialog.open(PeriodModalComponent, {
            data: { period: this.$page.data[index] }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'success') {
                this.loadData();
            }
        })
    }

    onSetActive(periodId: number) {
        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'other.periods.modal.active'}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.periodApi.setActive(periodId, true).subscribe( response => {
                    if (response.success) {
                        this.loadData();
                    }
                });
            }
        });
    }

    onSetInactive(periodId: number) {
        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'other.periods.modal.inactive'}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.periodApi.setActive(periodId, false).subscribe( response => {
                    if (response.success) {
                        this.loadData();
                    }
                });
            }
        });
    }

    onDelete(periodId: number) {
        const index = this.$page.data.findIndex(period => period.id === periodId);
        if (index < 0) {
            return;
        }

        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'other.periods.modal.delete', specify: this.$page.data[index].name}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.periodApi.deletePeriod(periodId).subscribe(response => {
                    if (response.success) {
                        this.loadData();
                    }
                })
            }
        });
    }


}
