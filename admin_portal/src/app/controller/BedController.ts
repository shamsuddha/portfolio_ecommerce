import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from "../dto/Page";
import { Bed } from "../entity/Bed";
import { BedSearchDto } from "../dto/BedSearchDto";

@Injectable({ providedIn: 'root' })
export class BedController {

  constructor(private httpClient: HttpClient) { }

  save(bed: Bed): Observable<Bed> {
    return this.httpClient.post<Bed>('http://localhost:9999/bed/save', bed);
  }

  update(bed: Bed): Observable<Bed> {
    return this.httpClient.post<Bed>('http://localhost:9999/bed/update', bed);
  }

  delete(bed: Bed): Observable<boolean> {
    return this.httpClient.post<boolean>('http://localhost:9999/bed/delete', bed)
  }

  search(bedSearchDto: BedSearchDto): Observable<Page<Bed>> {
    return this.httpClient.post<Page<Bed>>('http://localhost:9999/bed/search', bedSearchDto);
  }
}