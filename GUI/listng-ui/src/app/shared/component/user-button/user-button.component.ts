import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {AuthenticationService} from '../../../core/service/authentication.service';

@Component({
    selector: 'app-user-button',
    standalone: true,
    imports: [CommonModule, CoreModule, MatMenuModule, MatButtonModule, MatIconModule, MatDividerModule],
    templateUrl: './user-button.component.html'
})
export class UserButtonComponent {

    constructor(private authService: AuthenticationService) {
    }

    logout() {
        this.authService.handleLogout();
    }

}
