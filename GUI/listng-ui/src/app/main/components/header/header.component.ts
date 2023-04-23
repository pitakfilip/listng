import {Component, OnInit} from '@angular/core';
import {faUserCircle} from '@fortawesome/free-solid-svg-icons';
import {AuthApiService} from '../../../core/api/auth-api.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

    menuCollapsed = true;
    userCollapsed = true;
    faUser = faUserCircle;

    constructor(private authApi: AuthApiService) {
        console.log('debilko');
    }

    // TODO get menu items (ak si student tak iba kurzy, ucitel vidi dalsie (musi sa prepnut najprv do kurzu))
    ngOnInit(): void {
    }

    menu = [
        {name: 'Kurzy', active: true, link: ''},
        {name: 'Ulohy', active: false, link: 'tasks'},
        {name: 'Zostavy', active: false, link: 'tasksets'},
        {name: 'Uzivatelia', active: false, link: 'users'},
    ];

    toggleMenu() {
        this.menuCollapsed = !this.menuCollapsed;
        if (this.menuCollapsed) {
            this.userCollapsed = true;
        }
    }

    toggleUser() {
        this.userCollapsed = !this.userCollapsed;
    }

    logout() {
        this.authApi.logout().subscribe(response => {
            console.log(response);
        })
        // this.authService.logout();
    }
}
