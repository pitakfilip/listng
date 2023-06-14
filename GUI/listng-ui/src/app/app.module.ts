import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MissingTranslationHandler, TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {DEFAULT_LANG} from './core/consts/translate.consts';
import {CustomTranslateLoader} from './core/service/translate/custom-translate-loader.service';
import {CustomMissingTranslationHandler} from './core/service/translate/custom-missing-translation.handler';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTooltipModule} from '@angular/material/tooltip';
import {SharedModule} from './shared/shared.module';
import {BackgroundComponent} from './main/components/background/background.component';
import {CoreModule} from './core/core.module';
import {MatDialogModule} from '@angular/material/dialog';
import {MatMenuModule} from '@angular/material/menu';
import {MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {ListPaginator} from './core/custom/list-paginator';
import {SweetAlert2Module} from '@sweetalert2/ngx-sweetalert2';

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        SharedModule,
        CoreModule,
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        FormsModule,
        HttpClientModule,
        TranslateModule.forRoot({
            defaultLanguage: DEFAULT_LANG,
            loader: {
                provide: CustomTranslateLoader
            },
            missingTranslationHandler: {
                provide: MissingTranslationHandler,
                useClass: CustomMissingTranslationHandler
            },
            isolate: false
        }),
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatTooltipModule,
        BackgroundComponent,
        MatDialogModule,
        MatMenuModule,
        BackgroundComponent,
        MatPaginatorModule,
        SweetAlert2Module.forRoot()
    ],
    providers: [
        {
            provide: TranslateLoader,
            useClass: CustomTranslateLoader
        },
        {
            provide: MatPaginatorIntl,
            useClass: ListPaginator
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
