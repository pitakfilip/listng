import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseCardComponent } from './component/course-card/course-card.component';
import {TranslateModule} from '@ngx-translate/core';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {RouterLinkWithHref} from '@angular/router';
import { ListTableComponent } from './component/list-table/list-table.component';
import { TablePagingComponent } from './component/table-paging/table-paging.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { PagingPipe } from './pipe/paging.pipe';
import { RoundUpPipe } from './pipe/round-up.pipe';
import { PagingInfoPipe } from './pipe/paging-info.pipe';
import {FormsModule} from '@angular/forms';



@NgModule({
    declarations: [
        CourseCardComponent,
        ListTableComponent,
        TablePagingComponent,
        PagingPipe,
        RoundUpPipe,
        PagingInfoPipe
    ],
    exports: [
        CourseCardComponent,
        ListTableComponent,
        TablePagingComponent
    ],
    imports: [
        CommonModule,
        TranslateModule,
        FontAwesomeModule,
        RouterLinkWithHref,
        NgbModule,
        FormsModule
    ]
})
export class SharedModule { }
