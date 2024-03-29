import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './main/main.component';
import {LoginComponent} from './main/components/login/login.component';
import {PasswordResetComponent} from './main/components/password-reset/password-reset.component';
import {AuthGuard} from './core/guard/auth.guard';
import {NotFoundComponent} from './main/components/not-found/not-found.component';

const routes: Routes = [
    {
        path: '',
        component: MainComponent,
        loadChildren: () => import('./main/main.module').then(m => m.MainModule),
        canActivate: [AuthGuard],
        canActivateChild: [AuthGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'reset',
        component: PasswordResetComponent
    },
    {
        path: 'reset/:hash',
        component: PasswordResetComponent,
    },
    {
        path: 'not-found',
        component: NotFoundComponent
    },
    {
        path: '**',
        redirectTo: 'not-found'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
