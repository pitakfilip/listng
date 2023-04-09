import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Filter} from '../../../../core/model/filter/filter';
import {FilterType} from '../../../../core/model/filter/filter-type';
import {Utils} from '../../../../core/util/utils';

@Component({
  selector: 'app-filter-block',
  templateUrl: './filter-block.component.html'
})
export class FilterBlockComponent {

    @Input() filters: Filter[];

    @Output() onFilterChange = new EventEmitter();

    onChange(value, index: number) {
        this.filters[index] = {...value};
    }

    // Odoslat zmeny na spracovanie
    onSubmit() {
        this.onFilterChange.emit(this.filters);
    }

    // Vymazat filter (reset)
    onClear() {
        for (let i = 0; i < this.filters.length ; i++) {
            this.filters[i] = {
                ...this.filters[i],
            } as Filter;

            if (Utils.exists(this.filters[i].default) && this.filters[i].default instanceof Array) {
                this.filters[i].value = [...this.filters[i].default];
            } else {
                this.filters[i].value = this.filters[i].default
            }
        }
    }

    isFullText (type : FilterType) {
        return type === FilterType.FULL_TEXT;
    }

    isRange (type : FilterType) {
        return type === FilterType.RANGE;
    }

    isBoolean (type : FilterType) {
        return type === FilterType.BOOLEAN;
    }

    isChoice (type : FilterType) {
        return type === FilterType.CHOICE;
    }

    trackByFn(index, item) {
        return index;
    }
}
