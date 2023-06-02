import {Component, ElementRef, forwardRef, ViewChild} from '@angular/core';
import {ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR, ReactiveFormsModule} from '@angular/forms';
import {Utils} from '../../../../core/util/utils';
import {Filter} from '../../../../core/model/filter/filter';
import {NgbTypeahead} from '@ng-bootstrap/ng-bootstrap';
import {CoreModule} from '../../../../core/core.module';
import {MatIconModule} from '@angular/material/icon';
import {AsyncPipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatChipInputEvent, MatChipsModule} from '@angular/material/chips';
import {MatAutocompleteModule, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {map, Observable, startWith} from 'rxjs';

@Component({
    selector: 'app-filter-choice',
    templateUrl: './filter-choice.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => FilterChoiceComponent),
            multi: true
        }
    ],
    imports: [
        CoreModule,
        NgbTypeahead,
        MatIconModule,
        NgIf,
        NgForOf,
        MatInputModule,
        MatChipsModule,
        MatAutocompleteModule,
        ReactiveFormsModule,
        AsyncPipe,
        NgClass
    ],
    standalone: true
})
export class FilterChoiceComponent implements ControlValueAccessor {

    @ViewChild('choiceInput') choiceInput: ElementRef<HTMLInputElement>;

    separatorKeysCodes: number[] = [ENTER, COMMA];
    valueCtrl = new FormControl('');
    filteredValues: Observable<string[]>;
    selectedOptions: string[] = [];
    options: string[];
    filter: Filter;

    constructor() {
        this.filteredValues = this.valueCtrl.valueChanges.pipe(
            startWith(null),
            map((option: string | null) => (option ? this._filter(option) : this.options.slice())),
        );
    }

    private _filter(value: string): string[] {
        const filterValue = value.toLowerCase();
        return this.options.filter(fruit => fruit.toLowerCase().includes(filterValue));
    }

    add(event: MatChipInputEvent): void {
        const value = (event.value || '').trim();
        if (value) {
            this.selectedOptions.push(value);
        }
        event.chipInput!.clear();
        this.valueCtrl.setValue(null);
    }

    remove(val: string): void {
        const index = this.selectedOptions.indexOf(val);

        if (index >= 0) {
            this.selectedOptions.splice(index, 1);
            this.filter.value = [this.selectedOptions];
            this.onChange(this.filter);
        }
    }

    selected(event: MatAutocompleteSelectedEvent): void {
        this.selectedOptions.push(event.option.viewValue);
        this.filter.value = [this.selectedOptions];
        this.onChange(this.filter);
        this.choiceInput.nativeElement.value = '';
        this.valueCtrl.setValue(null);
    }

    private onChange(_) {}

    private onTouch() {}

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouch = fn;
    }

    writeValue(obj: Filter): void {
        if (Utils.exists(obj)) {
            this.filter = {...obj};
            this.options = obj.data.map( filterOption => filterOption.label.SK);
        }
    }
}
