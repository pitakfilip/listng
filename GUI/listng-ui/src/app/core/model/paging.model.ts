export interface Paging {
    page: number;
    pageSize: number;
    orders: Order[];
}

export interface Order {
    property: string;
    direction: string;
}


export interface PagingParams {
    page: number;
    size: number;
    sort: SortParams[];
}

export interface SortParams {
    field: string;
    direction: SortDirection;
}

export enum SortDirection {
    ASC = 'ASC',
    DESC = 'DESC'
}

export interface PageResponse {
    data: any[];
    page: number;
    totalEntries: number;
    totalPages: number;
}

export function pagingFactory(size: number) {
    return {
        page: 0,
        size: size,
        sort: [] as SortParams[]
    } as PagingParams;
}

export function sortFactory() {
    return {
        field: undefined,
        direction: SortDirection.DESC
    } as SortParams;
}
