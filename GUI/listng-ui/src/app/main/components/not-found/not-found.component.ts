import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterLink, RouterOutlet} from '@angular/router';
import {CoreModule} from '../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-not-found',
    standalone: true,
    imports: [CommonModule, RouterOutlet, CoreModule, MatButtonModule, RouterLink],
    templateUrl: './not-found.component.html'
})
export class NotFoundComponent {

}
