import { SearchModel } from './../../interfaces/search.interface';
import { Component, inject, OnInit } from '@angular/core';
import { JobofferlistComponent } from '../jobofferlist/jobofferlist.component';
import { JobOfferService } from '../joboffer/services/job-offer.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'home',
  standalone: true,
  imports: [
    JobofferlistComponent,
    FormsModule,
    CommonModule,
    RouterModule,
    NavigationComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  private jobOfferService: JobOfferService = inject(JobOfferService);
  private router: Router = inject(Router);
  private activated: ActivatedRoute = inject(ActivatedRoute);

  public visibility: string = '';
  public searchModel: SearchModel;
  public sort = '';
  public jobOffers: any = [];

  public ngOnInit(): void {
    this.activated.queryParams.subscribe((params) => {
      this.search(params);
    });
  }

  public onSubmit(form: NgForm): void {
    const formData = { ...form.value };
    let params = this.removeEmptyParams(
      this.mergeObj(formData, this.getSortParams())
    );
    this.router.navigate([''], { queryParams: params });
  }

  public onSort(): void {
    const currentParams = {
      ...this.router.routerState.snapshot.root.queryParams,
    };
    let params = this.removeEmptyParams(
      this.mergeObj(currentParams, this.getSortParams())
    );
    this.router.navigate([''], { queryParams: params });
  }

  public mergeObj(obj1: Object, obj2: Object): any {
    return { ...obj1, ...obj2 };
  }

  public showFilters(): void {
    this.visibility = 'active';
  }

  public close(): void {
    this.visibility = 'inactive';
  }

  public search(queryParams: any): void {
    this.jobOfferService
      .searchOffers(new URLSearchParams(queryParams).toString())
      .subscribe((result) => {
        console.log(result);
        this.jobOffers = result;
      });
  }

  public removeEmptyParams(obj: any): any {
    Object.keys(obj).forEach((key) => obj[key] === '' && delete obj[key]);
    return obj;
  }

  public getSortParams(): any {
    const s = this.sort.split('_');
    return {
      orderBy: s[0],
      sort: s[1],
    };
  }
}
