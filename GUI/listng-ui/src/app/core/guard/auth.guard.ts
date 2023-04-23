import {inject} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';

export const AuthGuard = () => {
    const router = inject(Router);
    const service = inject(AuthenticationService);

    if (service.canAccess()) {
        return true;
    }

    // TODO call nejaku error service co vyhodi modalne okno so spravou

    return router.navigate(['login']);
}
