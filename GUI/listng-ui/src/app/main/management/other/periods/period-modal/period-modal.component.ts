import {Component, Inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../../../core/core.module';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import {MatTooltipModule} from '@angular/material/tooltip';
import {PermissionSelectComponent} from '../../../../../shared/component/permission-select/permission-select.component';
import {PeriodApiService} from '../../../../../core/api/period-api.service';
import {Period} from '../../../../../core/model/period';
import {Utils} from '../../../../../core/util/utils';
import {multiLangFactory} from '../../../../../core/model/multilang';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCheckboxModule} from '@angular/material/checkbox';

@Component({
    selector: 'app-period-modal',
    standalone: true,
    imports: [CommonModule, CoreModule, FormsModule, MatButtonModule, MatDialogModule, MatDividerModule, MatIconModule, MatTableModule, MatTooltipModule, PermissionSelectComponent, MatInputModule, MatDatepickerModule, ReactiveFormsModule, MatCheckboxModule],
    templateUrl: './period-modal.component.html'
})
export class PeriodModalComponent {

    inner: Period;
    isNewPeriod = false;

    periodForm: FormGroup;

    constructor(@Inject(MAT_DIALOG_DATA) public data: { period: Period },
                public dialogRef: MatDialogRef<PeriodModalComponent>,
                private periodApi: PeriodApiService) {
        if (Utils.exists(data.period)) {
            this.inner = {...data.period};
        } else {
            this.inner = {
                id: null,
                name: multiLangFactory(),
                start: undefined,
                end: undefined,
                active: false
            } as Period;
            this.isNewPeriod = true;
        }

        this.periodForm = new FormGroup({
            nameSK: new FormControl(this.inner.name.SK),
            nameEN: new FormControl(this.inner.name.EN),
            start: new FormControl(this.inner.start),
            end: new FormControl(this.inner.end),
            active: new FormControl(this.inner.active)
        });
    }

    isNew() {
        return this.isNewPeriod;
    }

    close() {
        this.dialogRef.close('close');
    }

    submit() {
        this.inner.name.SK = this.periodForm.get('nameSK').value;
        this.inner.name.EN = this.periodForm.get('nameEN').value;
        this.inner.start = this.periodForm.get('start').value;
        this.inner.end = this.periodForm.get('end').value;
        this.inner.active = this.periodForm.get('active').value;

        this.periodApi.savePeriod(this.inner).subscribe(response => {
            if (response.success) {
                this.dialogRef.close('success');
            }
        })
    }
}
