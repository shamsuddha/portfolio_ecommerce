import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'OrderComp',
    templateUrl: 'OrderComp.html',
    styleUrls: ['OrderComp.scss'],
    standalone: true,
    imports: [CommonModule]
})

export class OrderComp implements OnInit {

    constructor() { }

    ngOnInit() { }

}