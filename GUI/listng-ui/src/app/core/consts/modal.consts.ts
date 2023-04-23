import {NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';

export const STANDARD_MODAL_DIALOG_OPTIONS: NgbModalOptions = {
    backdrop: 'static',
    keyboard: true,
    centered: true,
    animation: false,
    windowClass: 'standard-modal-dialog'
};

export const LARGE_MODAL_DIALOG_OPTIONS: NgbModalOptions = {
    ...STANDARD_MODAL_DIALOG_OPTIONS,
    size: 'lg'
};
