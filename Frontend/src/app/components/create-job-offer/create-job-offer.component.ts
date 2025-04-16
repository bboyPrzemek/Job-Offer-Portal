import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { JobOfferService} from '../../services/job-offer.service';
import { CommonModule } from '@angular/common';
import { JobOfferModel } from '../../interfaces/job-offer-model';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-job-offer',
  standalone: true,
  imports: [FormsModule, CommonModule, NgMultiSelectDropDownModule],
  templateUrl: './create-job-offer.component.html',
  styleUrl: './create-job-offer.component.css',
})
export class CreateJobOfferComponent implements OnInit {

  jobOfferModel: JobOfferModel = {
    title: '',
    details: '',
    salaryMin: '',
    salaryMax: '',
    location: '',
    position: '',
    experiences: [],
    technologies: [],
    worktypes: []
  }

  dropdownList: any = [];
  selectedItems: any = [];
  dropdownSettings = {};

  validationMessages = {
    required: 'This field is required.',
  };

  ngOnInit() {
    this.dropdownList = {
      technologies: [{
          id: 1,
          value: 'Java'
        },
        {
          id: 2,
          value: 'Salesforce'
        }
      ],

      worktypes: [{
          id: 1,
          value: 'Remote'
        },
        {
          id: 2,
          value: 'Hybrid'
        },
        {
          id: 3,
          value: 'Office'
        }
      ],

      experiences: [{
          id: 1,
          value: 'Junior'
        },
        {
          id: 2,
          value: 'Mid'
        },
        {
          id: 3,
          value: 'Senior'
        }
      ]
    };

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'value',
      selectAllText: 'Select All',
      unSelectAllText: 'Deselect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }

  constructor(private jobOfferService: JobOfferService, private toast: ToastrService) {}

  onSubmit(form: NgForm) {
    console.log(form.value)
    this.jobOfferService.createJobOffer(form.value).subscribe(response => {
      if (response.status == 201) {
        this.toast.success('Success');
        form.resetForm();
      }
    }, (error) => {
      this.toast.error(error);
    });
  }
}
