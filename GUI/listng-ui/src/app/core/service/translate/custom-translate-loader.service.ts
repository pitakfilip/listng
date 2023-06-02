import {Injectable} from '@angular/core';
import {TranslateLoader} from '@ngx-translate/core';
import {Observable, of} from 'rxjs';
import {SK_LANG} from '../../consts/translate.consts';
import skLocale from 'src/assets/i18n/sk.json';
import enLocale from 'src/assets/i18n/en.json';

@Injectable({
    providedIn: 'root'
})
export class CustomTranslateLoader implements TranslateLoader {

    constructor() {
    }

    getTranslation(lang: string): Observable<any> {
        return lang === SK_LANG ? of(skLocale) : of(enLocale);
    }
}
