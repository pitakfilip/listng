import {Component} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalComponent} from '../modal/modal.component';
import {MatButtonModule} from '@angular/material/button';
import {Router} from '@angular/router';
import {CoreModule} from '../../../core/core.module';

@Component({
    selector: 'app-auth-error-modal',
    templateUrl: './auth-error-modal.component.html',
    imports: [
        ModalComponent,
        MatButtonModule,
        CoreModule
    ],
    standalone: true
})
export class AuthErrorModalComponent {

    type: string;

    constructor(private activeModal: NgbActiveModal,
                private router: Router) {
    }

    closeModal() {
        this.activeModal.close('CLOSE');
        this.router.navigate(['login']);
    }

}
