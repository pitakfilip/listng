import {Component, OnInit} from '@angular/core';
import {StudentBase} from '../../core/model/student.model';

@Component({
    selector: 'app-student',
    templateUrl: './student.component.html'
})
export class StudentComponent implements OnInit {

    dummy = {
        name: 'Ferko Mrkva',
        email: 'mrkva123@uniba.sk',
        courseCount: 3
    } as StudentBase

    students : any;

    selected : StudentBase[];

    toggledStudent : number = 0;

    constructor() {
        this.students = [];
        this.selected = [];
        for (let i = 0; i < 5; i++) {
            let s = {...this.dummy} as StudentBase;
            s.id = i+1;
            this.students.push(s);
        }
    }

    ngOnInit(): void {
    }

    select(item : StudentBase) {
        const indx = this.selected.findIndex(elem => elem.id === item.id);
        if (indx !== -1) {
            this.selected.splice(indx, 1);
        } else {
            this.selected.push(item);
        }
        console.log(this.selected);
    }

    isSelected(id : number){
        const indx = this.selected.findIndex(elem => elem.id === id);
        return indx !== -1;
    }

    openDetail(id : number) {
        if (this.toggledStudent === id) {
            this.toggledStudent = 0;
        } else {
            this.toggledStudent = id;
        }
    }
}