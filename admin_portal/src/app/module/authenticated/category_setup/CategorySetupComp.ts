import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { DataGridComp } from "../../../widget/data_grid/DataGridComp";
import { FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RxFormBuilder, RxReactiveFormsModule } from "@rxweb/reactive-form-validators";
import { PaginationComp } from "../../../widget/pagination/PaginationComp";
import { NgSelectModule } from "@ng-select/ng-select";
import { Category } from "../../../entity/Category";
import { map, Observable, of } from "rxjs";
import { ColDef } from "ag-grid-community";
import { ButtonCellRendererComp } from "../../../widget/data_grid/button_cell_renderer/ButtonCellRendererComp";
import { CategoryController } from "../../../controller/CategoryController";
import { CategorySearchDto } from "../../../dto/CategorySearchDto";


@Component({
    selector: 'CategorySetupComp',
    templateUrl: 'CategorySetupComp.html',
    styleUrls: ['CategorySetupComp.scss'],
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
export class CategorySetupComp implements OnInit {

    categoryFg: FormGroup = this.rxFormBuilder.formGroup(Category);
    categorySearchFg: FormGroup = this.rxFormBuilder.formGroup(Category);
    categoryList$: Observable<Array<Category>> = of([]);
    showItemPerPage: Array<number> = [10, 15, 20, 25];
    colDefList: ColDef[] = [
        { field: 'id' },
        { field: 'name' },
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
            cellStyle: { color: 'black', 'padding-left': '0px', 'padding-right': '0px' },
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
        private categoryController: CategoryController
    ) { }

    ngOnInit(): void {
        this.search();
    }

    save() {
        this.categoryController.save(this.categoryFg.value)
            .subscribe((e: Category) => {
                console.log(e);
                this.search();
            });
    }

    onUpdateClick(category: any) {
        console.log('on update click', category)
        this.categoryFg.patchValue(category);
        this.categoryFg.patchValue({ updateMode: true });
    }

    update() {
        this.categoryController.update(this.categoryFg.value)
            .subscribe((e: Category) => {
                this.search();
            });

    }

    onDeleteClick(data: any) {
        this.categoryController.delete(data)
            .subscribe((e) => { this.search(); });
    }

    search() {
        this.categoryList$ = this.categoryController.search(new CategorySearchDto({ 'idList': [] }))
            .pipe(map(e => e.content));
    }

    searchByName(data: any) {
        console.log(data);
        this.categoryList$ = this.categoryController.search(new CategorySearchDto({ 'idList': [] }))
            .pipe(
                map(e => e.content.filter((f) => f.name == data))
            );
        console.log(this.categoryList$);
    }

    viewDetail(data: any) {
        console.log('btn click', data)
    }

    reset() {
        this.categoryFg.reset();
    }

    cng($event: any) {
        console.log($event)
    }

}