import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../components/home/home.component';
import { SigninComponent } from '../components/signin/signin.component';
import { AdminpanelComponent } from '../components/adminpanel/adminpanel.component';
import { AccountSettingsComponent } from '../components/account-settings/account-settings.component';
import { AuthGuard } from '../guards/auth.guard';
import { RegisterComponent } from '../components/register/register.component';
import { JobOfferDetailsComponent } from '../components/job-offer-details/job-offer-details.component';
import { ManageOffersComponent } from '../components/manage-offers/manage-offers.component';
import { CreateJobOfferComponent } from '../components/create-job-offer/create-job-offer.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch : 'full' },
  { path: 'login', component : SigninComponent },
  { path : 'register', component : RegisterComponent },
  { path: 'offer/:id', component : JobOfferDetailsComponent},
  {path: 'admin', component : AdminpanelComponent , canActivate : [AuthGuard],
    children: [
      { path: 'manage', component: ManageOffersComponent },
      { path: 'settings', component: AccountSettingsComponent },
      { path: 'create', component : CreateJobOfferComponent }
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

export class Routing { }
