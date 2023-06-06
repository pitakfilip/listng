import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule, Validators,} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {NgIf} from '@angular/common';
import {RxwebValidators} from '@rxweb/reactive-form-validators';
import {MatButtonModule} from '@angular/material/button';
import {NgbModal, NgbToast} from '@ng-bootstrap/ng-bootstrap';
import {AuthErrorModalComponent} from '../auth-error-modal/auth-error-modal.component';
import {STANDARD_MODAL_DIALOG_OPTIONS} from '../../../core/consts/modal.consts';
import {Utils} from '../../../core/util/utils';
import {MatIconModule} from '@angular/material/icon';
import {CoreModule} from '../../../core/core.module';

@Component({
    selector: 'app-password-reset',
    templateUrl: './password-reset.component.html',
    imports: [
        CoreModule,
        ReactiveFormsModule,
        MatInputModule,
        NgIf,
        MatButtonModule,
        NgbToast,
        MatIconModule
    ],
    standalone: true
})
export class PasswordResetComponent implements OnInit {

    hash: any;
    dataFromHash: { username: string, valid: Date};
    isPermaLink: boolean;
    hidePass = true;
    hideConfirm = true;
    year: number;

    resetForm : FormGroup;

    constructor(private route: ActivatedRoute,
                private router: Router,
                private modalService: NgbModal) {
        this.year = new Date().getFullYear();
    }

    ngOnInit() {
        this.isPermaLink = this.route.snapshot.paramMap.has('hash');

        if (!this.isPermaLink) {
            this.resetForm = new FormGroup({
                email: new FormControl('', [Validators.required, Validators.email])
            });
        } else {
            this.resetForm = new FormGroup({
                password: new FormControl('', [Validators.required, Validators.minLength(6)]),
                confirm: new FormControl('', [RxwebValidators.compare({fieldName: 'password'})])
            });

            this.hash = this.route.snapshot.paramMap.get('hash');
            this.decryptHash();
            this.validateHash();
        }
    }

    decryptHash() {

    }

    validateHash() {
        if (Utils.exists(this.dataFromHash) && this.dataFromHash.valid < new Date()) {
            this.openErrorModal('expire.reset');
        }
    }

    openErrorModal(type: string) {
        const modalRef = this.modalService.open(AuthErrorModalComponent, STANDARD_MODAL_DIALOG_OPTIONS);
        modalRef.componentInstance.type = type;
    }

    cancel() {
        this.router.navigate(['login'])
    }

    submit() {
        if (this.isPermaLink) {
            this.resetPasswords();
        } else {
            this.sendEmail();
        }

        this.router.navigate(['login']);
    }

    sendEmail() {

    }

    resetPasswords() {
        const pass1 = this.resetForm.value['password'];
        const pass2 = this.resetForm.value['confirm'];

        if (pass1 == pass2) {
            // TODO poslat na BE zmenu hesla
        }
    }

    enterEmail() {
        return !this.isPermaLink;
    }

    enterNewPass() {
        return this.isPermaLink;
    }

    passwordHasError() {
        return this.resetForm.controls['password'].invalid;
    }

    confirmHasError() {
        return this.resetForm.controls['confirm'].invalid;
    }

    triggerConfirm() {
        this.resetForm.controls['confirm'].setValue(this.resetForm.controls['confirm'].value);
    }
}
