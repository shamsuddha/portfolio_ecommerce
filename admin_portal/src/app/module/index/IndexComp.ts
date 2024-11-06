import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'IndexComp',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './IndexComp.html',
  styleUrl: './IndexComp.scss'
})
export class IndexComp {

  title = 'IndexComp';
 // hello world


}
