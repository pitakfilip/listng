import {Component} from '@angular/core';
import {Utils} from '../../../core/util/utils';
import {NgbDropdown, NgbDropdownItem, NgbDropdownMenu, NgbDropdownToggle} from '@ng-bootstrap/ng-bootstrap';
import {CourseCardComponent} from '../../../shared/component/course-card/course-card.component';
import {NgForOf, NgIf} from '@angular/common';
import {Period} from '../../../core/model/period';
import {PeriodApiService} from '../../../core/api/period-api.service';
import {MatSelectModule} from '@angular/material/select';
import {FormsModule} from '@angular/forms';
import {CourseBase} from '../../../core/model/course';
import {CoreModule} from '../../../core/core.module';
import {CourseApiService} from '../../../core/api/course-api.service';
import {UserPermissionApiService} from '../../../core/api/user-permission-api.service';
import {CoursePermission, DEFAULT_STUDENT_PERMISSION} from '../../../core/model/course-permission';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    imports: [
        NgbDropdown,
        NgbDropdownMenu,
        NgbDropdownToggle,
        NgbDropdownItem,
        CourseCardComponent,
        NgForOf,
        NgIf,
        MatSelectModule,
        FormsModule,
        CoreModule
    ],
    standalone: true
})
export class HomeComponent {

    $periods: Period[];
    selectedPeriod : any;

    $courses: CourseBase[];
    $permissions = new Map<number, CoursePermission>();

    constructor(private periodApi: PeriodApiService,
                private courseApi: CourseApiService,
                private permissionApi: UserPermissionApiService) {
        this.fetchPeriods();
    }

    fetchPeriods() {
        this.periodApi.getPeriods()
            .subscribe(response => {
                this.$periods = response.payload;
                this.selectedPeriod = this.$periods.find(period => period.active).id;
                this.fetchCourses();
            });
    }

    fetchCourses() {
        if (!Utils.exists(this.selectedPeriod)) {
            return;
        }
        this.courseApi.getOfPeriod(this.selectedPeriod)
            .subscribe(response => {
                if (response.success) {
                    this.$courses = response.payload;
                    this.fetchPermissions();
                }
            });
    }

    fetchPermissions() {
        this.permissionApi.getPeriodPermissions(this.selectedPeriod)
            .subscribe(response => {
                if (response.success) {
                    for (let course of this.$courses) {
                        this.$permissions.set(course.id, response.payload[course.id]);
                    }
                }
            })
    }

    onPeriodChange() {
        this.fetchCourses();
    }

    getCoursePermission(courseId: number) {
        if (this.$permissions.has(courseId) && Utils.exists(this.$permissions.get(courseId))) {
            return this.$permissions.get(courseId);
        }
        return DEFAULT_STUDENT_PERMISSION;
    }
}
