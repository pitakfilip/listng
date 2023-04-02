import {Component, Input} from '@angular/core';
import {Student} from '../../../core/model/student.model';
import {CourseBase} from '../../../core/model/course-base';
import {FormControl, Validators} from '@angular/forms';

@Component({
    selector: 'app-student-detail',
    templateUrl: './student-detail.component.html',
    styles: []
})
export class StudentDetailComponent {

    @Input() id! : number;

    student : Student;

    changed = false;
    newPass = "";
    prevEmail = "";
    prevName = "";

    emailControl : FormControl;
    passwordControl : FormControl;


    constructor() {
        this.student = {
            name: 'Ferko Mrkva',
            email: 'mrkva123@uniba.sk',
            courseCount: 3,
            courses: [
                { id: 1, name: "Java prog4", isAdmin: true} as CourseBase,
                { id: 2, name: "Python prog1", isAdmin: false} as CourseBase,
                { id: 3, name: "Python prog2", isAdmin: false} as CourseBase,
                { id: 4, name: "C++ prog3", isAdmin: false} as CourseBase
            ],
            lastLogin: new Date(Date.now())
        } as Student

        this.prevName = this.student.name;
        this.prevEmail = this.student.email;

        this.emailControl = new FormControl(this.student.email, {
            validators: [Validators.required, Validators.email],
            updateOn: 'submit'
        });

        this.passwordControl = new FormControl(this.newPass, {
            validators: [Validators.required, Validators.minLength(6)],
            updateOn: 'submit'
        });
    }

    submitChanges() {

    }

    changedData() {
        return this.student.email !== this.prevEmail || this.student.name !== this.prevName;
    }
}
