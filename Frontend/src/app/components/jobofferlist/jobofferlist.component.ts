import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { JobofferComponent } from '../joboffer/joboffer.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'jobofferlist',
  standalone: true,
  imports: [JobofferComponent, CommonModule],
  templateUrl: './jobofferlist.component.html',
  styleUrl: './jobofferlist.component.css'
})

export class JobofferlistComponent {
  initialized = false;
  @Input() jobOffers: any[] = [];
}