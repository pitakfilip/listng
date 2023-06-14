import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PagingParams} from '../model/paging.model';
import {RestReposnse} from '../model/restReposnse';
import {ROOM_API} from '../consts/api-path.consts';
import {Room} from '../model/room';

@Injectable({
    providedIn: 'root'
})
export class RoomApiService {

    constructor(private http: HttpClient) {
    }

    getRoomPage(paging: PagingParams) {
        return this.http.post<RestReposnse>(ROOM_API + '/page', paging);
    }

    saveRoom(room: Room){
        return this.http.post<RestReposnse>(ROOM_API + '/save', room);
    }

    deleteRoom(roomId: number) {
        return this.http.get<RestReposnse>(ROOM_API + `/${roomId}/delete`)
    }
}
