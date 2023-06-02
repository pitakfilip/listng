import {Component, Inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {UserApiService} from '../../../core/api/user-api.service';
import {CoreModule} from '../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import {MatTooltipModule} from '@angular/material/tooltip';
import {PermissionSelectComponent} from '../../../shared/component/permission-select/permission-select.component';
import {ReactiveFormsModule} from '@angular/forms';
import {Utils} from '../../../core/util/utils';
import {
    ACTIVE,
    ATTENDEE,
    CoursePermission,
    DENIED, EVALUATOR, OWNER, PENDING,
    ROOT,
    STUDENT,
    TEACHER,
    VIEWER
} from '../../../core/model/course-permission';
import {SystemRole, User} from '../../../core/model/user.model';
import {MatChipsModule} from '@angular/material/chips';

@Component({
    selector: 'app-edit-users-modal',
    standalone: true,
    imports: [CommonModule, CoreModule, MatButtonModule, MatDialogModule, MatDividerModule, MatIconModule, MatTableModule, MatTooltipModule, PermissionSelectComponent, ReactiveFormsModule, MatChipsModule],
    templateUrl: './edit-users-modal.component.html'
})
export class EditUsersModalComponent {

    $users : User[];

    role = STUDENT;

    newPermissions: CoursePermission[];
    removePermissions: CoursePermission[];

    constructor(@Inject(MAT_DIALOG_DATA) public data: { userIds: number[] },
                public dialogRef: MatDialogRef<EditUsersModalComponent>,
                private userApi: UserApiService) {

        this.loadUsers();
    }

    loadUsers() {
        this.userApi.getUsers(this.data.userIds).subscribe(response => {
            if (response.success) {
                this.$users = response.payload;
            }
        })
    }

    unselect(userId: number) {
        const index = this.$users.findIndex(user => user.id === userId);
        if (index >= 0) {
            this.$users.splice(index, 1);
        }
    }

    isSysRole(role: SystemRole) {
        return role === this.role;
    }

    setSysRole(role: SystemRole) {
        this.role = role;
    }

    onNewPermissionChange(updated: CoursePermission[]) {
        if (Utils.exists(updated)) {
            this.newPermissions = [...updated];
        }
    }

    onRemovePermissionChange(updated: CoursePermission[]) {
        if (Utils.exists(updated)) {
            this.removePermissions = [...updated];
        }
    }

    close() {
        this.dialogRef.close('close');
    }

    submit() {
        let perms: CoursePermission[] = [...this.newPermissions];

        if (this.role !== STUDENT) {
            for (let removePerm of this.removePermissions) {
                let copy = {...removePerm};
                copy.id = -1;
                perms.push(copy);
            }
        }

        this.userApi.bulkUpdateUsers(this.role, this.$users, perms)
            .subscribe(response => {
                if (response.success) {
                    // this.dialogRef.close('submit');
                }
            });
    }

    protected readonly TEACHER = TEACHER;
    protected readonly STUDENT = STUDENT;
    protected readonly ROOT = ROOT;
    protected readonly DENIED = DENIED;
    protected readonly ATTENDEE = ATTENDEE;
    protected readonly VIEWER = VIEWER;
    protected readonly ACTIVE = ACTIVE;
    protected readonly EVALUATOR = EVALUATOR;
    protected readonly OWNER = OWNER;
    protected readonly PENDING = PENDING;
}
