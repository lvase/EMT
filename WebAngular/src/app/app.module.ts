import {NgModule} from '@angular/core';
import {BrowserModule, provideClientHydration} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {AuthService} from "./services/auth.service";
import {LoginComponent} from './login/login.component';
import {FormsModule} from "@angular/forms";
import {WishlistComponent} from './wishlist/wishlist.component';
import {JwtInterceptor} from "./interceptors/jwt-intereceptor.interceptor";
import {SharedModule} from "./shared/shared.module";
import {HomeComponent} from "./home/home.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WishlistComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SharedModule
  ],
  providers: [
    provideClientHydration(),
    AuthService,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
