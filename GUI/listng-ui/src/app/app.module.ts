import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BackgroundComponent } from './main/components/background/background.component';
import { LoginComponent } from './main/components/login/login.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule, HttpHandler} from '@angular/common/http';
import {MissingTranslationHandler, TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {DEFAULT_LANG} from './core/consts/translate.consts';
import {CustomTranslateLoader} from './core/service/translate/custom-translate-loader.service';
import {CustomMissingTranslationHandler} from './core/service/translate/custom-missing-translation.handler';

@NgModule({
    declarations: [
        AppComponent,
        BackgroundComponent,
        LoginComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        FontAwesomeModule,
        FormsModule,
        HttpClientModule,
        TranslateModule.forRoot({
            defaultLanguage: DEFAULT_LANG,
            loader: {
                provide: CustomTranslateLoader,
                deps: [HttpClient]
            },
            missingTranslationHandler: {
                provide: MissingTranslationHandler,
                useClass: CustomMissingTranslationHandler
            }
        }),
        ReactiveFormsModule

    ],
    providers: [
        {
            provide: TranslateLoader,
            useClass: CustomTranslateLoader
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
