import {Component, Inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {Utils} from '../../../../../core/util/utils';
import {Room} from '../../../../../core/model/room';
import {RoomApiService} from '../../../../../core/api/room-api.service';
import {CoreModule} from '../../../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDividerModule} from '@angular/material/divider';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {ReactiveTypedFormsModule} from '@rxweb/reactive-form-validators';

@Component({
    selector: 'app-room-modal',
    standalone: true,
    imports: [CommonModule, CoreModule, MatButtonModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatDividerModule, MatFormFieldModule, MatIconModule, MatInputModule, ReactiveFormsModule, ReactiveTypedFormsModule],
    templateUrl: './room-modal.component.html'
})
export class RoomModalComponent {

    inner: Room;
    isNewRoom = false;

    constructor(@Inject(MAT_DIALOG_DATA) public data: { room: Room },
                public dialogRef: MatDialogRef<RoomModalComponent>,
                private roomApi: RoomApiService) {
        if (Utils.exists(data.room)) {
            this.inner = {...data.room};
        } else {
            this.inner = {
                id: null,
                name: '',
                capacity: undefined
            } as Room;
            this.isNewRoom = true;
        }
    }

    isNew() {
        return this.isNewRoom;
    }

    close() {
        this.dialogRef.close('close');
    }

    submit() {
        this.roomApi.saveRoom(this.inner).subscribe(response => {
            if (response.success) {
                this.dialogRef.close('success');
            }
        })
    }
}
