import {Component, Input} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-modal',
    templateUrl: './modal.component.html',
    imports: [
        MatIconModule,
        MatButtonModule
    ],
    standalone: true
})
export class ModalComponent {

    @Input() title: string | undefined;

    constructor(private activeModal: NgbActiveModal) { }

    closeModal() {
        this.activeModal.dismiss('CLOSE');
    }
}
