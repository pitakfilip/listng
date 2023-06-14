import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CourseComponent} from './course/course.component';
import {HomeComponent} from './components/home/home.component';
import {UserComponent} from './management/user/user.component';
import {CoursesComponent} from './management/courses/courses.component';
import {TasksComponent} from './management/tasks/tasks.component';
import {TasksetsComponent} from './management/tasksets/tasksets.component';
import {OtherComponent} from './management/other/other.component';
import {MossComponent} from './management/moss/moss.component';
import {LogsComponent} from './management/logs/logs.component';
import {SystemComponent} from './management/system/system.component';

const routes: Routes = [
    {
      path: '',
      component: HomeComponent
    },
    {
        path: 'course/:id',
        component: CourseComponent,
        loadChildren: () => import('./course/course.module').then(m => m.CourseModule)
    },
    {
        path: 'users',
        component: UserComponent
    },
    {
        path: 'courses',
        component: CoursesComponent
    },
    {
        path: 'tasks',
        component: TasksComponent
    },
    {
        path: 'tasksets',
        component: TasksetsComponent
    },
    {
        path: 'other',
        component: OtherComponent
    },
    {
        path: 'moss',
        component: MossComponent
    },
    {
        path: 'logs',
        component: LogsComponent
    },
    {
        path: 'system',
        component: SystemComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
