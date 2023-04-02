import {Component, ViewEncapsulation} from '@angular/core';
import {
    faGraduationCap,
    faLaptopCode,
    faBookOpen,
    faSchool,
    faTerminal,
    faBug,
    faKeyboard,
    faBrain,
    faDna,
    faPencilRuler
} from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-background',
    templateUrl: './background.component.html',
    styleUrls: ['./background.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class BackgroundComponent {

    faIcons = [faGraduationCap, faLaptopCode, faBookOpen,
        faSchool, faTerminal, faBug, faKeyboard, faBrain,
        faDna, faPencilRuler];

    animToggled = true;

    constructor() {
    }

    toggleAnimation() {
        this.animToggled = !this.animToggled;
        const icons = document.getElementsByTagName('fa-icon');

        // @ts-ignore
        for (const icon of icons) {
            let anims = icon.getAnimations();
            for (const anim of anims) {
                if (this.animToggled) {
                    anim.play();
                } else {
                    anim.pause();
                }
            }
        }
    }

}
