import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from '../../../core/model/user.model';
import {CourseBase} from '../../../core/model/course';
import {UserCoursePermission, UserCourseRole, UserCourseState} from '../../../core/model/user-course-permission';
import {FormControl, FormsModule, Validators} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {TranslateModule} from '@ngx-translate/core';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-user-detail',
    templateUrl: './user-detail.component.html',
    imports: [
        MatTableModule,
        TranslateModule,
        MatButtonToggleModule,
        MatIconModule,
        MatTooltipModule,
        FormsModule,
        NgIf
    ],
    standalone: true
})
export class UserDetailComponent implements OnInit {

    @Input() studentTab: boolean;

    @Input() user: User;

    @Input() courses: CourseBase[];

    @Output() onSubmit = new EventEmitter();

    newName: string;
    newEmail: string;
    newPass = '';
    initPermissions: { course: CourseBase, permission: UserCoursePermission}[];
    permissions: { course: CourseBase, permission: UserCoursePermission}[];

    emailControl : FormControl;
    passwordControl : FormControl;

    constructor() {
    }

    ngOnInit() {
        this.newName = this.user.name;
        this.newEmail = this.user.email;

        this.setPermissions();

        this.emailControl = new FormControl(this.user.email, {
            validators: [Validators.required, Validators.email],
            updateOn: 'submit'
        });

        this.passwordControl = new FormControl(this.newPass, {
            validators: [Validators.required, Validators.minLength(6)],
            updateOn: 'submit'
        });
    }

    showOwnerRole() {
        return !this.studentTab;
    }

    setPermissions() {
        this.permissions = [];

        // TODO GET FROM DB
        this.courses.forEach(course => {
            this.permissions.push({
                course: course,
                permission: {
                    state: UserCourseState.PENDING,
                    role: undefined
                } as UserCoursePermission})
        });
    }

    isPending(index: number) {
        return this.permissions[index].permission.state === UserCourseState.PENDING;
    }

    isAccepted(index: number) {
        return this.permissions[index].permission.state === UserCourseState.ACCEPTED;
    }

    isDenied(index: number) {
        return this.permissions[index].permission.state === UserCourseState.DENIED;
    }

    isViewer(index: number) {
        return this.permissions[index].permission.role === UserCourseRole.VIEWER;
    }

    isAttendee(index: number) {
        return this.permissions[index].permission.role === UserCourseRole.ATTENDEE;
    }

    isEvaluator(index: number) {
        return this.permissions[index].permission.role === UserCourseRole.EVALUATOR;
    }

    isOwner(index: number) {
        return this.permissions[index].permission.role === UserCourseRole.OWNER;
    }

    acceptUser(index: number){
        if (this.permissions[index].permission.state !== undefined) {
            this.permissions[index].permission.state = UserCourseState.ACCEPTED;
            this.permissions[index].permission.role = UserCourseRole.ATTENDEE;
        }
    }

    denyUser(index: number) {
        if (this.permissions[index].permission.state !== undefined) {
            this.permissions[index].permission.state = UserCourseState.DENIED;
            this.permissions[index].permission.role = undefined;
        }
    }

    setAsViewer(index: number) {
        this.permissions[index].permission.role = UserCourseRole.VIEWER;
    }

    setAsAttendee(index: number) {
        this.permissions[index].permission.role = UserCourseRole.ATTENDEE;
    }

    setAsEvaluator(index: number) {
        this.permissions[index].permission.role = UserCourseRole.EVALUATOR;
    }

    setAsOwner(index: number) {
        this.permissions[index].permission.role = UserCourseRole.OWNER;
    }

    submitChanges() {

        this.onSubmit.emit();
    }
}
