import {Injectable} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {DEFAULT_LANG, EN_LANG, LOCALE_KEY, SK_LANG} from '../../consts/translate.consts';
import {Utils} from '../../util/utils';

@Injectable({
    providedIn: 'root'
})
export class LocaleTranslateService {

    constructor(private translate: TranslateService) {
        this.refresh();
    }

    getCurrentLang() {
        return this.translate.currentLang;
    }

    private save() {
        const lang = this.translate.currentLang;
        sessionStorage.setItem(LOCALE_KEY, lang);
    }

    refresh() {
        const lang = sessionStorage.getItem(LOCALE_KEY);
        if (Utils.exists(lang)) {
            this.translate.use(lang);
        } else {
            this.translate.use(DEFAULT_LANG);
            this.save();
        }
    }

    swap() {
        if (this.translate.currentLang === SK_LANG) {
            this.translate.use(EN_LANG);
        } else {
            this.translate.use(SK_LANG);
        }
        this.save();
        return this.translate.currentLang;
    }

    isSlovak() {
        return this.translate.currentLang === SK_LANG;
    }
}
