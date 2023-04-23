import {Component} from '@angular/core';
import {Filter} from '../../core/model/filter/filter';
import {FilterType} from '../../core/model/filter/filter-type';
import {User, UserBase} from '../../core/model/user.model';
import {CourseBase} from '../../core/model/course';
import {SelectionModel} from '@angular/cdk/collections';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {SharedModule} from '../../shared/shared.module';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {UserDetailComponent} from './user-detail/user-detail.component';
import {TranslateModule} from '@ngx-translate/core';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    imports: [
        MatButtonToggleModule,
        MatTableModule,
        MatIconModule,
        SharedModule,
        MatCheckboxModule,
        UserDetailComponent,
        TranslateModule,
        NgIf,
    ],
    standalone: true
})
export class UserComponent {

    isRoot: boolean;
    studentTab = true;

    users: UserBase[];
    activeCourses: CourseBase[];

    filters = [
        { field: 'name', label: 'user.name', type: FilterType.FULL_TEXT, value: '', default: '' } as Filter,
        { field: 'email', label: 'user.email', type: FilterType.FULL_TEXT, value: '', default: '' } as Filter
    ];

    // Table properties
    displayedColumns = ['select', 'name', 'email', 'courses'];
    initialSelection = [];
    allSelected = false;
    allowMultiSelect = true;
    selection = new SelectionModel<number>(this.allowMultiSelect, this.initialSelection);
    expandedUserId: number;

    // TODO docasne
    dummy_course = {
        id: 1,
        name: 'Progamovanie (4)',
        short: 'Java'
    } as CourseBase

    dummy_user = {
        id: 1,
        name: 'Ferko Mrkva',
        email: 'mrkva123@uniba.sk',
        courses: [this.dummy_course],
        lastLogin: new Date(Date.now())
    } as UserBase;

    constructor() {
        this.isRoot = true; // TODO GET PRAVA BY USER

        this.getActiveCourses();
        this.getUserData();

        this.users = [];
        for (let i = 0 ; i < 10 ; i++) {
            this.users.push({...this.dummy_user, id: i+1});
        }
        this.expandedUserId = 1;
    }

    getActiveCourses() {
        const dummy = {
            id: 1,
            name: 'Progamovanie (4)',
            short: 'Java'
        } as CourseBase;

        this.activeCourses = [this.dummy_course, dummy, dummy, dummy, dummy, dummy, dummy, dummy, dummy, dummy, dummy, dummy];
        console.log(this.activeCourses);
    }

    getUserData() {

    }

    swapType(students : boolean) {
        this.studentTab = students;
        this.getUserData();
    }

    onFilterChange(filters: Filter[]) {
        console.log(filters);
    }

    toggleRow(userId: number) {
        this.selection.toggle(userId);
        this.checkAllSelected();
    }

    toggleAllRows() {
        this.users.forEach(user => {
            if (this.allSelected) {
                this.selection.deselect(user.id);
            } else {
                this.selection.select(user.id);
            }
        });

        this.allSelected = !this.allSelected;
    }

    isAllSelected() {
        return this.allSelected;
    }

    checkAllSelected() {
        let foundUnselected = false;
        this.users.forEach(user => {
            if (!this.selection.isSelected(user.id)){
                foundUnselected = true;
            }
        });
        this.allSelected = !foundUnselected;
    }

    toggleDetail(userId: number) {
        if (this.expandedUserId !== userId){
            this.expandedUserId = userId;
        } else {
            this.expandedUserId = -1;
        }

    }
}
