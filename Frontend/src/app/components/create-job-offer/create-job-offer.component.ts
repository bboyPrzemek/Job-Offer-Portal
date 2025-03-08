import { Component, inject, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { ToastrService } from 'ngx-toastr';
import { JobOfferInterface } from '../../interfaces/job-offer';
import { JobOfferService } from '../joboffer/services/job-offer.service';

@Component({
  selector: 'app-create-job-offer',
  standalone: true,
  imports: [FormsModule, CommonModule, NgMultiSelectDropDownModule],
  templateUrl: './create-job-offer.component.html',
  styleUrl: './create-job-offer.component.css',
})
export class CreateJobOfferComponent implements OnInit {
  private jobOfferService: JobOfferService = inject(JobOfferService);
  private toast: ToastrService = inject(ToastrService);

  public jobOfferModel: JobOfferInterface = null;
  public dropdownList: any = {};
  public selectedItems: any[] = [];
  public dropdownSettings: any = {};
  public validationMessages = {
    required: 'This field is required.',
  };

  public ngOnInit(): void {
    this.createDropdownList();
    this.createDropdownSettings();
  }

  public showSuccess(): void {
    //this.toast.success('Hello world!', 'Toastr fun!');
  }

  public onSubmit(form: NgForm): void {
    console.log(form.value);
    this.jobOfferService.createJobOffer(form.value).subscribe(
      (response) => {
        const statusOk: number = 201;
        if (response.status === statusOk) {
          this.toast.success('Success');
          form.resetForm();
        }
      },
      (error) => {
        this.toast.error(error);
      }
    );
  }

  private createDropdownSettings(): void {
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'value',
      selectAllText: 'Select All',
      unSelectAllText: 'Deselect All',
      itemsShowLimit: 3,
      allowSearchFilter: true,
    };
  }

  private createDropdownList(): void {
    this.dropdownList = {
      technologies: [
        {
          id: 1,
          value: 'Java',
        },
        {
          id: 2,
          value: 'Salesforce',
        },
      ],

      worktypes: [
        {
          id: 1,
          value: 'Remote',
        },
        {
          id: 2,
          value: 'Hybrid',
        },
        {
          id: 3,
          value: 'Office',
        },
      ],

      experiences: [
        {
          id: 1,
          value: 'Junior',
        },
        {
          id: 2,
          value: 'Mid',
        },
        {
          id: 3,
          value: 'Senior',
        },
      ],
    };
  }
}
