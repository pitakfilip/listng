export interface Paging {
    page: number;
    pageSize: number;
    orders: Order[];
}

export interface Order {
    property: string;
    direction: string;
}
