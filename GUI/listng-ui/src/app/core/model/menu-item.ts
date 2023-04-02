export interface MenuItem {
    id: string;
    name: string | (() => string);
    routerLink?: string;
    children?: MenuItem[];
}
