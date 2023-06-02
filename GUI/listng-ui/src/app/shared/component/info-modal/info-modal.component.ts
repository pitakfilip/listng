import {Component, Inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MAT_DIALOG_DATA, MatDialogModule} from '@angular/material/dialog';
import {TranslateModule} from '@ngx-translate/core';
import {CoreModule} from '../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-info-modal',
    standalone: true,
    imports: [CommonModule, TranslateModule, CoreModule, MatDialogModule, MatButtonModule],
    templateUrl: './info-modal.component.html'
})
export class InfoModalComponent {

    constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string }) {
    }
}
