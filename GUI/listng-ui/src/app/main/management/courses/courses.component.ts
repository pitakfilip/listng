import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatTooltipModule} from '@angular/material/tooltip';
import {
    PageResponse,
    pagingFactory,
    PagingParams,
    SortDirection,
    sortFactory,
    SortParams
} from '../../../core/model/paging.model';
import {MatDialog} from '@angular/material/dialog';
import {CourseApiService} from '../../../core/api/course-api.service';
import {SelectionModel} from '@angular/cdk/collections';
import {Utils} from '../../../core/util/utils';
import {CourseBase} from '../../../core/model/course';
import {EMPTY_MULTILANG, MultiLang} from '../../../core/model/multilang';
import {PeriodApiService} from '../../../core/api/period-api.service';
import {Period} from '../../../core/model/period';
import {ConfirmModalComponent} from '../../../shared/component/confirm-modal/confirm-modal.component';
import {CourseModalComponent} from './course-modal/course-modal.component';
import Swal from 'sweetalert2';
import {TranslateService} from '@ngx-translate/core';

@Component({
    selector: 'app-courses',
    standalone: true,
    imports: [CommonModule, CoreModule, MatButtonModule, MatCheckboxModule, MatIconModule, MatMenuModule, MatPaginatorModule, MatTableModule, MatTooltipModule],
    templateUrl: './courses.component.html'
})
export class CoursesComponent {

    paging: PagingParams;
    sort: SortParams;
    defaultSize = 25;
    pageSizes = [25, 50, 100];
    columns = ['select', 'name', 'period', 'abbreviation', 'groups', 'actions'];
    $page: PageResponse;
    periods: Map<number, Period> = new Map();

    initSelect = [];
    selection: SelectionModel<number>;
    names: Map<number, MultiLang> = new Map();
    allSelected = false;

    constructor(public dialog: MatDialog,
                private courseApi: CourseApiService,
                private periodApi: PeriodApiService,
                private translate: TranslateService) {
        this.init();
        this.loadData();
        this.loadPeriods();
    }

    init() {
        this.paging = pagingFactory(this.defaultSize);
        this.sort = sortFactory();
        this.selection = new SelectionModel<number>(true, this.initSelect);
        this.sort.field = 'id';
    }

    loadData() {
        this.paging.sort = [this.sort];
        this.courseApi.getCoursePage(this.paging).subscribe(response => {
            if (response.success) {
                this.$page = response.payload;
            }
        })
    }

    loadPeriods() {
        this.periodApi.getPeriods().subscribe(response => {
            if (response.success) {
                for (let period of response.payload) {
                    this.periods.set(period.id, period);
                }
            }
        })
    }

    getPeriodName(periodId: number) {
        if (this.periods.has(periodId)) {
            return this.periods.get(periodId).name;
        }
        return EMPTY_MULTILANG;
    }

    onPagingChange($event: PageEvent) {
        this.paging.page = $event.pageIndex;
        this.paging.size = $event.pageSize;
        this.loadData();
    }

    clearSelection() {
        this.selection.clear();
    }

    toggleRow(course: CourseBase) {
        this.selection.toggle(course.id);

        if (this.selection.isSelected(course.id)) {
            this.names.set(course.id, course.name);
        } else {
            this.names.delete(course.id);
        }
        this.checkAllSelected();
    }

    toggleAllRows() {
        if (Utils.exists(this.$page) && Utils.exists(this.$page.data)) {
            this.$page.data.forEach(course => {
                if (this.allSelected) {
                    this.selection.deselect(course.id);
                    this.names.delete(course.id);
                } else {
                    this.selection.select(course.id);
                    this.names.set(course.id, course.name);
                }
            });
            this.allSelected = !this.allSelected;
        }
    }

    isAllSelected() {
        return this.allSelected;
    }

    checkAllSelected() {
        let foundUnselected = false;
        if (Utils.exists(this.$page) && Utils.exists(this.$page.data)) {
            for (let course of this.$page.data) {
                if (!this.selection.isSelected(course.id)) {
                    foundUnselected = true;
                    break;
                }
            }
        }
        this.allSelected = !foundUnselected;
    }

    getSelectedCount() {
        return this.selection.selected.length;
    }

    isSortedBy(field: string) {
        return this.sort.field === field;
    }

    sortByColumn(field: string) {
        console.log(this.sort);
        this.sort = Utils.updateSort(this.sort, field);
        console.log(this.sort);
        this.loadData();
    }

    getSortIcon(field: string) {
        if (Utils.exists(this.sort.field) && this.sort.field === field) {
            return this.sort.direction === SortDirection.DESC ? 'keyboard_arrow_down' : 'keyboard_arrow_up';
        } else {
            return 'keyboard_arrow_down';
        }
    }

    onCreate() {
        const dialogRef = this.dialog.open(CourseModalComponent, {
            data: { course: undefined }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'success') {
                this.loadData();
            }
        });
    }

    onCopy(courseId: number) {
        let options = new Map<string, string>();
        for (let period of this.periods.values()) {
            options.set('' + period.id, (this.translate.currentLang === 'sk')? period.name.SK : period.name.EN);
        }

        Swal.fire({
            icon: 'question',
            title: this.translate.instant('courses.modal.copy'),
            text: this.translate.instant('courses.modal.copy.info'),
            input: 'select',
            inputOptions: options,
            showCancelButton: true,
            confirmButtonColor: '#aed581',
            cancelButtonColor: '#f44336',
            confirmButtonText: this.translate.instant('label.confirm'),
            cancelButtonText: this.translate.instant('label.close'),
        }).then(result => {
            if (result.isConfirmed) {
                const selectedPeriodId = Number(result.value);
                this.courseApi.copyCourses([courseId], selectedPeriodId)
                    .subscribe(response => {
                        this.loadData();
                    })
            }
        })
    }

    onBulkCopy() {
        let options = new Map<string, string>();
        for (let period of this.periods.values()) {
            options.set('' + period.id, (this.translate.currentLang === 'sk')? period.name.SK : period.name.EN);
        }

        Swal.fire({
            icon: 'question',
            title: this.translate.instant('courses.modal.copy.bulk'),
            text: this.translate.instant('courses.modal.copy.info'),
            input: 'select',
            inputOptions: options,
            showCancelButton: true,
            confirmButtonColor: '#aed581',
            cancelButtonColor: '#f44336',
            confirmButtonText: this.translate.instant('label.confirm'),
            cancelButtonText: this.translate.instant('label.close'),
        }).then(result => {
            if (result.isConfirmed) {
                const selectedPeriodId = Number(result.value);
                this.courseApi.copyCourses(this.selection.selected, selectedPeriodId)
                    .subscribe(response => {
                        this.loadData();
                        this.selection.clear();
                    })
            }
        })
    }

    onEdit(courseId: number) {
        const index = this.$page.data.findIndex(course => course.id === courseId);
        if (index < 0) {
            return;
        }

        const dialogRef = this.dialog.open(CourseModalComponent, {
            data: { course: this.$page.data[index] }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'success') {
                this.loadData();
            }
        });
    }

    onDelete(courseId: number) {
        const index = this.$page.data.findIndex(course => course.id === courseId);
        if (index < 0) {
            return;
        }

        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'courses.modal.delete', specify: this.$page.data[index].name}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.courseApi.deleteCourse(courseId).subscribe(response => {
                    if (response.success) {
                        this.loadData();
                    }
                })
            }
        });
    }

    onBulkDelete() {
        if (this.selection.isEmpty()) {
            return;
        }
        const selected = [];
        this.names.forEach((value, key) => selected.push(value));

        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'courses.modal.delete.bulk', list: selected}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.courseApi.deleteCourses(this.selection.selected).subscribe(response => {
                    if (response.success) {
                        this.loadData();
                    }
                })
            }
        });
    }

    protected readonly Utils = Utils;
}
