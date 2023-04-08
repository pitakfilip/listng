import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Filter} from '../../../../core/model/filter';
import {FilterType} from '../../../../core/model/filter-type';

@Component({
  selector: 'app-filter-block',
  templateUrl: './filter-block.component.html'
})
export class FilterBlockComponent implements OnInit{

    _inner: Filter[];

    @Input() filters: Filter[];

    @Output() onFilterChange = new EventEmitter();

    ngOnInit() {
        this._inner = [...this.filters];
    }

    onChange(value, index: number) {
        this._inner[index] = {...value};
    }

    // Odoslat zmeny na spracovanie
    onSubmit() {
        this.filters = {...this._inner}
        this.onFilterChange.emit(this.filters);
    }

    // Zrusit zmeny (ponechat stary filter)
    onCancel() {
        this._inner = [...this.filters];
    }

    // Vymazat filter (reset)
    onClear() {
        for (let i = 0; i < this._inner.length ; i++) {
            this._inner[i] = {
                field: this._inner[i].field,
                label: this._inner[i].label,
                type: this._inner[i].type,
                value: this._inner[i].default,
                default: this._inner[i].default
            } as Filter;
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
