import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {ErrorInterceptor} from './interceptor/error.interceptor';
import { CustomTranslatePipe } from './pipe/custom-translate.pipe';

@NgModule({
    declarations: [
        CustomTranslatePipe
    ],
    imports: [
        CommonModule
    ],
    exports: [
        CustomTranslatePipe
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorInterceptor,
            multi: true
        }
    ]
})
export class CoreModule {
}
