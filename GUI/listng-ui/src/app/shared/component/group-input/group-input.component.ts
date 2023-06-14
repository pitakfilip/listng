import {Component, forwardRef} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ControlValueAccessor, FormsModule, NG_VALUE_ACCESSOR} from '@angular/forms';
import {MultiLang} from '../../../core/model/multilang';
import {Utils} from '../../../core/util/utils';
import {Group, groupFactory} from '../../../core/model/course';
import {MultiLangInputComponent} from '../multi-lang-input/multi-lang-input.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {CoreModule} from '../../../core/core.module';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CollapsePanelComponent} from '../collapse-panel/collapse-panel.component';
import {PeriodsComponent} from '../../../main/management/other/periods/periods.component';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-group-input',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => GroupInputComponent),
            multi: true
        }
    ],
    standalone: true,
    imports: [CommonModule, MultiLangInputComponent, FormsModule, MatCheckboxModule, CoreModule, MatIconModule, MatTooltipModule, MatFormFieldModule, MatInputModule, CollapsePanelComponent, PeriodsComponent, MatButtonModule],
    templateUrl: './group-input.component.html'
})
export class GroupInputComponent implements ControlValueAccessor {

    groups: Group[];

    private onChange(_) {
    }

    private onTouch() {
    }

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouch = fn;
    }

    writeValue(obj: Group[]): void {
        if (Utils.exists(obj)) {
            this.groups = [...obj];
        }
    }

    onInnerChange() {
        this.onChange(this.groups);
    }

    toggleGroup(group: Group) {
        const index = this.groups.findIndex(elem => elem === group);
        if (index > -1) {
            if (!this.groups[index].isDefault) {
                for (let other of this.groups) {
                    other.isDefault = false;
                }
            }
            this.groups[index].isDefault = !this.groups[index].isDefault;
        }
        this.onInnerChange();
    }

    newGroup() {
        this.groups.push(groupFactory());
        this.onInnerChange();
    }

    removeGroup(remove: Group) {
        if (this.groups.length === 1) {
            return;
        }
        const index = this.groups.findIndex(elem => elem === remove);
        this.groups.splice(index,1);
        this.onInnerChange();
    }
}
