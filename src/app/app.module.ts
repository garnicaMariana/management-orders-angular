import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllOrdersComponent } from './all-orders/all-orders.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component'
import { FormsModule } from '@angular/forms';
import { UpdateComponent } from './update/update.component';
import { LoadDataComponent } from './load-data/load-data.component';
import {DataTablesModule} from 'angular-datatables';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog'; 
import { MatButtonModule } from '@angular/material/button'; 
import { MatButtonToggleModule } from '@angular/material/button-toggle'; 

import { BrowserModule } from "@angular/platform-browser";
import { IgxDatePickerModule } from "igniteui-angular";



@NgModule({
  declarations: [
    AppComponent,
    AllOrdersComponent,
    RegisterComponent,
    UpdateComponent,
    LoadDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DataTablesModule,
    MatDatepickerModule,
    MatInputModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    MatButtonToggleModule,
    IgxDatePickerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
