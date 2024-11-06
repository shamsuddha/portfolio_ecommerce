import {
  ChangeDetectorRef,
  Component, EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChange,
  SimpleChanges
} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PageChangedEvent, PaginationModule} from "ngx-bootstrap/pagination";
import {FormsModule} from "@angular/forms";
import {Page} from "../../dto/Page";

@Component({
  selector: 'PaginationComp',
  standalone: true,
  imports: [CommonModule, PaginationModule, FormsModule],
  template: `
    <!--
    totalItems  : total page
    currentPage : current page
    itemPerPage : item per page
    -->
    <pagination class="mt-3 px-2"
                [maxSize]="maxSize"
                [itemsPerPage]="page.size"
                [boundaryLinks]="true"
                [totalItems]="page.totalElements"
                [rotate]="true"
                [ngModel]="page.pageable.pageNumber + 1"
                [disabled]="false"
                (pageChanged)="pageChanged($event)">
    </pagination>
  `,
})
export class PaginationComp implements OnInit, OnChanges {

  maxSize: number = 0;  // number of page show in pagination at a time
  @Input() page: Page<any> = new Page<any>({content: []});  // page

  @Output() onPageChange = new EventEmitter<PageChangedEvent>();

  constructor(private cdr: ChangeDetectorRef) {
  }

  ngOnChanges(changes: { [propName: string]: SimpleChange }) {
    if (changes['page']) {
      this.page = changes['page'].currentValue;
    }
    /*if( changes['totalItems'] && changes['totalItems'].previousValue != changes['totalItems'].currentValue ) {
      this.totalItems = changes['totalItems'].currentValue;
    }
    if( changes['itemPerPage'] && changes['itemPerPage'].previousValue != changes['itemPerPage'].currentValue ) {
      this.itemPerPage = changes['itemPerPage'].currentValue;
    }
    if( changes['currentPage'] && changes['currentPage'].previousValue != changes['currentPage'].currentValue ) {
      setTimeout(()=>{ this.currentPage = changes['currentPage'].currentValue  },25)
    }*/
    this.cdr.detectChanges();
  }

  ngOnInit(): void {
    this.maxSize = 15;
    this.cdr.detectChanges();
  }

  pageChanged($event: PageChangedEvent) {
    console.log($event);
    this.onPageChange.emit(
      {page: $event.page - 1, itemsPerPage: $event.itemsPerPage})
  }

}
