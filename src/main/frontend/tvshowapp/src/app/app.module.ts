import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {AppRouteModule} from './route/app.route';
import {SearchFormComponent} from './component/search-form/search-form.component';
import { HeaderComponent } from './component/header/header.component';
import {ShowService} from './service/show.service';

@NgModule({
  declarations: [
    AppComponent,
    SearchFormComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpModule, AppRouteModule
  ],
  providers: [ShowService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
