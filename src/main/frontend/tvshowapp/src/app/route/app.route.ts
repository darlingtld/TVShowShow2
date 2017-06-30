/**
 * Created by lingda on 26/06/2017.
 */
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SearchResultComponent} from '../component/search-result/search-result.component';
import {EntranceComponent} from '../component/entrance/entrance.component';

const routes: Routes = [
  {path: 'search', component: EntranceComponent},
  {
    path: 'search/:term',
    component: SearchResultComponent,
  },
  {
    path: '',
    redirectTo: '/search',
    pathMatch: 'full'
  },
  {path: '**', component: EntranceComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRouteModule {
}
