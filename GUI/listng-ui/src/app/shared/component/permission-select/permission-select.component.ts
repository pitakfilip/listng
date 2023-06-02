import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatOptionModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {PeriodApiService} from '../../../core/api/period-api.service';
import {CourseApiService} from '../../../core/api/course-api.service';
import {UserPermissionApiService} from '../../../core/api/user-permission-api.service';
import {Period} from '../../../core/model/period';
import {Utils} from '../../../core/util/utils';
import {CourseBase} from '../../../core/model/course';
import {
    ACTIVE,
    ATTENDEE,
    CoursePermission,
    DENIED,
    EVALUATOR,
    OWNER,
    PENDING,
    STUDENT,
    studentPermissionFactory,
    teacherPermissionFactory,
    UserCourseRole,
    UserCourseStatus,
    VIEWER
} from '../../../core/model/course-permission';
import {FormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatTableModule} from '@angular/material/table';
import {SystemRole} from '../../../core/model/user.model';
import {MatChipsModule} from '@angular/material/chips';

@Component({
    selector: 'app-permission-select',
    standalone: true,
    imports: [CommonModule, CoreModule, MatFormFieldModule, MatOptionModule, MatSelectModule, FormsModule, MatIconModule, MatMenuModule, MatTooltipModule, MatButtonToggleModule, MatTableModule, MatChipsModule],
    templateUrl: './permission-select.component.html'
})
export class PermissionSelectComponent implements OnChanges, OnInit {

    @Input() role: SystemRole;

    @Input() userId: number | undefined;

    @Input() customTitle: string | undefined;

    @Input() disabledInteraction: boolean | undefined

    @Output() onChange = new EventEmitter<any>();

    loaded = false;
    title = 'permission.period';

    selectedPeriod: number;
    prevPeriod: number;
    $periods: Period[];
    $courses: CourseBase[]; // Vsetky kurzy vo vybranom obdobi
    unassignedCourses: CourseBase[]; // Kurzy v ktorych nemame vytvoreny permission
    assignedCourses: CourseBase[]; // Kurzy v ktorych nemame vytvoreny permission

    // Pre ukladanie permissions pri prepinani typov uzivatelov
    studPermissions: CoursePermission[] = [];
    adminPermissions: CoursePermission[] = [];
    // Aktualne spravovane prava
    permissions: CoursePermission[] = [];
    isStudent: boolean;
    enabledInteraction = true;

    constructor(private periodApi: PeriodApiService,
                private courseApi: CourseApiService,
                private permissionApi: UserPermissionApiService) {
        this.fetchPeriods();
    }

    ngOnInit(): void {
        if (Utils.exists(this.customTitle)) {
            this.title = this.customTitle;
        }
        if (Utils.exists(this.disabledInteraction)) {
            this.enabledInteraction = !this.disabledInteraction;
        }
    }

    canInteract() {
        return this.enabledInteraction;
    }

    getPeriodLabel(periodId: number) {
        const index = this.$periods.findIndex(period => period.id === periodId);
        if (index >= 0) {
            return this.$periods[index].name;
        }
        return undefined;
    }

    // Zmeny inputov od parent (iba role nas zaujima)
    ngOnChanges(changes: SimpleChanges): void {
        if (!this.loaded) {
            this.isStudent = changes['role'].currentValue === STUDENT;
            return;
        }
        if (changes['role'].previousValue === STUDENT || changes['role'].currentValue === STUDENT) {
            if (this.isStudent) {
                this.studPermissions = [...this.permissions];
                this.permissions = [...this.adminPermissions];
                this.assignedCourses = [...this.$courses];
                this.unassignedCourses = [];
            } else {
                this.adminPermissions = [...this.permissions];
                this.permissions = [...this.studPermissions];
                this.setAssigned();
            }
            this.isStudent = !this.isStudent;
            this.emitChange();
        }
    }

    emitChange() {
        this.onChange.emit(this.permissions);
    }

    isStudentFunc() {
        return this.isStudent;
    }

    fetchPeriods() {
        this.periodApi.getPeriods()
            .subscribe(response => {
                if (response.success) {
                    this.$periods = response.payload;
                    this.selectedPeriod = this.$periods.find(period => period.active).id;
                    this.prevPeriod = this.selectedPeriod;
                    this.fetchCourses();
                }
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
                    if (Utils.exists(this.userId) && !this.loaded) {
                        this.fetchPermissions();
                    } else {
                        this.setAssigned();
                    }

                    if (this.isStudent) {
                        this.setTeacherPermissions();
                    }
                    this.loaded = true;
                }
            });
    }

    fetchPermissions() {
        this.permissionApi.getUserPermissions(this.userId)
            .subscribe(response => {
                if (response.success) {

                    for (const permission of response.payload) {
                        const index = this.permissions.findIndex(perm => perm.courseId === permission.courseId);
                        if (index < 0) {
                            this.permissions.push(permission);
                        }
                    }
                    this.emitChange();
                    this.setAssigned();
                }
            });
    }

    onPeriodChange() {
        this.fetchCourses();
    }

    setAssigned() {
        this.unassignedCourses = [];
        this.assignedCourses = [];
        for (const course of this.$courses) {
            const index = this.permissions.findIndex(perm => perm.courseId === course.id);
            if (index < 0) {
                this.unassignedCourses.push(course);
            } else {
                this.assignedCourses.push(course);
            }
        }
    }

    setTeacherPermissions() {
        for (const course of this.$courses) {
            this.adminPermissions.push(teacherPermissionFactory(course.id));
        }
    }

    addNewPermission(courseId: number) {
        const index = this.unassignedCourses.findIndex(course => course.id === courseId);
        if (index >= 0) {
            this.assignedCourses.push({...this.unassignedCourses[index]});
            this.unassignedCourses.splice(index, 1);

            if (this.isStudent) {
                this.permissions.push(studentPermissionFactory(courseId));
            }

            this.assignedCourses = [...this.assignedCourses];
            this.emitChange();
        }
    }

    removePermission(courseId: number) {
        const index = this.assignedCourses.findIndex(course => course.id === courseId);
        if (index >= 0) {
            this.unassignedCourses.push({...this.assignedCourses[index]});
            this.assignedCourses.splice(index, 1);

            const permIndex = this.permissions.findIndex(perm => perm.courseId === courseId);
            this.permissions.splice(permIndex, 1);

            this.assignedCourses = [...this.assignedCourses];
            this.emitChange();
        }
    }

    getPermissionIndex(courseId) {
        return this.permissions.findIndex(perm => perm.courseId === courseId);
    }

    isState(courseId: number, status: UserCourseStatus) {
        const index = this.getPermissionIndex(courseId);
        if (index < 0) {
            return false;
        }
        return this.permissions[index].status === status;
    }

    setState(courseId: number, status: UserCourseStatus) {
        const index = this.getPermissionIndex(courseId);
        this.permissions[index].status = status;
        this.emitChange();
    }

    isRole(courseId: number, role: UserCourseRole) {
        const index = this.getPermissionIndex(courseId);
        if (index < 0) {
            return false;
        }
        return this.permissions[index].role === role;
    }

    setRole(courseId: number, role: UserCourseRole) {
        const index = this.getPermissionIndex(courseId);
        this.permissions[index].role = role;
        this.emitChange();
    }

    protected readonly ACTIVE = ACTIVE;
    protected readonly VIEWER = VIEWER;
    protected readonly DENIED = DENIED;
    protected readonly ATTENDEE = ATTENDEE;
    protected readonly PENDING = PENDING;
    protected readonly OWNER = OWNER;
    protected readonly EVALUATOR = EVALUATOR;

}
