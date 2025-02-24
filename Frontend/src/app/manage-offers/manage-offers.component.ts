import { Component, OnInit } from '@angular/core';
import { JobOfferService } from '../job-offer.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-manage-offers',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './manage-offers.component.html',
  styleUrl: './manage-offers.component.css'
})
export class ManageOffersComponent implements OnInit{

  constructor(private jobOfferService : JobOfferService){}

  headers = ["#", "Title", "Salary Min", "Salary Max", "Created Date", "Action"];
  offers : any = [] ;

  ngOnInit() {
      this.jobOfferService.getJobOffers().subscribe(result =>{
        console.log(result)
        this.offers = result;
      })
  }
}
