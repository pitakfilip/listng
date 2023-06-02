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

export const GENERAL_MANAGEMENT = { name: 'general', active: false, link: 'management'} as MenuItem

export const MENU_ITEMS = [COURSE_MANAGEMENT, TASK_MANAGEMENT, TASKSET_MANAGEMENT, USER_MANAGEMENT, MOSS_MANAGEMENT, GENERAL_MANAGEMENT, LOGS]

