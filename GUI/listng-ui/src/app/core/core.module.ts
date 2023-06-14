import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {ErrorInterceptor} from './interceptor/error.interceptor';
import { CustomTranslatePipe } from './pipe/custom-translate.pipe';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';

@NgModule({
    declarations: [
        CustomTranslatePipe
    ],
    imports: [
        CommonModule,
        MatDatepickerModule,
        MatNativeDateModule
    ],
    exports: [
        CustomTranslatePipe
    ],
    providers: [
        MatDatepickerModule,
        { provide: MAT_DATE_LOCALE, useValue: 'en-GB' },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorInterceptor,
            multi: true
        }
    ]
})
export class CoreModule {
}
