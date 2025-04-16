import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../components/home/home.component';
import { SigninComponent } from '../components/signin/signin.component';
import { RegisterComponent } from '../components/register/register.component';
import { AdminpanelComponent } from '../components/adminpanel/adminpanel.component';
import { ManageOffersComponent } from '../components/manage-offers/manage-offers.component';
import { AccountSettingsComponent } from '../components/account-settings/account-settings.component';
import { AuthGuard } from '../guards/auth.guard';
import { CreateJobOfferComponent } from '../components/create-job-offer/create-job-offer.component';
import { JobOfferDetailsComponent } from '../components/job-offer-details/job-offer-details.component';


const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch : 'full', canActivate: [AuthGuard], data: { requiresAuth: false}},
  { path: 'login', component : SigninComponent, canActivate: [AuthGuard], data: { requiresAuth: false}},
  { path : 'register', component : RegisterComponent, canActivate: [AuthGuard], data: { requiresAuth: false}},
  { path: 'offer/:id', component : JobOfferDetailsComponent , canActivate: [AuthGuard], data: { requiresAuth: false}},
  {path: 'admin', component : AdminpanelComponent , canActivate : [AuthGuard], data: { requiresAuth: true },
    children: [
      { path: 'manage', component: ManageOffersComponent , canActivate : [AuthGuard], data: { requiresAuth: true } },
      { path: 'settings', component: AccountSettingsComponent,  canActivate : [AuthGuard], data: { requiresAuth: true } },
      { path: 'create', component : CreateJobOfferComponent,  canActivate : [AuthGuard], data: { requiresAuth: true } }
    ]
  }
];


@NgModule({
  declarations: [],
  imports:
    [
      CommonModule, RouterModule.forRoot(routes)
    ],
  exports: [RouterModule],
})

export class Routing {}
