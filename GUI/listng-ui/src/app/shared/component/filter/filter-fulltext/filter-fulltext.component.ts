import {Component, ElementRef, forwardRef, ViewChild} from '@angular/core';
import {ControlValueAccessor, FormsModule, NG_VALUE_ACCESSOR} from '@angular/forms';
import {Utils} from '../../../../core/util/utils';
import {Filter} from '../../../../core/model/filter/filter';
import {CoreModule} from '../../../../core/core.module';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-filter-fulltext',
    templateUrl: './filter-fulltext.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => FilterFulltextComponent),
            multi: true
        }
    ],
    imports: [
        CoreModule,
        FormsModule,
        NgIf
    ],
    standalone: true
})
export class FilterFulltextComponent implements ControlValueAccessor {

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
