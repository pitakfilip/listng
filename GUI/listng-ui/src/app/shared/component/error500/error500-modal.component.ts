import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-error500',
    standalone: true,
    imports: [CommonModule, MatDialogModule, MatIconModule, MatListModule, MatButtonModule, CoreModule],
    templateUrl: './error500-modal.component.html'
})
export class Error500ModalComponent {

    constructor(public dialogRef: MatDialogRef<Error500ModalComponent>) {
    }

    close() {
        this.dialogRef.close('close');
    }
}
