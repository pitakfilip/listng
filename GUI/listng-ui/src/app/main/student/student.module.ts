import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentComponent } from './student.component';
import {NgbCollapseModule, NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import {SharedModule} from '../../shared/shared.module';
import {TranslateModule} from '@ngx-translate/core';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import {ReactiveFormsModule} from '@angular/forms';



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
        ReactiveFormsModule
    ]
})
export class StudentModule { }
