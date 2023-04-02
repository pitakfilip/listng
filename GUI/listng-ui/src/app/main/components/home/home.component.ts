import {Component} from '@angular/core';
import {Utils} from '../../../core/util/utils';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styles: []
})
export class HomeComponent {

    $kurzy = [{id: 1, name: 'Programovanie 4 (Java)', students: 43, isAdmin: false}]

    $kurzyy = [
        {id: 1, short:'prog4', name: 'Programovanie 4 (Java)', students: 43, isAdmin: false},
        {id: 2, short:'prog3', name: 'Programovanie 3 (C++)', students: 69, isAdmin: true},
        {id: 3, short:'prog2', name: 'Programovanie 2 (Python)', students: 22, isAdmin: true},
        {id: 4, short:'prog1', name: 'Programovanie 1 (Python)', students: 99, isAdmin: false},
    ];

    $obdobia = [
        { id: 2, ozn: 'Zim.2022/2023', isCurrent : false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false },
        { id: 1, ozn: 'Let.2022/2023', isCurrent: false }
    ]
    selectedPeriod : any;
    otherPeriods : any;

    constructor() {
        this.setDefaultPeriod();
        this.setOtherPeriods();
    }

    setDefaultPeriod() {
        if (Utils.exists(this.$obdobia)) {
            const current = this.$obdobia.find( element => {
                return element.isCurrent;
            });
            if (Utils.exists(current)){
                this.selectedPeriod = current;
            } else {
                this.selectedPeriod = this.$obdobia[0];
            }
        }
    }

    setOtherPeriods() {
        if (Utils.exists(this.$obdobia)){
            this.otherPeriods = this.$obdobia.filter( element => {
                return element.id !== this.selectedPeriod.id;
            });
        }
    }

    changePeriod (id : number) {
        console.log(id);
        this.selectedPeriod = this.$obdobia.find( element => {
            return element.id === id;
        });
        this.setOtherPeriods();
    }
}
