import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatTabsModule} from '@angular/material/tabs';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {SharedModule} from '../../shared/shared.module';
import {MatTableModule} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgIconComponent} from '@ng-icons/core';
import {MatIconModule} from '@angular/material/icon';
import {CoreModule} from '../../core/core.module';

@NgModule({
  declarations: [
  ],
    imports: [
        CommonModule,
        CoreModule,
        MatTabsModule,
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
