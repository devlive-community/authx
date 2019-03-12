import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// router
const routes: Routes = [
  { path: '', loadChildren: './layout/layout.module#LayoutModule' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
