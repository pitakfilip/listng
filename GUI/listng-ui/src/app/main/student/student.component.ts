import {Component} from '@angular/core';
import {StudentBase} from '../../core/model/student.model';
import {FilterType} from '../../core/model/filter/filter-type';
import {Filter} from '../../core/model/filter/filter';
import {FilterOption} from '../../core/model/filter/filter-option';

@Component({
    selector: 'app-student',
    templateUrl: './student.component.html'
})
export class StudentComponent {

    filters: Filter[];

    dummy = {
        name: 'Ferko Mrkva',
        email: 'mrkva123@uniba.sk',
        courseCount: 3
    } as StudentBase;

    students: any;

    selected: StudentBase[];

    toggledStudent: number = 0;

    options: FilterOption[];

    constructor() {
        this.students = [];
        this.selected = [];
        for (let i = 0; i < 5; i++) {
            let s = {...this.dummy} as StudentBase;
            s.id = i + 1;
            this.students.push(s);
        }

        this.options = [
            { id: 1, level: 0, label: 'de1111111', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 2, level: 0, label: 'de222222222222', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 3, level: 1, label: 'de33333333', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 4, level: 1, label: 'de444444444', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 5, level: 2, label: 'dedadsadsdasdasdasdbil5', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 6, level: 2, label: 'dedasdasdasbil6', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 7, level: 3, label: 'deasdasdasdasbil7', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 8, level: 4, label: 'debil8', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 9, level: 0, label: 'debil9', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 10, level: 0, label: 'debil11', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 11, level: 0, label: 'debil12', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 12, level: 0, label: 'debil13', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 13, level: 0, label: 'debil14', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 14, level: 0, label: 'debil15', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 15, level: 0, label: 'debil16', value: 'amater fero ajajajaj'} as FilterOption,
            { id: 16, level: 0, label: 'debil17', value: 'amater fero ajajajaj'} as FilterOption
        ]


        this.filters = [{ field: 'type', label: 'dummy', type: FilterType.CHOICE, value: [], default: [], data: this.options} as Filter,
            { field: 'name', label: 'student.name', type: FilterType.FULL_TEXT, value: '', default: '' } as Filter,
            { field: 'email', label: 'student.email', type: FilterType.FULL_TEXT, value: '', default: '' } as Filter,
            { field: 'active', label: 'student.active', type: FilterType.BOOLEAN, value: undefined, default: undefined} as Filter
        ];
    }

    select(item: StudentBase) {
        const indx = this.selected.findIndex(elem => elem.id === item.id);
        if (indx !== -1) {
            this.selected.splice(indx, 1);
        } else {
            this.selected.push(item);
        }
        console.log(this.selected);
    }

    isSelected(id: number) {
        const indx = this.selected.findIndex(elem => elem.id === id);
        return indx !== -1;
    }

    openDetail(id: number) {
        if (this.toggledStudent === id) {
            this.toggledStudent = 0;
        } else {
            this.toggledStudent = id;
        }
    }

    onFilterChange(filters: Filter[]) {
        console.log(filters);
    }
}
