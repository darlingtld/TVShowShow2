import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {AppRouteModule} from './app-route.module';
import {SearchFormComponent} from './search-form/search-form.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchFormComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpModule, AppRouteModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
