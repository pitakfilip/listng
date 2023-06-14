import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {MatDialog} from '@angular/material/dialog';
import Swal from 'sweetalert2';
import {TranslateService} from '@ngx-translate/core';

@Injectable({
    providedIn: 'root'
})
export class ErrorService {

    constructor(private router: Router,
                private authService: AuthenticationService,
                public dialog: MatDialog,
                private translate: TranslateService) {
    }

    public noLogin() {
        Swal.fire({
            icon: 'error',
            title: this.translate.instant('error.login.expired.title'),
            confirmButtonText: this.translate.instant('label.close'),
            confirmButtonColor: '#aed581'
        }).then(() => {
            this.authService.handleLogout();
        })
    }

    public insufficientPermissions(redirect: boolean ) {
        Swal.fire({
            icon: 'error',
            title: this.translate.instant('error.login.access'),
            confirmButtonText: this.translate.instant('label.close'),
            confirmButtonColor: '#aed581'
        }).then(() => {
            if (redirect) {
                this.authService.redirectHome();
            }
        })
    }

    public errorMessage(message: string) {
        Swal.fire({
            icon: 'error',
            title: this.translate.instant('error.500.title'),
            text: this.translate.instant(message),
            confirmButtonText: this.translate.instant('label.close'),
            confirmButtonColor: '#aed581'
        })
    }

    public failedRequest() {
        Swal.fire({
            icon: 'error',
            title: this.translate.instant('error.500.title'),
            text: this.translate.instant('error.500.info'),
            confirmButtonText: this.translate.instant('label.close'),
            confirmButtonColor: '#aed581'
        })
    }
}
