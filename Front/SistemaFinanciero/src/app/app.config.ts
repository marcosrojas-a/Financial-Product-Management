import { ApplicationConfig, provideBrowserGlobalErrorListeners, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi, HTTP_INTERCEPTORS } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import localeEsCo from '@angular/common/locales/es-CO';
import { routes } from './app.routes';
import { HttpErrorInterceptor } from './core/interceptors/http-error.interceptor';
import { LoadingInterceptor } from './core/interceptors/LoadingInterceptor';

registerLocaleData(localeEsCo);

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true },
    {provide: HTTP_INTERCEPTORS,useClass: LoadingInterceptor,multi: true}

  ],
};
