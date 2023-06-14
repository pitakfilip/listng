import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import {CoreModule} from '../../../../core/core.module';
import {PageResponse, pagingFactory, PagingParams, sortFactory, SortParams} from '../../../../core/model/paging.model';
import {MatDialog} from '@angular/material/dialog';
import {RoomApiService} from '../../../../core/api/room-api.service';
import {MatButtonModule} from '@angular/material/button';
import {ConfirmModalComponent} from '../../../../shared/component/confirm-modal/confirm-modal.component';
import {RoomModalComponent} from './room-modal/room-modal.component';

@Component({
    selector: 'app-rooms',
    standalone: true,
    imports: [CommonModule, MatPaginatorModule, MatIconModule, MatTableModule, CoreModule, MatButtonModule],
    templateUrl: './rooms.component.html'
})
export class RoomsComponent {

    paging: PagingParams;
    sort: SortParams;
    defaultSize = 10;
    pageSizes = [10, 20, 50];
    columns = ['name', 'capacity', 'actions'];
    $page: PageResponse;

    constructor(public dialog: MatDialog,
                private roomApi: RoomApiService) {
        this.init();
        this.loadData();
    }

    init() {
        this.paging = pagingFactory(this.defaultSize);
        this.sort = sortFactory();
        this.sort.field = 'end';
    }

    loadData() {
        this.roomApi.getRoomPage(this.paging).subscribe(response => {
            if (response.success) {
                this.$page = response.payload;
            }
        })
    }

    onPagingChange($event: PageEvent) {
        this.paging.page = $event.pageIndex;
        this.paging.size = $event.pageSize;
        this.loadData();
    }

    onCreate() {
        const dialogRef = this.dialog.open(RoomModalComponent, {
            data: { room: undefined }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'success') {
                this.loadData();
            }
        })
    }

    onEdit(roomId: number) {
        const index = this.$page.data.findIndex(room => room.id === roomId);
        if (index < 0) {
            return;
        }
        const dialogRef = this.dialog.open(RoomModalComponent, {
            data: { room: this.$page.data[index] }
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result === 'success') {
                this.loadData();
            }
        })
    }

    onDelete(roomId: number) {
        const index = this.$page.data.findIndex(room => room.id === roomId);
        if (index < 0) {
            return;
        }

        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: {message: 'other.rooms.modal.delete', specify: this.$page.data[index].name}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'confirm') {
                this.roomApi.deleteRoom(roomId).subscribe(response => {
                    if (response.success) {
                        this.loadData();
                    }
                })
            }
        });
    }

}
