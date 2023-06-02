import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MainRoutingModule} from './main-routing.module';
import {HeaderComponent} from './components/header/header.component';
import {MainComponent} from './main.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {NgbCollapseModule, NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';

import {SharedModule} from '../shared/shared.module';
import {CoreModule} from '../core/core.module';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {LanguageSwitchComponent} from '../shared/component/language-picker/language-switch.component';
import {UserButtonComponent} from '../shared/component/user-button/user-button.component';

@NgModule({
    declarations: [
        HeaderComponent,
        MainComponent,
    ],
    exports: [],
    imports: [
        CommonModule,
        SharedModule,
        CoreModule,
        MainRoutingModule,
        FontAwesomeModule,
        NgbCollapseModule,
        NgbDropdownModule,
        MatToolbarModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatIconModule,
        MatSelectModule,
        LanguageSwitchComponent,
        UserButtonComponent
    ]
})
export class MainModule {
}

