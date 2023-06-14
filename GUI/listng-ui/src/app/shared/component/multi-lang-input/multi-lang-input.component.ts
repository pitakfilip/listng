import {Component, forwardRef, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ControlValueAccessor, FormsModule, NG_VALUE_ACCESSOR} from '@angular/forms';
import {Utils} from '../../../core/util/utils';
import {MultiLang} from '../../../core/model/multilang';
import {MatInputModule} from '@angular/material/input';
import {CoreModule} from '../../../core/core.module';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatButtonModule} from '@angular/material/button';

@Component({
    selector: 'app-multi-lang-input',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => MultiLangInputComponent),
            multi: true
        }
    ],
    standalone: true,
    imports: [CommonModule, MatInputModule, CoreModule, FormsModule, MatButtonToggleModule, MatButtonModule],
    templateUrl: './multi-lang-input.component.html'
})
export class MultiLangInputComponent implements ControlValueAccessor {

    @Input() title: string;

    multiLang: MultiLang;

    text: string;

    skLang = true;

    isSlovak() {
        return this.skLang;
    }

    private onChange(_) {
    }

    private onTouch() {
    }

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouch = fn;
    }

    writeValue(obj: MultiLang): void {
        console.log(obj);
        if (Utils.exists(obj)) {
            this.multiLang = {...obj};
            this.text = this.multiLang[this.skLang ? 'SK' : 'EN'];
        }
    }

    onTextChange() {
        this.multiLang[this.skLang ? 'SK' : 'EN'] = this.text;
        this.onChange(this.multiLang);
    }

    toggleLang() {
        this.skLang = !this.skLang;
        this.text = this.multiLang[this.skLang ? 'SK' : 'EN'];
    }
}
