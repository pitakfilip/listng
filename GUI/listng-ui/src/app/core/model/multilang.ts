export interface MultiLang {
    SK: string;
    EN: string;
}

export function multiLangFactory() {
    return {
        SK: '',
        EN: ''
    } as MultiLang
}

export function multiLangOf(sk: string, en: string) {
    return {
        SK: sk,
        EN: en
    } as MultiLang
}

export const EMPTY_MULTILANG = {
    SK: '',
    EN: ''
} as MultiLang
