import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user.component';
import {MatTabsModule} from '@angular/material/tabs';
import {TranslateModule} from '@ngx-translate/core';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {SharedModule} from '../../shared/shared.module';
import {MatTableModule} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { UserDetailComponent } from './user-detail/user-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgIconComponent} from '@ng-icons/core';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
  ],
    imports: [
        CommonModule,
        MatTabsModule,
        TranslateModule,
        MatButtonToggleModule,
        SharedModule,
        MatTableModule,
        MatCheckboxModule,
        ReactiveFormsModule,
        FormsModule,
        NgIconComponent,
        MatIconModule
    ]
})
export class UserModule { }
