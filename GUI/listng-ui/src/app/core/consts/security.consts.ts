import {PREVIEW_API, USER_API} from './api-path.consts';

export const LOGIN_API = '/api/auth/login';
export const LOGOUT_API = '/api/auth/logout';

export const WHITELISTED_PATHS = [
    LOGIN_API,
    LOGOUT_API,
    `${PREVIEW_API}/**` // TODO
]

export const TEACHER_PATHS = [
    `${USER_API}/**`,

]

export const TOKEN_HEADER_NAME = 'X_AUTH_TOKEN';
