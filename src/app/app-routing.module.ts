import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllOrdersComponent } from './all-orders/all-orders.component';
import { RegisterComponent } from './register/register.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  {path: 'orders', component:AllOrdersComponent},
  {path: '', redirectTo:'orders', pathMatch:'full'},
  {path: 'register-orders', component:RegisterComponent},
  {path: 'update-order/:id', component:UpdateComponent},
  {path: 'delete-order/:id', redirectTo:'orders' },
  {path: 'orders/time/:startDate/:endDate', component:AllOrdersComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
