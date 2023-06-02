import {Component, forwardRef} from '@angular/core';
import {ControlValueAccessor, NG_VALUE_ACCESSOR} from '@angular/forms';
import {Filter} from '../../../../core/model/filter/filter';
import {Utils} from '../../../../core/util/utils';

@Component({
    selector: 'app-filter-range',
    templateUrl: './filter-range.component.html',providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => FilterRangeComponent),
            multi: true
        }
    ],
    standalone: true
})
export class FilterRangeComponent implements ControlValueAccessor {

    filter: Filter;

    private onChange(_) {}

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

    innerChange() {
        this.onChange(this.filter);
    }
}
