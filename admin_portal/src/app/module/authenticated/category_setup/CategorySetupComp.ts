import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RxFormBuilder } from '@rxweb/reactive-form-validators';
import { CategorySetupController } from '../../../controller/CategorySetupController';

@Component({
    selector: 'CategorySetupComp',
    templateUrl: 'CategorySetupComp.html',
    styleUrls: ['CategorySetupComp.scss'],
    standalone: true,
    imports: [CommonModule]
})
export class CategorySetupComp implements OnInit {

    constructor(
        private rxFormBuilder: RxFormBuilder,
        private categorySetupController: CategorySetupController
    ) { }

    ngOnInit() { }

}