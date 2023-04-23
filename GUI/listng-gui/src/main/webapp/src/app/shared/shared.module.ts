import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CustomTranslateLoader} from './service/translate/custom-translate-loader.service';
import {MissingTranslationHandler, TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {DEFAULT_LANG} from './constant/translate.consts';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {CustomMissingTranslationHandler} from './service/translate/custom-missing-translation-handler.service';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { BackgroundComponent } from './components/background/background.component';


@NgModule({
  declarations: [
    BackgroundComponent
  ],
  imports: [
    CommonModule,
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

  ],
  exports: [
    BackgroundComponent
  ],
  providers: [
    {
      provide: TranslateLoader,
      useClass: CustomTranslateLoader
    }
  ]
})
export class SharedModule {
}
