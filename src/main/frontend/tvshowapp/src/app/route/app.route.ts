/**
 * Created by lingda on 26/06/2017.
 */
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SearchResultComponent} from '../component/search-result/search-result.component';
import {EntranceComponent} from '../component/entrance/entrance.component';
import {SearchResultTableComponent} from "../component/search-result-table/search-result-table.component";

const routes: Routes = [
  {path: 'index', component: EntranceComponent},
  {
    path: 'search',
    component: SearchResultComponent,
    children: [
      {path: '', component: SearchResultTableComponent},
      {path: 'show', component: SearchResultTableComponent},
      {path: 'movie', component: SearchResultTableComponent},
      {path: 'variety', component: SearchResultTableComponent}
    ]
  },
  {
    path: '',
    redirectTo: '/index',
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
