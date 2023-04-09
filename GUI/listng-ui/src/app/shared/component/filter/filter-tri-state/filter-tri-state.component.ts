import {Component, forwardRef} from '@angular/core';
import {ControlValueAccessor, NG_VALUE_ACCESSOR} from '@angular/forms';
import {Filter} from '../../../../core/model/filter/filter';
import {Utils} from '../../../../core/util/utils';

@Component({
    selector: 'app-filter-boolean',
    templateUrl: './filter-tri-state.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => FilterTriStateComponent),
            multi: true
        }
    ]
})
export class FilterTriStateComponent implements ControlValueAccessor {

    filter: Filter;

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

    writeValue(obj: any): void {
        if (Utils.exists(obj)) {
            this.filter = {...obj};
        }
    }

    toggleCheckbox() {
        if (!Utils.exists(this.filter.value)) {
            this.filter.value = true;
        } else {
            if (this.filter.value) {
                this.filter.value = false;
            } else {
                this.filter.value = undefined;
            }
        }
        this.onChange(this.filter);
    }

    isIndeterminate() {
        return Utils.exists(this.filter.value) && !this.filter.value;
    }

    isChecked() {
        return Utils.exists(this.filter.value) || this.filter.value;
    }
}
