/**
 * Created by lingda on 26/06/2017.
 */
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SearchFormComponent} from '../component/search-form/search-form.component';

const routes: Routes = [
  {path: 'search', component: SearchFormComponent},
  // { path: 'hero/:id',      component: HeroDetailComponent },
  // {
  //   path: 'heroes',
  //   component: HeroListComponent,
  //   data: { title: 'Heroes List' }
  // },
  {
    path: '',
    redirectTo: '/search',
    pathMatch: 'full'
  },
  {path: '**', component: SearchFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRouteModule {
}
