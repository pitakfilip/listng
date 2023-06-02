import {Component, Inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {UserApiService} from '../../../core/api/user-api.service';
import {MatButtonModule} from '@angular/material/button';
import {CoreModule} from '../../../core/core.module';
import {MatIconModule} from '@angular/material/icon';
import {User, SystemRole} from '../../../core/model/user.model';
import {Utils} from '../../../core/util/utils';
import {FormsModule} from '@angular/forms';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {CoursePermission, ROOT, STUDENT, TEACHER} from '../../../core/model/course-permission';
import {MatTableModule} from '@angular/material/table';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {PermissionSelectComponent} from '../../../shared/component/permission-select/permission-select.component';

@Component({
    selector: 'app-edit-user-modal',
    standalone: true,
    imports: [CommonModule, MatDialogModule, MatButtonModule, CoreModule, MatIconModule, FormsModule, MatTooltipModule, MatInputModule, MatSelectModule, MatTableModule, MatButtonToggleModule, MatListModule, MatMenuModule, PermissionSelectComponent],
    templateUrl: './edit-user-modal.component.html'
})
export class EditUserModalComponent {

    $user: User;
    permissions: CoursePermission[];

    constructor(@Inject(MAT_DIALOG_DATA) public data: { userId: number },
                public dialogRef: MatDialogRef<EditUserModalComponent>,
                private userApi: UserApiService) {
        this.fetchUser();
    }

    close() {
        this.dialogRef.close('close');
    }

    submit() {
        this.userApi.updateUser(this.$user, this.permissions).subscribe(response => {
            if (response.success) {
                this.dialogRef.close('submit');
            }
        });
    }

    fetchUser() {
        if (Utils.exists(this.data) && Utils.exists(this.data.userId)) {
            this.userApi.getUser(this.data.userId).subscribe(response => {
                if (response.success && Utils.exists(response.payload)) {
                    this.$user = response.payload;
                }
            })
        }
    }

    isSysRole(role: SystemRole) {
        return role === this.$user.role;
    }

    setSysRole(role: SystemRole) {
        this.$user.role = role;
    }

    onPermissionChange(updated: CoursePermission[]) {
        if (Utils.exists(updated)) {
            this.permissions = [...updated];
        }
    }

    protected readonly TEACHER = TEACHER;
    protected readonly STUDENT = STUDENT;
    protected readonly ROOT = ROOT;
}
