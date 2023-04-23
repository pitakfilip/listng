export class Utils {

    static exists(value: any): boolean {
        return typeof value !== 'undefined' && value !== null && value !== undefined;
    }
}
