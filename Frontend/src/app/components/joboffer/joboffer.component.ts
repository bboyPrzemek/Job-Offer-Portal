import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

@Component({
  selector: 'joboffer',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './joboffer.component.html',
  styleUrl: './joboffer.component.css',
})
export class JobofferComponent {
  @Input() Id: string = '';
  @Input() title: string = '';
  @Input() companyName: string = '';
  @Input() location: string = '';
  @Input() imgSrc: string = '';
  @Input() salaryMin: string = '';
  @Input() salaryMax: string = '';
  @Input() position: { id: string; name: string } = { id: '', name: '' };
  @Input() technology: { id: string; name: string }[] = [];
  @Input() experience: { id: string; name: string }[] = [];
  @Input() worktype: { id: string; name: string }[] = [];
}
