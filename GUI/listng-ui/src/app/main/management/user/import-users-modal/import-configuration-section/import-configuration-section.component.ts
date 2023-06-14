import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';
import {CollapsePanelComponent} from '../../../../../shared/component/collapse-panel/collapse-panel.component';
import {PeriodsComponent} from '../../../other/periods/periods.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import {MatDividerModule} from '@angular/material/divider';
import {CoursePermission, ROOT, STUDENT, TEACHER} from '../../../../../core/model/course-permission';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {PermissionSelectComponent} from '../../../../../shared/component/permission-select/permission-select.component';
import {Utils} from '../../../../../core/util/utils';
import {Observable} from 'rxjs';
import {MatRadioModule} from '@angular/material/radio';

@Component({
    selector: 'app-import-configuration-section',
    standalone: true,
    imports: [CommonModule, CoreModule, MatButtonModule, MatChipsModule, CollapsePanelComponent, PeriodsComponent, MatButtonToggleModule, MatInputModule, FormsModule, MatDividerModule, MatIconModule, MatTooltipModule, PermissionSelectComponent, MatRadioModule],
    templateUrl: './import-configuration-section.component.html'
})
export class ImportConfigurationSectionComponent {

    @Input() header: { num: number, value: string}[];

    @Input() columns = {
        firstName: undefined,
        lastName: undefined,
        fullName: undefined,
        email: undefined
    }

    @Input() isRoot = false;

    @Output() onReturn = new EventEmitter();

    @Output() onConfirm = new EventEmitter();

    collapsedConfig = true;
    columnType = 'firstName';

    sysRole = STUDENT;
    permissions: CoursePermission[] = [];

    onCollapseToggle(val) {
        this.collapsedConfig = val;
    }

    isSelected(num) {
        return this.columns.firstName === num || this.columns.lastName === num ||
            this.columns.fullName === num || this.columns.email === num;
    }

    select(num) {
        for (let key of Object.keys(this.columns)) {
            if (this.columns[key] === num) {
                this.columns[key] = null;
            }
        }
        this.columns[this.columnType] = num;
    }

    getSelectedPrefix(num) {
        if (this.columns.firstName === num) {
            return '(1) ';
        }
        if (this.columns.lastName === num) {
            return '(2) ';
        }
        if (this.columns.fullName === num) {
            return '(3) ';
        }
        if (this.columns.email === num) {
            return '(4) ';
        }
        return '';
    }

    isSysRole(role) {
        return this.sysRole === role;
    }

    setSysRole(role) {
        this.sysRole = role;
    }

    onPermissionsChange(updated: CoursePermission[]) {
        if (Utils.exists(updated)) {
            this.permissions = [...updated];
        }
    }
    confirm() {
        this.onConfirm.emit({
            columns: this.columns,
            role: this.sysRole,
            permissions: this.permissions
        });
    }

    return() {
        this.onReturn.emit();
    }

    protected readonly ROOT = ROOT;
    protected readonly STUDENT = STUDENT;
    protected readonly TEACHER = TEACHER;
}
