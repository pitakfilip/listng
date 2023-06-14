import {Component, ContentChild, EventEmitter, Input, Output, TemplateRef} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../core/core.module';
import {MatIconModule} from '@angular/material/icon';
import {NgbCollapse} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-collapse-panel',
    standalone: true,
    imports: [CommonModule, CoreModule, MatIconModule, NgbCollapse],
    templateUrl: './collapse-panel.component.html'
})
export class CollapsePanelComponent {

    @Input() title: string;

    @Input() collapsed = false;

    @Output() toggle = new EventEmitter();

    @ContentChild(TemplateRef) template: TemplateRef<any>;

    get expanded() {
        return !this.collapsed;
    }

    toggleCollapse() {
        this.collapsed = !this.collapsed;
        this.toggle.emit(this.collapsed);
    }
}
