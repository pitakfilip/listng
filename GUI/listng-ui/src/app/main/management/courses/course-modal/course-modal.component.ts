import {Component, Inject, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../../core/core.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {PeriodApiService} from '../../../../core/api/period-api.service';
import {CourseApiService} from '../../../../core/api/course-api.service';
import {Course, courseFactory} from '../../../../core/model/course';
import {Utils} from '../../../../core/util/utils';
import {MultiLangInputComponent} from '../../../../shared/component/multi-lang-input/multi-lang-input.component';
import {Period} from '../../../../core/model/period';
import {MatOptionModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {GroupInputComponent} from '../../../../shared/component/group-input/group-input.component';

@Component({
    selector: 'app-course-modal',
    standalone: true,
    imports: [CommonModule, CoreModule, FormsModule, MatButtonModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatDividerModule, MatFormFieldModule, MatIconModule, MatInputModule, ReactiveFormsModule, MultiLangInputComponent, MatOptionModule, MatSelectModule, GroupInputComponent],
    templateUrl: './course-modal.component.html'
})
export class CourseModalComponent implements OnInit {

    inner: Course;
    isNewCourse = false;
    $periods: Period[];

    constructor(@Inject(MAT_DIALOG_DATA) public data: { course: Course | undefined },
                public dialogRef: MatDialogRef<CourseModalComponent>,
                private periodApi: PeriodApiService,
                private courseApi: CourseApiService) {
    }

    ngOnInit() {
        if (Utils.exists(this.data.course)) {
            this.inner = this.data.course;
        } else {
            this.inner = courseFactory();
            this.isNewCourse = true;
        }

        this.fetchPeriods();
    }

    fetchPeriods() {
        this.periodApi.getPeriods()
            .subscribe(response => {
                if (response.success) {
                    this.$periods = response.payload;
                    this.inner.periodId = this.$periods.find(period => period.active).id;
                }
            });
    }

    isNew() {
        return this.isNewCourse;
    }

    close() {
        this.dialogRef.close('close');
    }

    submit() {
        this.courseApi.saveCourse(this.inner).subscribe(response => {
            if (response.success) {
                this.dialogRef.close('success');
            }
        })
    }
}
