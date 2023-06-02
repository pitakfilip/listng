import {Component, Inject, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {Filter} from '../../../../core/model/filter/filter';
import {Utils} from '../../../../core/util/utils';
import {FilterType} from '../../../../core/model/filter/filter-type';
import {CoreModule} from '../../../../core/core.module';
import {FormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {PermissionSelectComponent} from '../../permission-select/permission-select.component';
import {SharedModule} from '../../../shared.module';
import {FilterTriStateComponent} from '../filter-tri-state/filter-tri-state.component';
import {FilterChoiceComponent} from '../filter-choice/filter-choice.component';
import {FilterRangeComponent} from '../filter-range/filter-range.component';
import {FilterFulltextComponent} from '../filter-fulltext/filter-fulltext.component';

@Component({
  selector: 'app-filter-modal',
  standalone: true,
    imports: [CommonModule, CoreModule, FormsModule, MatButtonModule, MatDialogModule, MatDividerModule, MatIconModule, MatTooltipModule, PermissionSelectComponent, SharedModule, FilterTriStateComponent, FilterChoiceComponent, FilterRangeComponent, FilterFulltextComponent],
  templateUrl: './filter-modal.component.html'
})
export class FilterModalComponent implements OnInit{

    filters: Filter[];

    constructor(@Inject(MAT_DIALOG_DATA) public data: { filterConfigs: Filter[] },
                public dialogRef: MatDialogRef<FilterModalComponent>) {
    }

    ngOnInit(): void {
        this.filters = [...this.data.filterConfigs];
    }

    onChange(value, index: number) {
        this.filters[index] = {...value};
    }

    close() {
        this.dialogRef.close(this.data.filterConfigs);
    }

    submit() {
        this.dialogRef.close(this.filters);
    }

    clear() {
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
