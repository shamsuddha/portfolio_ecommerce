import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DataGridComp } from '../../../widget/data_grid/DataGridComp';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RxFormBuilder, RxReactiveFormsModule } from '@rxweb/reactive-form-validators';
import { Floor } from '../../../entity/Floor';
import { Observable, map, of } from 'rxjs';
import { ColDef } from 'ag-grid-community';
import { ButtonCellRendererComp } from '../../../widget/data_grid/button_cell_renderer/ButtonCellRendererComp';
import { FloorController } from '../../../controller/FloorController';
import { FloorSearchDto } from '../../../dto/FloorSearchDto';
import { PaginationComp } from '../../../widget/pagination/PaginationComp';
import { NgSelectModule } from '@ng-select/ng-select';

@Component({
  selector: 'FloorSetupComp',
  templateUrl: './FloorSetupComp.html',
  styleUrls: ['./FloorSetupComp.scss'],
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
export class FloorSetupComp implements OnInit {

  floorFg: FormGroup = this.rxFormBuilder.formGroup(Floor);
  floorSearchFg: FormGroup = this.rxFormBuilder.formGroup(Floor);
  floorList$: Observable<Array<Floor>> = of([]);
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
    private floorController: FloorController
  ) { }

  ngOnInit(): void {
    this.search();
  }

  save() {
    this.floorController.save(this.floorFg.value)
      .subscribe((e: Floor) => {
        console.log(e);
        this.search();
      });
  }

  onUpdateClick(floor: any) {
    console.log('on update click', floor)
    this.floorFg.patchValue(floor);
    this.floorFg.patchValue({ updateMode: true });
  }

  update() {
    this.floorController.update(this.floorFg.value)
      .subscribe((e: Floor) => {
        this.search();
      });

  }

  onDeleteClick(data: any) {
    this.floorController.delete(data)
    .subscribe((e) => { this.search(); });
  }

  search() {
    this.floorList$ = this.floorController.search(new FloorSearchDto({ 'idList': [] }))
      .pipe(map(e => e.content));
  }

  searchByName(data: any) {
    console.log(data);
    this.floorList$ = this.floorController.search(new FloorSearchDto({ 'idList': [] }))
      .pipe(
        map(e => e.content.filter((f) => f.name == data))
      );
    console.log(this.floorList$);
  }

  viewDetail(data: any) {
    console.log('btn click', data)
  }
  
  reset() {
    this.floorFg.reset();
  }

  cng($event: any) {
    console.log($event)
  }

}
