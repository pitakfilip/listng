import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule, HttpHandler} from '@angular/common/http';
import {MissingTranslationHandler, TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {DEFAULT_LANG} from './core/consts/translate.consts';
import {CustomTranslateLoader} from './core/service/translate/custom-translate-loader.service';
import {CustomMissingTranslationHandler} from './core/service/translate/custom-missing-translation.handler';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTooltipModule} from '@angular/material/tooltip';
import {SharedModule} from './shared/shared.module';
import {BackgroundComponent} from './main/components/background/background.component';
import {CoreModule} from './core/core.module';

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
        FontAwesomeModule,
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
        BackgroundComponent

    ],
    providers: [
        {
            provide: TranslateLoader,
            useClass: CustomTranslateLoader
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
