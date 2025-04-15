import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { NavigationComponent } from '../navigation/navigation.component';
import { JobOfferService } from '../job-offer.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { EmailService } from '../email.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-job-offer-details',
  imports: [NavigationComponent, CommonModule, FormsModule],
  templateUrl: './job-offer-details.component.html',
  styleUrl: './job-offer-details.component.css'
})
export class JobOfferDetailsComponent implements OnInit {

  isOpen: boolean = false;
  attachment : File | null = null;
  valid : Boolean = false;

  validationMessages = {
    required : "This value is required"
  }

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

  constructor(private jobOfferService: JobOfferService, private emailService : EmailService, private router : Router,  private toast : ToastrService) {}

  ngOnInit(): void {
    const offerId = this.router.url.split("/").pop();
    this.jobOfferService.getOfferById(offerId).subscribe(result => {
      this.result = result;
    })
  }

  submit(){
    let formData = new FormData();
    if (this.attachment){
       formData.append('file', this.attachment);
    }

    if (this.valid == false) return;

    this.emailService.sendEmail(formData).subscribe(e=>{
      if (e.status === 200){
        this.toast.success('Email has been sent.');
      }
    }, error=>{
      this.toast.error('Someting went wrong.');
    })
    this.closePopup();
  }

  onChange(e : Event){
    const input = e.target as HTMLInputElement;
    if (input.files && input.files.length > 0){
      this.attachment = input.files[0];
      this.valid = true;
    }else{
      this.valid = false;
    }
  }

  showPopup(){
    this.isOpen = true;
  }

  closePopup(){
    this.isOpen = false;
  }
}
