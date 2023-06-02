import {ChangeDetectorRef, Pipe} from '@angular/core';
import {TranslatePipe, TranslateService} from '@ngx-translate/core';
import {MultiLang} from '../model/multilang';
import {LocaleTranslateService} from '../service/translate/locale-translate.service';

@Pipe({
    name: 'translation',
    pure: false
})
export class CustomTranslatePipe extends TranslatePipe {

    constructor(
        private translateService: TranslateService,
        private changeDetectionRef: ChangeDetectorRef,
        private localeService: LocaleTranslateService
    ) {
        super(translateService, changeDetectionRef);
    }

    override transform(value: MultiLang | string, ...args: any[]): any {
        let jsonValue;
        let objValue = '';
        if (typeof value === 'string') {
            jsonValue = super.transform(value);
        } else {
            // spustime mechanizmus ngx-translate, aby sme vyuzili synchronizovany update prekladov aj pre objekty
            jsonValue = super.transform('dummy.object');
            objValue = this.localeService.isSlovak() ? value.SK : value.EN;
        }

        return typeof value === 'string' ? jsonValue : objValue;
    }

}
