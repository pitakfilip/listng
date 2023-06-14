import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {CollapsePanelComponent} from '../../../shared/component/collapse-panel/collapse-panel.component';
import {PeriodsComponent} from './periods/periods.component';
import {RoomsComponent} from './rooms/rooms.component';

@Component({
    selector: 'app-other',
    standalone: true,
    imports: [CommonModule, CoreModule, CollapsePanelComponent, PeriodsComponent, RoomsComponent],
    templateUrl: './other.component.html'
})
export class OtherComponent {

    collapse = {
        periods: false,
        rooms: false
    }

    onTogglePeriods(value) {
        this.collapse.periods = value;
    }

    onToggleRooms(value) {
        this.collapse.rooms = value;
    }
}
