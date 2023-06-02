import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {MatDialog} from '@angular/material/dialog';

@Injectable({
    providedIn: 'root'
})
export class ErrorService {

    constructor(private router: Router,
                private authService: AuthenticationService,
                public dialog: MatDialog) {
    }

    public noLogin() {

    }

    public insufficientPermissions(redirect: boolean ) {
        if (redirect) {

        }
    }

    public errorMessage(message: String) {

    }

    public failedRequest() {
        // return this.dialog.open(Error500ModalComponent);
    }

    public redirect404() {
        // this.router.navigate(['err404']);
    }
}
