import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {WishlistComponent} from "./wishlist/wishlist.component";
import {viewGuard} from "./guards/view.guard";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path:'home',component: HomeComponent,canActivate:[viewGuard]},
  {path: 'wishlist', component: WishlistComponent, canActivate: [viewGuard]},
  {path: '', redirectTo: 'login', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
