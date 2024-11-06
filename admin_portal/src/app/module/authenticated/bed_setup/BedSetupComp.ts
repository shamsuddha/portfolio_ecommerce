import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DataGridComp } from '../../../widget/data_grid/DataGridComp';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RxFormBuilder, RxReactiveFormsModule } from '@rxweb/reactive-form-validators';
import { Bed } from '../../../entity/Bed';
import { Observable, map, of } from 'rxjs';
import { ColDef } from 'ag-grid-community';
import { ButtonCellRendererComp } from '../../../widget/data_grid/button_cell_renderer/ButtonCellRendererComp';
import { BedController } from '../../../controller/BedController';
import { BedSearchDto } from '../../../dto/BedSearchDto';
import { PaginationComp } from '../../../widget/pagination/PaginationComp';
import { NgSelectModule } from '@ng-select/ng-select';

@Component({
  selector: 'BedSetupComp',
  templateUrl: './BedSetupComp.html',
  styleUrls: ['./BedSetupComp.scss'],
  standalone: true,
  imports: [CommonModule,
    DataGridComp,
    FormsModule,
    ReactiveFormsModule,
    RxReactiveFormsModule,
    PaginationComp,
    NgSelectModule
  ]
})
export class BedSetupComp implements OnInit {

  bedFg: FormGroup = this.rxFormBuilder.formGroup(Bed);
  bedSearchFg: FormGroup = this.rxFormBuilder.formGroup(Bed);
  bedList$: Observable<Array<Bed>> = of([]);
  showItemPerPage: Array<number> = [10, 15, 20, 25];
  
  colDefList: ColDef[] = [
    { field: 'id' },
    { field: 'name' },
    { field: 'code' },
    { field: 'numberOfBed' },
    { field: 'numberOfAdultGuest' },
    { field: 'numberOfChildGuest' },
    { field: 'floorSize' },
    { field: 'floorName' },
    { field: 'description' },
    {
      headerName: '', editable: false, colId: 'action', width: 80,
      cellRenderer: ButtonCellRendererComp, pinned: 'left',
      cellStyle: { color: 'black', 'padding-left': '0px', 'padding-right': '0px' },
      cellRendererParams: {
        buttonLabel: 'Detail',
        buttonClass: 'btn btn-primary mb-1 py-1 px-2 ms-2',
        buttonOnClick: (param: any) => {
          this.viewDetail(param.data);
        }
      }
    },
    {
      headerName: '', editable: false, colId: 'action', width: 80,
      cellRenderer: ButtonCellRendererComp, pinned: 'left',
      cellStyle: { color: 'black', 'padding-left': '0px', 'padding-right': '0px'  },
      cellRendererParams: {
        buttonLabel: 'Update',
        buttonClass: 'btn btn-info mb-1 py-1 px-2',
        buttonOnClick: (param: any) => {
          this.onUpdateClick(param.data);
        }
      }
    },
    {
      headerName: '', editable: false, colId: 'action', width: 80,
      cellRenderer: ButtonCellRendererComp, pinned: 'left',
      cellStyle: { color: 'black', 'padding-left': '0px', 'padding-right': '0px' },
      cellRendererParams: {
        buttonLabel: 'Delete',
        buttonClass: 'btn btn-danger mb-1 py-1 px-2',
        buttonOnClick: (param: any) => {
          this.onDeleteClick(param.data);
        }
      }
    },
  ];

  constructor(
    private rxFormBuilder: RxFormBuilder,
    private bedController: BedController
  ) { }

  ngOnInit(): void {
    this.search();
  }

  save() {
    this.bedController.save(this.bedFg.value)
      .subscribe((e: Bed) => {
        console.log(e);
        this.search();
      });
  }

  onUpdateClick(floor: any) {
    console.log('on update click', floor)
    this.bedFg.patchValue(floor);
    this.bedFg.patchValue({ updateMode: true });
  }

  update() {
    this.bedController.update(this.bedFg.value)
      .subscribe((e: Bed) => {
        this.search();
      });

  }

  onDeleteClick(data: any) {
    this.bedController.delete(data)
      .subscribe((e) => { this.search(); });
  }

  search() {
    this.bedList$ = this.bedController.search(new BedSearchDto({ 'idList': [] }))
      .pipe(map(e => e.content));
  }

  searchByName(data: any) {
    console.log(data);
    this.bedList$ = this.bedController.search(new BedSearchDto({ 'idList': [] }))
      .pipe(
        map(e => e.content.filter((f) => f.name == data))
      );
    console.log(this.bedList$);
  }

  viewDetail(data: any) {
    console.log('btn click', data)
  }

  reset() {
    this.bedFg.reset();
  }

  cng($event: any) {
    console.log($event)
  }

}


