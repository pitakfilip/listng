import {Component, Inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MAT_DIALOG_DATA, MatDialogModule} from '@angular/material/dialog';
import {TranslateModule} from '@ngx-translate/core';
import {MultiLang} from '../../../core/model/multilang';
import {CoreModule} from '../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-confirm-modal',
    standalone: true,
    imports: [CommonModule, MatInputModule, MatDialogModule, TranslateModule, CoreModule, MatButtonModule],
    templateUrl: './confirm-modal.component.html'
})
export class ConfirmModalComponent {

    constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string, specify: MultiLang|string, simpleList: string[], list: MultiLang[] }) {
        if (typeof data.specify === 'string') {
            data.specify = { EN: data.specify, SK: data.specify } as MultiLang;
        }
    }
}
