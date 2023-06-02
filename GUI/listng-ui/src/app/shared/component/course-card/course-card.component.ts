import {Component, Input} from '@angular/core';
import {CourseBase} from '../../../core/model/course';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {TranslateModule} from '@ngx-translate/core';
import {NgIf} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmModalComponent} from '../confirm-modal/confirm-modal.component';
import {UserApiService} from '../../../core/api/user-api.service';
import {CoursePermission, UserCourseRole, UserCourseStatus} from '../../../core/model/course-permission';
import {InfoModalComponent} from '../info-modal/info-modal.component';

@Component({
    selector: 'app-course-card',
    templateUrl: './course-card.component.html',
    imports: [
        FontAwesomeModule,
        TranslateModule,
        NgIf,
        CoreModule,
        MatCardModule,
        MatIconModule
    ],
    standalone: true
})
export class CourseCardComponent {

    @Input() course: CourseBase;

    @Input() permission: CoursePermission;

    constructor(private router: Router,
                private userApi: UserApiService,
                public dialog: MatDialog) {
    }

    onClick() {
        if (this.permission.role !== UserCourseRole.NOT_ASSIGNED) {
            this.router.navigate(['/course/' + this.course.id]);

        } else if (this.permission.status === UserCourseStatus.DENIED) {
            const dialogRef = this.dialog.open(InfoModalComponent, {
                data: {message: 'course.label.denied', specify: this.course.name},
            });
        } else if (this.permission.status === UserCourseStatus.PENDING) {
            const dialogRef = this.dialog.open(InfoModalComponent, {
                data: {message: 'course.label.pending', specify: this.course.name},
            });
        } else {
            const dialogRef = this.dialog.open(ConfirmModalComponent, {
                data: {message: 'course.label.request.entry', specify: this.course.name},
            });

            dialogRef.afterClosed().subscribe(result => {
                if (result === 'confirm') {
                    this.userApi.requestCourseEntry(this.course.id)
                        .subscribe();

                    window.location.reload();
                }
            });
        }
    }

    getActionLabel() {
        return 'course.action.' + (this.permission.role !== UserCourseRole.NOT_ASSIGNED ? 'enter' : 'request');
    }

}
