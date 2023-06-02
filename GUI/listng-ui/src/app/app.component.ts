import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {LocaleTranslateService} from './core/service/translate/locale-translate.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    title = 'listng-ui';

    constructor(private localeService: LocaleTranslateService) {
        this.localeService.refresh();
    }
}
