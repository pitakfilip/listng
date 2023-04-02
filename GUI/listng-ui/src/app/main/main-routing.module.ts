import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CourseComponent} from './course/course.component';
import {HomeComponent} from './components/home/home.component';
import {StudentComponent} from './student/student.component';

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
        path: 'students',
        component: StudentComponent,
        loadChildren: () => import('./student/student.module').then(m => m.StudentModule)
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
