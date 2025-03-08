import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobOfferService } from '../joboffer/services/job-offer.service';

@Component({
  selector: 'app-manage-offers',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './manage-offers.component.html',
  styleUrl: './manage-offers.component.css',
})
export class ManageOffersComponent implements OnInit {
  private jobOfferService: JobOfferService = inject(JobOfferService);

  public headers: string[] = [
    '#',
    'Title',
    'Salary Min',
    'Salary Max',
    'Created Date',
    'Action',
  ];
  offers: any = [];

  public ngOnInit(): void {
    this.jobOfferService.getJobOffers().subscribe((result) => {
      console.log(result);
      this.offers = result;
    });
  }
}
