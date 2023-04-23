import {Component, Input} from '@angular/core';
import {CourseBase} from '../../../core/model/course';
import {faUserCog, faUserGraduate} from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-course-card',
    templateUrl: './course-card.component.html',
    styles: []
})
export class CourseCardComponent {

    @Input() course : CourseBase | undefined;

    faStudent = faUserGraduate;
    faAdmin = faUserCog;

    constructor() {
    }


}
