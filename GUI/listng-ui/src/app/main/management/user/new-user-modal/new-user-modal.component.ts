import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {PermissionSelectComponent} from '../../../../shared/component/permission-select/permission-select.component';
import {ReactiveTypedFormsModule} from '@rxweb/reactive-form-validators';
import {UserApiService} from '../../../../core/api/user-api.service';
import {Utils} from '../../../../core/util/utils';
import {
    ACTIVE,
    ATTENDEE,
    CoursePermission,
    DENIED, EVALUATOR, OWNER, PENDING,
    ROOT,
    STUDENT,
    TEACHER,
    VIEWER
} from '../../../../core/model/course-permission';
import {UserBase, SystemRole} from '../../../../core/model/user.model';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatTableModule} from '@angular/material/table';

@Component({
    selector: 'app-new-user-modal',
    standalone: true,
    imports: [CommonModule, CoreModule, MatButtonModule, MatDialogModule, MatDividerModule, MatIconModule, MatTooltipModule, PermissionSelectComponent, ReactiveTypedFormsModule, MatButtonToggleModule, MatTableModule],
    templateUrl: './new-user-modal.component.html'
})
export class NewUserModalComponent {

    EMPTY = {id: -1, name: '-', email: '-'} as UserBase;

    count = 0;
    editId = undefined;
    role = STUDENT;
    users: UserBase[] = [];
    permissions: CoursePermission[];

    isRoot = false;

    constructor(public dialogRef: MatDialogRef<NewUserModalComponent>,
                private userApi: UserApiService) {
        this.addUser();
        userApi.isAdmin().subscribe(response => {
            if (response.success) {
                this.isRoot = response.payload;
            }
        })
    }

    isSysRole(role: SystemRole) {
        return role === this.role;
    }

    setSysRole(role: SystemRole) {
        this.role = role;
    }

    addUser() {
        if (this.count === 0) {
            this.users = [];
        }
        this.count += 1;
        this.users.push({id: this.count, name: '', email: ''});
        this.editId = this.count;
        this.users = [...this.users];
    }

    editUser(id: number) {
        if (this.isUnfulledRow(id)) {
            this.deleteUser(id);
        }

        if (this.isEditing(id)) {
            this.editId = -1;
        } else {
            this.editId = id;
        }
    }

    isUnfulledRow(id: number) {
        return this.users[id - 1].name === '' && this.users[id - 1].email === '';
    }

    deleteUser(id: number) {
        this.users.splice(id - 1, 1);
        this.count -= 1;

        for (let i = 0; i < this.count; i++) {
            this.users[i].id = i + 1;
        }

        if (this.count === 0) {
            this.users = [this.EMPTY];
        }

        this.users = [...this.users];
    }

    isEditing(id: number) {
        return this.editId === id;
    }

    isNotEmpty() {
        return this.count !== 0;
    }

    onPermissionChange(updated: CoursePermission[]) {
        if (Utils.exists(updated)) {
            this.permissions = [...updated];
        }
    }

    close() {
        this.dialogRef.close('close');
    }

    submit() {
        if (!Utils.exists(this.permissions)) {
            this.permissions = [];
        }
        this.userApi.createUsers(this.role, this.users, this.permissions)
            .subscribe(response => {
                if (response.success) {
                    this.dialogRef.close('submit');
                }
            });
    }

    protected readonly TEACHER = TEACHER;
    protected readonly STUDENT = STUDENT;
    protected readonly ROOT = ROOT;
}
