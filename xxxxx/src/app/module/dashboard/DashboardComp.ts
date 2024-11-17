import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'DashboardComp',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './DashboardComp.html',
  styleUrl: './DashboardComp.scss'
})
export class DashboardComp {

  title = 'DashboardComp';

}
