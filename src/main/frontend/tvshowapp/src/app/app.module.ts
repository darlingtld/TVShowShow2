import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {AppRouteModule} from './route/app.route';
import {SearchFormComponent} from './component/search-form/search-form.component';
import {HeaderComponent} from './component/header/header.component';
import {ShowService} from './service/show.service';
import {SearchResultTableComponent} from './component/search-result-table/search-result-table.component';
import {SearchResultDetailComponent} from './component/search-result-detail/search-result-detail.component';
import {SearchService} from './service/search.service';

@NgModule({
  declarations: [
    AppComponent,
    SearchFormComponent,
    HeaderComponent,
    SearchResultTableComponent,
    SearchResultDetailComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpModule, AppRouteModule
  ],
  providers: [ShowService, SearchService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
