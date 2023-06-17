import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthApiService} from '../../../core/api/auth-api.service';
import {MatInputModule} from '@angular/material/input';
import {NgClass, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {AuthenticationService} from '../../../core/service/authentication.service';
import {CoreModule} from '../../../core/core.module';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    imports: [
        CoreModule,
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
    emailControl = new FormControl('', {
        validators: [Validators.required, Validators.email]
    });
    passwordControl = new FormControl('', {
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
    }

    submit() {
        if (this.loginForm.invalid) {
            return;
        }
        const username = this.loginForm.value['email'];
        const password = this.loginForm.value['password'];
        this.authApi.verifyLogin(username, password)
            .subscribe(response => {
                console.log(response)
                if (response.success){
                    this.authService.handleLogin(response.payload);
                } else {
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
