import { Injectable } from '@angular/core';
import {TranslateLoader} from '@ngx-translate/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {I18N_DIR, I18N_FILE_TYPE} from '../../constant/translate.consts';

@Injectable({
  providedIn: 'root'
})
export class CustomTranslateLoader implements TranslateLoader {

  constructor(private http: HttpClient) { }

  getTranslation(lang: string): Observable<any> {
    return this.http.get(`${I18N_DIR}${lang}${I18N_FILE_TYPE}`);
  }
}
