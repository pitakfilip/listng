import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {faExclamationTriangle} from '@fortawesome/free-solid-svg-icons';
import {OauthService} from '../../../core/service/oauth-service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: []
})
export class LoginComponent {

    loginForm : FormGroup;
    _email = '';
    _password = '';
    emailControl = new FormControl(this._email, {
        validators: [Validators.required, Validators.email],
        updateOn: 'submit'
    });
    passwordControl = new FormControl(this._password, {
        validators: [Validators.required, Validators.minLength(6)],
        updateOn: 'submit'
    });

    failedLogin = false;
    year;
    faError = faExclamationTriangle;

    oauthService : OauthService;

    constructor(private authService : OauthService) {
        this.loginForm = new FormGroup({
            email: this.emailControl,
            password: this.passwordControl
        });

        this.year = new Date().getFullYear();
        this.oauthService = authService;
    }

    submit() {
        console.log(this.loginForm);
        this.failedLogin = !this.oauthService.verifyLogin(this._email, this._password);
    }

    invalidCredentials() {
        return this.failedLogin;
    }

}
