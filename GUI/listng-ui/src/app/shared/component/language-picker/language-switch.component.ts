import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LocaleTranslateService} from '../../../core/service/translate/locale-translate.service';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {TranslateModule} from '@ngx-translate/core';
import {LANGUAGES} from '../../../core/consts/translate.consts';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-language-switch',
    standalone: true,
    imports: [CommonModule, MatInputModule, MatSelectModule, TranslateModule, MatButtonModule],
    templateUrl: './language-switch.component.html'
})
export class LanguageSwitchComponent {

    selected;

    constructor(private localeService: LocaleTranslateService) {
        this.selected = localeService.getCurrentLang();
    }

    onChange() {
        this.selected = this.localeService.swap();
    }

    isSelected(lang: string) {
        return this.selected === lang;
    }

    getSkFloat() {
        return (this.isSelected('sk'))? '0%' : '-10%';
    }

    getEnFloat() {
        return (this.isSelected('en'))? '-10%' : '-20%';
    }
}
