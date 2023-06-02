import {Component, ViewEncapsulation} from '@angular/core';

@Component({
    selector: 'app-background',
    templateUrl: './background.component.html',
    styleUrls: ['./background.component.scss'],
    encapsulation: ViewEncapsulation.ShadowDom,
    imports: [],
    standalone: true
})
export class BackgroundComponent {

    constructor() {
    }


}
