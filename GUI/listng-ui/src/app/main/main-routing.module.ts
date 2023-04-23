import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CourseComponent} from './course/course.component';
import {HomeComponent} from './components/home/home.component';
import {UserComponent} from './user/user.component';

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
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
