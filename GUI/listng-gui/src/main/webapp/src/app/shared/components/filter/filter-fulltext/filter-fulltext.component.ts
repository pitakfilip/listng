import {Component, forwardRef} from '@angular/core';
import {ControlValueAccessor, FormsModule, NG_VALUE_ACCESSOR} from '@angular/forms';
import {Filter} from '../../../model/filter/filter';
import {Utils} from '../../../service/utils.service';
import {TranslateModule} from '@ngx-translate/core';
import {SharedModule} from '../../../shared.module';

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
        SharedModule,
        TranslateModule,
        FormsModule
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
