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
import { FilterFulltextComponent } from './component/filter/filter-fulltext/filter-fulltext.component';
import {NgIconsModule} from '@ng-icons/core';
import { bootstrapX, bootstrapCheck, bootstrapTrash } from '@ng-icons/bootstrap-icons';
import { FilterRangeComponent } from './component/filter/filter-range/filter-range.component';
import { FilterChoiceComponent } from './component/filter/filter-choice/filter-choice.component';
import { FilterBlockComponent } from './component/filter/filter-block/filter-block.component';
import { FilterTriStateComponent } from './component/filter/filter-tri-state/filter-tri-state.component';
import {MatCheckboxModule} from '@angular/material/checkbox';

@NgModule({
    declarations: [
        CourseCardComponent,
        ListTableComponent,
        TablePagingComponent,
        PagingPipe,
        RoundUpPipe,
        PagingInfoPipe,
        FilterFulltextComponent,
        FilterRangeComponent,
        FilterChoiceComponent,
        FilterBlockComponent,
        FilterTriStateComponent
    ],
    exports: [
        CourseCardComponent,
        ListTableComponent,
        TablePagingComponent,
        FilterFulltextComponent,
        FilterBlockComponent
    ],
    imports: [
        CommonModule,
        TranslateModule,
        FontAwesomeModule,
        RouterLinkWithHref,
        NgbModule,
        FormsModule,
        NgIconsModule.withIcons({bootstrapX, bootstrapCheck, bootstrapTrash}),
        MatCheckboxModule
    ]
})
export class SharedModule { }
