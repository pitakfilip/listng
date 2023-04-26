import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthApiService} from '../../../core/api/auth-api.service';
import {TranslateModule} from '@ngx-translate/core';
import {MatInputModule} from '@angular/material/input';
import {NgClass, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {AuthenticationService} from '../../../core/service/authentication.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    imports: [
        TranslateModule,
        MatInputModule,
        ReactiveFormsModule,
        NgClass,
        NgIf,
        RouterLink,
        MatButtonModule,
        MatIconModule
    ],
    standalone: true
})
export class LoginComponent {

    loginForm : FormGroup;
    emailControl = new FormControl('frantik1@uniba.sk', {
        validators: [Validators.required, Validators.email]
    });
    passwordControl = new FormControl('a', {
        validators: [Validators.required]
    });

    failedLogin = false;
    hidePass = true;
    year;

    constructor(private authApi : AuthApiService,
                private authService: AuthenticationService) {
        this.loginForm = new FormGroup({
            email: this.emailControl,
            password: this.passwordControl
        });

        this.year = new Date().getFullYear();
        const dd = "eyJ1c2VybmFtZSI6ImZwaXRhazExQGdtYWlsLmNvbSIsImV4cGlyZXMiOjE2ODIzMzAxNzE1MDQsInRva2VuIjoiNTcxOTc5MzUzMzQ1ODIzN0U7djk3OGQ5W1k0M1MrKFs2NiQ0KkQ4MEw9djE3NkpneSY1M3YifQ==";
        console.log(atob(dd));

    }

    submit() {
        const username = this.loginForm.value['email'];
        const password = this.loginForm.value['password'];

        this.authApi.verifyLogin(username, password)
            .subscribe(response => {
                if (!response.success){
                    this.invalidCredentials();
                }
            });
    }

    invalidCredentials() {
        this.failedLogin = true;
        this.loginForm.controls['email'].setValue('');
        this.loginForm.controls['email'].markAsTouched();

        this.loginForm.controls['password'].setValue('');
        this.loginForm.controls['password'].markAsTouched();
    }

    onEnter() {
        this.failedLogin = false;
    }
}
