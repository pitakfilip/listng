import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class MenuService {

    // readonly #apiUrl = `${API_URL}/menu`;

    constructor(private http: HttpClient) {
    }

    // TODO prepisat na Observable<MenuItem[]> a volat rest api
    getMenuItems(): string[] {

        return ['Kurzy', 'Studenti', 'Ucet', 'idk nieco este'];

        // return this.http.get<MenuItem[]>(this.#apiUrl);
    }
}
