import {Component} from '@angular/core';
import {MENU_ITEMS} from '../../../core/consts/menu.consts';
import {AuthenticationService} from '../../../core/service/authentication.service';
import {UserApiService} from '../../../core/api/user-api.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {

    showMenu = false;
    menuItems = MENU_ITEMS;

    constructor(private userApi: UserApiService) {
        userApi.isAdmin().subscribe(response => {
            this.showMenu = (response.success && response.payload === true);
        });
    }
}
