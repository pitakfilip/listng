import {Component, forwardRef} from '@angular/core';
import {ControlValueAccessor, NG_VALUE_ACCESSOR} from '@angular/forms';
import {Utils} from '../../../../core/util/utils';
import {Filter} from '../../../../core/model/filter/filter';
import {FilterOption} from '../../../../core/model/filter/filter-option';
import {debounceTime, distinctUntilChanged, filter, map, Observable} from 'rxjs';
import {NgbTypeaheadSelectItemEvent} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-filter-choice',
    templateUrl: './filter-choice.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => FilterChoiceComponent),
            multi: true
        }
    ]
})
export class FilterChoiceComponent implements ControlValueAccessor {

    options: FilterOption[];
    filter: Filter;

    formatter = (option: FilterOption) => option.label;

    search = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            filter((term) => term.length >= 2),
            map((term) => this.options.filter((option) =>{
                 return this.filter.value.findIndex( elem => elem.id === option.id) === -1
                }).filter((option) =>
                new RegExp(term, 'mi').test(option.label))
            ),
        );

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
            this.options = [...obj.data];
        }
    }

    onSelect($event: NgbTypeaheadSelectItemEvent, input) {
        $event.preventDefault();
        input.value = '';

        const item: FilterOption = $event.item;
        const index = this.filter.value.findIndex( elem => {
            return elem.id === item.id;
        });

        if (Utils.exists(index) && index > -1) {
            this.filter.value.splice(index, 1);
        } else {
            this.filter.value.push(item);
        }

        this.onChange(this.filter);
    }

    onUnselect(item : FilterOption) {
        const index = this.filter.value.findIndex( elem => {
            return elem.id === item.id;
        });
        this.filter.value.splice(index, 1);
        this.onChange(this.filter);
    }

    clear() {
        this.filter.value = [];
        this.onChange(this.filter);
    }
}
