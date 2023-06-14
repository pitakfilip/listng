export interface MenuItem {
    name: string,
    active : boolean,
    link: string
}

export const COURSE_MANAGEMENT = { name: 'courses', active: false, link: 'courses'} as MenuItem

export const TASK_MANAGEMENT = { name: 'tasks', active: false, link: 'tasks'} as MenuItem

export const TASKSET_MANAGEMENT = { name: 'tasksets', active: false, link: 'tasksets'} as MenuItem

export const USER_MANAGEMENT = { name: 'users', active: false, link: 'users'} as MenuItem

export const MOSS_MANAGEMENT = { name: 'moss', active: false, link: 'moss'} as MenuItem

export const LOGS = { name: 'logs', active: false, link: 'logs'} as MenuItem

export const OTHER_MANAGEMENT = { name: 'other', active: false, link: 'other'} as MenuItem

export const SYSTEM_MANAGEMENT = { name: 'system', active: false, link: 'system'} as MenuItem

export const MENU_ITEMS = {
    'USERS': USER_MANAGEMENT,
    'COURSES': COURSE_MANAGEMENT,
    'TASKS': TASK_MANAGEMENT,
    'TASKSETS': TASKSET_MANAGEMENT,
    'MOSS': MOSS_MANAGEMENT,
    'LOGS': LOGS,
    'OTHER': OTHER_MANAGEMENT,
    'CONFIG': SYSTEM_MANAGEMENT
}
