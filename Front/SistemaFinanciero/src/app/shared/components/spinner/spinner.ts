import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-spinner',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './spinner.html',
  styleUrl: './spinner.css',
})
export class SpinnerComponent {
  @Input() size: number = 40;
  @Input() show: boolean = false;
}
