import { Component, inject, OnInit } from '@angular/core';
import { NavigationComponent } from '../navigation/navigation.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { JobOfferService } from '../joboffer/services/job-offer.service';

@Component({
  selector: 'app-job-offer-details',
  imports: [NavigationComponent, CommonModule],
  templateUrl: './job-offer-details.component.html',
  styleUrl: './job-offer-details.component.css',
})
export class JobOfferDetailsComponent implements OnInit {
  private jobOfferService: JobOfferService = inject(JobOfferService);
  private router: Router = inject(Router);

  public result: {
    title: String;
    details: String;
    salaryMin: number;
    salaryMax: number;
    location: {
      city: String;
      id: string;
    };
    user: {
      displayName: String;
      id: String;
    };
    worktypes: {
      name: String;
      id: String;
    }[];
    experiences: {
      name: String;
      id: String;
    }[];
    position: {
      name: String;
      id: String;
    };
  } = {
    title: '',
    details: '',
    salaryMin: 0,
    salaryMax: 0,
    location: {
      city: '',
      id: '',
    },
    user: {
      displayName: '',
      id: '',
    },
    worktypes: [],
    position: {
      name: '',
      id: '',
    },
    experiences: [],
  };

  public ngOnInit(): void {
    const offerId = this.router.url.split('/').pop();
    this.jobOfferService.getOfferById(offerId).subscribe((result) => {
      console.log(result);
      this.result = result;
    });
  }
}
