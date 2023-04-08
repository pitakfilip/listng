import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentComponent } from './student.component';
import {NgbCollapseModule, NgbModule, NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import {SharedModule} from '../../shared/shared.module';
import {TranslateModule} from '@ngx-translate/core';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [
    StudentComponent,
    StudentDetailComponent
  ],
    imports: [
        CommonModule,
        NgbPaginationModule,
        SharedModule,
        TranslateModule,
        NgbCollapseModule,
        ReactiveFormsModule,
        FormsModule,
        NgbModule
    ]
})
export class StudentModule { }
