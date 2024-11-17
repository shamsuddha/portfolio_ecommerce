import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'NotificationComp',
    templateUrl: 'NotificationComp.html',
    styleUrls: ['NotificationComp.scss'],
    standalone: true,
    imports: [CommonModule]
})

export class NotificationComp implements OnInit {

    constructor() { }

    ngOnInit() { }

}