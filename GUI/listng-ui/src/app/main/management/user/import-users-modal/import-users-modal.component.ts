import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../../../../core/core.module';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import {MatTooltipModule} from '@angular/material/tooltip';
import {PermissionSelectComponent} from '../../../../shared/component/permission-select/permission-select.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UserApiService} from '../../../../core/api/user-api.service';
import {MatRadioModule} from '@angular/material/radio';
import {MatInputModule} from '@angular/material/input';
import {FileSelectSectionComponent} from './file-select-section/file-select-section.component';
import {RestReposnse} from '../../../../core/model/restReposnse';
import {
    ImportConfigurationSectionComponent
} from './import-configuration-section/import-configuration-section.component';

@Component({
    selector: 'app-import-users-modal',
    standalone: true,
    imports: [CommonModule,
        CoreModule,
        MatButtonModule,
        MatDialogModule,
        MatDividerModule,
        MatIconModule,
        MatTableModule,
        MatTooltipModule,
        PermissionSelectComponent,
        ReactiveFormsModule,
        FormsModule,
        MatRadioModule, MatInputModule, FileSelectSectionComponent, ImportConfigurationSectionComponent],
    templateUrl: './import-users-modal.component.html'
})
export class ImportUsersModalComponent {

    currentSection = 'init';

    columns = {
        firstName: null,
        lastName: null,
        fullName: null,
        email: null
    }
    fileConfig: {
        files: File[],
        type: string,
        sheet: number,
        row: number,
        from: number,
        to: number
    }

    headerMap: any;
    currentUserIsRoot = false;

    constructor(public dialogRef: MatDialogRef<ImportUsersModalComponent>,
                private userApi: UserApiService) {
        userApi.isAdmin().subscribe(response => {
            if (response.success) {
                this.currentUserIsRoot = response.payload;
            }
        })
    }

    showSection(section) {
        return this.currentSection === section;
    }

    close() {
        this.dialogRef.close('close');
    }

    handleError(response : RestReposnse) {
        console.log(response);
    }

    onFileSelectContinue(config) {
        this.userApi.getImportHeader(config.files, config.type, config.sheet, config.row)
            .subscribe(response => {
                if (response.success) {
                    this.fileConfig = config;
                    this.headerMap = [];

                    for (let key of Object.keys(response.payload)) {
                        this.headerMap.push({ num: key, value: response.payload[key]});
                        if (response.payload[key] === 'Meno') {
                            this.columns.firstName = key;
                        }
                        if (response.payload[key] === 'Priezvisko') {
                            this.columns.lastName = key;
                        }
                        if (response.payload[key] === 'E-mail pridelený') {
                            this.columns.email = key;
                        }
                    }

                    this.currentSection = 'config';
                } else {
                    this.handleError(response);
                }

            });
    }

    onReturn(event) {
        this.currentSection = 'init';
    }

    submit(event) {
        const config = {
            sheet: this.fileConfig.sheet,
            rowFrom: this.fileConfig.from,
            rowTo: this.fileConfig.to,
            name: event.columns.firstName,
            surname: event.columns.lastName,
            fullName: event.columns.fullName,
            email: event.columns.email,
            role: event.role,
            permissions: event.permissions
        }
        this.userApi.importUsers(this.fileConfig.files[0], this.fileConfig.type, config).subscribe(response => {
            this.dialogRef.close('submit');
        })
    }

}
