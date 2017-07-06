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
import { SearchResultComponent } from './component/search-result/search-result.component';
import { EntranceComponent } from './component/entrance/entrance.component';
import { DefaultImgDirective } from './directive/default-img.directive';
import { CategoryNavbarComponent } from './component/category-navbar/category-navbar.component';
import { DownloadSectionComponent } from './component/download-section/download-section.component';
import { DownloadLinkComponent } from './component/download-link/download-link.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchFormComponent,
    HeaderComponent,
    SearchResultTableComponent,
    SearchResultDetailComponent,
    SearchResultComponent,
    EntranceComponent,
    DefaultImgDirective,
    CategoryNavbarComponent,
    DownloadSectionComponent,
    DownloadLinkComponent,
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpModule, AppRouteModule
  ],
  providers: [ShowService, SearchService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
