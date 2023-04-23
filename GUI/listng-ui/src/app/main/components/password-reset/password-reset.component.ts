import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {
    FormControl,
    FormGroup,
    ReactiveFormsModule,
    Validators,
} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MatInputModule} from '@angular/material/input';
import {NgIf} from '@angular/common';
import {RxwebValidators} from '@rxweb/reactive-form-validators';
import {MatButtonModule} from '@angular/material/button';
import {CryptoService} from '../../../core/service/crypto.service';
import {NgbModal, NgbToast} from '@ng-bootstrap/ng-bootstrap';
import {AuthErrorModalComponent} from '../auth-error-modal/auth-error-modal.component';
import {STANDARD_MODAL_DIALOG_OPTIONS} from '../../../core/consts/modal.consts';
import {Utils} from '../../../core/util/utils';
import {MatIconModule} from '@angular/material/icon';

@Component({
    selector: 'app-password-reset',
    templateUrl: './password-reset.component.html',
    imports: [
        ReactiveFormsModule,
        TranslateModule,
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
                private cryptoService: CryptoService,
                private modalService: NgbModal) {
        this.year = new Date().getFullYear();

        this.makeHash('frantisek1@uniba.sk');
    }

    ngOnInit() {
        console.log(this.makeHash('jozko@uniba.sk'));
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

    makeHash(username: string) {
        let expire = new Date();
        expire.setMinutes(expire.getMinutes() + 30);

        const obj = {
            email: username,
            valid: expire.toUTCString()
        };

        return this.cryptoService.encrypt(JSON.stringify(obj));
    }

    decryptHash() {
        try {
            const decryptedString = this.cryptoService.decrypt(this.hash);
            const json = JSON.parse(decryptedString);

            this.dataFromHash = {
                username: json.username,
                valid: new Date(json.valid)
            }
        } catch (e) {
            this.openErrorModal('invalid.resetHash');
        }
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
        const hash = this.makeHash(this.resetForm.value['email']);

        // TODO poslat request na BE na send email, ak neexistuje vyhodit chybu
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
