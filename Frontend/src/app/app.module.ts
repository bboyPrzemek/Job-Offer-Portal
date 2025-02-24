import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Routing} from './routing/routing.module';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
      AppComponent
  ],
  imports: [
    Routing,
    BrowserModule,
    HttpClientModule,
    RouterOutlet,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}







