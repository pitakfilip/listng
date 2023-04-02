import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './main/main.component';
import {LoginComponent} from './main/components/login/login.component';

const routes: Routes = [
    {
        path: '',
        component: MainComponent,
        loadChildren: () => import('./main/main.module').then(m => m.MainModule)
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'error',
        component: LoginComponent //TODO urobit error stranku pre tento pripad
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
