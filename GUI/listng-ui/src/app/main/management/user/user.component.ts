import {Component} from '@angular/core';
import {Filter} from '../../../core/model/filter/filter';
import {FilterType} from '../../../core/model/filter/filter-type';
import {SelectionModel} from '@angular/cdk/collections';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {SharedModule} from '../../../shared/shared.module';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {MatTabsModule} from '@angular/material/tabs';
import {
    PageResponse,
    pagingFactory,
    PagingParams,
    SortDirection,
    sortFactory,
    SortParams
} from '../../../core/model/paging.model';
import {UserApiService} from '../../../core/api/user-api.service';
import {MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import {MatButtonModule} from '@angular/material/button';
import {Utils} from '../../../core/util/utils';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmModalComponent} from '../../../shared/component/confirm-modal/confirm-modal.component';
import {User, SystemRole} from '../../../core/model/user.model';
import {EditUserModalComponent} from './edit-user-modal/edit-user-modal.component';
import {MatMenuModule} from '@angular/material/menu';
import {NewUserModalComponent} from './new-user-modal/new-user-modal.component';
import {EditUsersModalComponent} from './edit-users-modal/edit-users-modal.component';
import {FilterModalComponent} from '../../../shared/component/filter/filter-modal/filter-modal.component';
import {FilterOption} from '../../../core/model/filter/filter-option';
import {ImportUsersModalComponent} from './import-users-modal/import-users-modal.component';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    imports: [
        CoreModule,
        MatButtonToggleModule,
        MatTableModule,
        MatIconModule,
        SharedModule,
        MatCheckboxModule,
        NgIf,
        MatTabsModule,
        NgClass,
        MatPaginatorModule,
        MatButtonModule,
        MatTooltipModule,
        MatMenuModule,
        NgForOf,
    ],
    standalone: true
})
export class UserComponent {

    student = SystemRole.STUDENT;
    teacher = SystemRole.TEACHER;

    // Table params
    paging: PagingParams;
    defaultSize = 20;
    pageSizes = [50, 100, 200, 500];
    columns = ['select', 'name', 'email', 'actions'];
    $page: PageResponse;
    sort: SortParams;

    studentTab = true;
    initSelect = [];
    selection: SelectionModel<number>;
    names: Map<number, string>;
    allSelected = false;

    courseOptions: FilterOption[] = [];
    filters: Filter[];

    constructor(private userApi: UserApiService,
                public dialog: MatDialog) {
        this.init();
        this.loadData();
        this.initFilters();
    }

    // initialization filters, paging and selection model
    init() {
        this.sort = sortFactory();
        this.paging = pagingFactory(this.defaultSize);
        this.selection = new SelectionModel<number>(true, this.initSelect);
        this.names = new Map<number, string>();
    }

    // TODO
    initFilters() {
        this.courseOptions = [
            { id: 1, level: 0, label: { SK: "Programovanie 4 Java", EN: "Programming 4 Java"}, value: 'Java'} as FilterOption,
            { id: 2, level: 0, label: { SK: "Programovanie 1 Python", EN: "Programming 2 Python"}, value: 'Py1'} as FilterOption,
            { id: 3, level: 0, label: { SK: "Programovanie 2 Python", EN: "Programming 2 Python"}, value: 'Py2'} as FilterOption,
            { id: 4, level: 0, label: { SK: "Programovanie 3 CPP", EN: "Programming 3 CPP"}, value: 'Cpp'} as FilterOption,
            { id: 5, level: 0, label: { SK: "Principy Pocitacov OS", EN: "Computer Principles OS"}, value: 'OS'} as FilterOption,
            { id: 6, level: 0, label: { SK: "Matematicka Analyza", EN: "Mathematical Analysis"}, value: 'Mat2'} as FilterOption
        ]
        this.filters = [
            {field: 'name', label: 'user.name', type: FilterType.FULL_TEXT, value: '', default: ''} as Filter,
            {field: 'email', label: 'user.email', type: FilterType.FULL_TEXT, value: '', default: ''} as Filter,
            {field: 'active', label: 'user.isActive', type: FilterType.BOOLEAN, value: undefined, default: undefined} as Filter,
            {field: 'course', label: 'user.course.belongs', type: FilterType.CHOICE, value: [], default: [], data: this.courseOptions} as Filter
        ];
    }

    onPagingChange($event: PageEvent) {
        this.paging.page = $event.pageIndex;
        this.paging.size = $event.pageSize;
        this.loadData();
    }

    loadData() {
        this.paging.sort = [this.sort];
        this.userApi.getUsersPage(this.paging, this.studentTab).subscribe(response => {
            this.$page = response.payload;
            this.checkAllSelected();
        });
    }

    isStudentTab() {
        return this.studentTab;
    }

    openTab(tab: SystemRole) {
        if (tab === SystemRole.STUDENT && !this.studentTab) {
            this.studentTab = true;
            this.init();
            this.loadData();
        }
        if (tab === SystemRole.TEACHER && this.studentTab) {
            this.studentTab = false;
            this.init();
            this.loadData();
        }
    }

    clearSelection() {
        this.selection.clear();
    }

    toggleRow(user: User) {
        this.selection.toggle(user.id);

        if (this.selection.isSelected(user.id)) {
            this.names.set(user.id, user.name);
        } else {
            this.names.delete(user.id);
        }
        this.checkAllSelected();
    }

    toggleAllRows() {
        if (Utils.exists(this.$page) && Utils.exists(this.$page.data)) {
            this.$page.data.forEach(user => {
                if (this.allSelected) {
                    this.selection.deselect(user.id);
                    this.names.delete(user.id);
                } else {
                    this.selection.select(user.id);
                    this.names.set(user.id, user.name);
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
            for (let user of this.$page.data) {
                if (!this.selection.isSelected(user.id)) {
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

    sortByColumn(field: string) {
        this.sort = Utils.updateSort(this.sort, field);
        this.loadData();
    }

    getSortIcon(field: string) {
        if (Utils.exists(this.sort.field) && this.sort.field === field) {
            return this.sort.direction === SortDirection.DESC ? 'keyboard_arrow_down' : 'keyboard_arrow_up';
        } else {
            return 'keyboard_arrow_down';
        }
    }

    onDelete(id: number) {
        const index = this.$page.data.findIndex(user => user.id === id);
        if (index < 0) {
            return;
        }

        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'users.action.modal.delete', specify: this.$page.data[index].name},
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.userApi.deleteUsers([id]).subscribe(response => {
                    if (response.success) {
                        this.loadData();
                    }
                });
            }
        });
    }

    openBulkEdit() {
        if (!this.selection.isEmpty() && this.studentTab) {
            const dialogRef = this.dialog.open(EditUsersModalComponent, {
                data: { userIds: this.selection.selected }
            });

            dialogRef.afterClosed().subscribe(result => {
                if (result === 'submit') {
                    this.loadData();
                    this.selection.clear();
                }
            });
        }
    }

    onBulkDelete() {
        const detail = [];
        this.names.forEach((value, key) => detail.push(value));

        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'users.action.modal.delete.bulk', simpleList: detail},
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.userApi.deleteUsers(this.selection.selected).subscribe(response => {
                    if (response.success) {
                        this.loadData();
                        this.selection.clear();
                    }
                });
            }
        });
    }

    onEdit(id: number) {
        this.userApi.isAdmin().subscribe(response => {
            if (response.success) {
                const dialogRef = this.dialog.open(EditUserModalComponent, {
                    data: { userId: id , isRoot: response.payload},
                });
                dialogRef.afterClosed().subscribe(response => {
                    if (response === 'submit') {
                        this.loadData();
                    }
                });
            }
        })

    }

    openNewForm() {
        const dialogRef = this.dialog.open(NewUserModalComponent);
        dialogRef.afterClosed().subscribe(response => {
            if (response === 'submit') {
                this.loadData();
            }
        });
    }

    openImport() {
        const dialogRef = this.dialog.open(ImportUsersModalComponent);
        dialogRef.afterClosed().subscribe(response => {
            if (response === 'submit') {
                this.loadData();
            }
        });
    }

    openFilter() {
        const dialogRef = this.dialog.open(FilterModalComponent, {
            data : { filterConfigs: this.filters }
        });

        dialogRef.afterClosed().subscribe(response => {
        })
    }
}
