import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { HeaderComponent } from './components/header/header.component';
import { MainComponent } from './main.component';
import {TranslateModule} from '@ngx-translate/core';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {NgbCollapseModule, NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';

import {SharedModule} from '../shared/shared.module';
import {CoreModule} from '../core/core.module';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    HeaderComponent,
    MainComponent,
    HomeComponent
  ],
  exports: [],
    imports: [
        CommonModule,
        SharedModule,
        CoreModule,
        MainRoutingModule,
        TranslateModule,
        FontAwesomeModule,
        NgbCollapseModule,
        NgbDropdownModule
    ]
})
export class MainModule { }

