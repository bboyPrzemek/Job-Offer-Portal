import {Component, OnInit } from '@angular/core';
import { NavigationComponent} from '../navigation/navigation.component';
import { JobOfferService } from '../job-offer.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router'

@Component({
  selector: 'app-job-offer-details',
  imports: [NavigationComponent, CommonModule],
  templateUrl: './job-offer-details.component.html',
  styleUrl: './job-offer-details.component.css'
})
export class JobOfferDetailsComponent implements OnInit {

  result: {
    title: String;
    details: String;
    salaryMin: number;
    salaryMax: number;
    location: {
      city: String,
      id: string
    },
    user: {
      displayName: String,
      id: String
    },
    worktypes: {
      name: String,
      id: String
    } [],
    experiences: {
      name: String,
      id: String
    } [],
    position: {
      name: String,
      id: String
    }
  } = {
    title: '',
    details: '',
    salaryMin: 0,
    salaryMax: 0,
    location: {
      city: '',
      id: ''
    },
    user: {
      displayName: '',
      id: ''
    },
    worktypes: [],
    position: {
      name: '',
      id: ''
    },
    experiences: [],
  };

  constructor(private jobOfferService: JobOfferService,private router : Router) {}

  ngOnInit(): void {
    const offerId = this.router.url.split("/").pop();
    this.jobOfferService.getOfferById(offerId).subscribe(result => {
      console.log(result)
      this.result = result;
    })
  }

}
