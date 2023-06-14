import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../../../core/core.module';
import {FormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import {Utils} from '../../../../../core/util/utils';

@Component({
    selector: 'app-file-select-section',
    standalone: true,
    imports: [CommonModule, CoreModule, FormsModule, MatButtonModule, MatDividerModule, MatIconModule, MatInputModule, MatRadioModule],
    templateUrl: './file-select-section.component.html'
})
export class FileSelectSectionComponent {

    @Output() onContinue = new EventEmitter();

    selectedFiles: File[];
    fileName = '';
    fileType = 'XLSX';
    sheet = 1;
    headerRow: number;
    from: number;
    to: number;

    selectFile(event) {
        if (Utils.exists(event.target) && Utils.exists(event.target.files) && event.target.files.length > 0) {
            this.selectedFiles = event.target.files;
            this.fileName = this.selectedFiles[0].name;
        }
    }

    continue() {
        if (!Utils.exists(this.selectedFiles) || !Utils.exists(this.headerRow) ||
            !Utils.exists(this.from) || !Utils.exists(this.to)) {
            return;
        }

        this.onContinue.emit({
            files: this.selectedFiles,
            type: this.fileType,
            sheet: this.sheet,
            row: this.headerRow,
            from: this.from,
            to: this.to
        });
    }

}
