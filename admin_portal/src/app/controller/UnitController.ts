import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from "../dto/Page";
import { Unit } from "../entity/Unit";
import { UnitSearchDto } from "../dto/UnitSearchDto";

@Injectable({ providedIn: 'root' })
export class UnitController {

    constructor(private httpClient: HttpClient) { }

    save(unit: Unit): Observable<Unit> {

        return this.httpClient.post<Unit>('http://localhost:9999/unit/save', unit);
    }

    update(unit: Unit): Observable<Unit> {
        return this.httpClient.post<Unit>('http://localhost:9999/unit/update', unit);
    }

    delete(unit: Unit): Observable<boolean> {
        return this.httpClient.post<boolean>('http://localhost:9999/unit/delete', unit)
    }

    search(unitSearchDto: UnitSearchDto): Observable<Page<Unit>> {
        return this.httpClient.post<Page<Unit>>('http://localhost:9999/unit/search', unitSearchDto);
    }
}