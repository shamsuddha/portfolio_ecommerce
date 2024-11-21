import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'PointComp',
    templateUrl: 'PointComp.html',
    styleUrls: ['PointComp.scss'],
    standalone: true,
    imports: [CommonModule]
})

export class PointComp implements OnInit {

    constructor() { }

    ngOnInit() { }

}