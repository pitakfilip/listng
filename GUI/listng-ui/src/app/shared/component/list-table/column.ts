export interface Column<T = any> {
    type: ColumnType;
    name: string;
    value?: (row: T) => any;
    tooltipHeader?: string;
    disabled?: (row: T) => boolean;
    hidden?: (row: T) => boolean;
    visible?: (row: T) => boolean;

    checked?: (row: T) => boolean;
    checkboxChange?: (value, checked) => void;
}

export enum ColumnType {

}
