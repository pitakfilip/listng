import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {RouterLinkWithHref} from '@angular/router';
import {TablePagingComponent} from './component/table-paging/table-paging.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {PagingPipe} from './pipe/paging.pipe';
import {RoundUpPipe} from './pipe/round-up.pipe';
import {PagingInfoPipe} from './pipe/paging-info.pipe';
import {FormsModule} from '@angular/forms';
import {NgIconsModule} from '@ng-icons/core';
import {
    bootstrapCheck,
    bootstrapCheckCircleFill,
    bootstrapTrash,
    bootstrapX,
    bootstrapXCircleFill,
    bootstrapXLg
} from '@ng-icons/bootstrap-icons';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {CoreModule} from '../core/core.module';

@NgModule({
    declarations: [
        TablePagingComponent,
        PagingPipe,
        RoundUpPipe,
        PagingInfoPipe,
    ],
    exports: [
        TablePagingComponent,
    ],
    imports: [
        CommonModule,
        TranslateModule,
        RouterLinkWithHref,
        NgbModule,
        FormsModule,
        NgIconsModule.withIcons({
            bootstrapX, bootstrapXLg, bootstrapCheck, bootstrapTrash,
            bootstrapXCircleFill, bootstrapCheckCircleFill
        }),
        MatCheckboxModule,
        CoreModule
    ]
})
export class SharedModule { }
