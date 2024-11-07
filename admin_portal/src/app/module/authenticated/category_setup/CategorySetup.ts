import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RxFormBuilder } from '@rxweb/reactive-form-validators';
import { CategorySetupController } from '../../../controller/CategorySetupController';

@Component({
    selector: 'CategorySetup',
    templateUrl: 'CategorySetup.html',
    styleUrls: ['CategorySetup.scss'],
    standalone: true,
    imports: [CommonModule]
})
export class CategorySetup implements OnInit {

    constructor(
        private rxFormBuilder: RxFormBuilder,
        private categorySetupController: CategorySetupController
    ) { }

    ngOnInit() { }

}