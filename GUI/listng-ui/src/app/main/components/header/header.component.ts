import {Component} from '@angular/core';
import {MENU_ITEMS, MenuItem} from '../../../core/consts/menu.consts';
import {UserApiService} from '../../../core/api/user-api.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {

    menuItems: MenuItem[] = [];

    constructor(private userApi: UserApiService) {
        userApi.getHeaderMenuItems().subscribe(response => {
            if (response.success) {
                for (let permitted of response.payload) {
                    this.menuItems.push(MENU_ITEMS[permitted]);
                }
            }
        });
    }


}
