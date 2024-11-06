import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Floor } from "../entity/Floor";
import { FloorSearchDto } from "../dto/FloorSearchDto";
import { Page } from "../dto/Page";

@Injectable({ providedIn: 'root' })
export class FloorController {

  constructor(private httpClient: HttpClient) { }

  save(floor: Floor): Observable<Floor> {
    return this.httpClient.post<Floor>('http://localhost:9999/floor/save', floor);
  }

  update(floor: Floor): Observable<Floor> {
    return this.httpClient.post<Floor>('http://localhost:9999/floor/update', floor);
  }

  delete(floor: Floor): Observable<boolean> {
    return this.httpClient.post<boolean>('http://localhost:9999/floor/delete', floor)
  }

  search(floorSearchDto: FloorSearchDto): Observable<Page<Floor>> {
    return this.httpClient.post<Page<Floor>>('http://localhost:9999/floor/search', floorSearchDto);
  }
  
}