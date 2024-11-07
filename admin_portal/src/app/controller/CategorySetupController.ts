import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class CategorySetupController {
    constructor(private httpClient: HttpClient) {

        save(floor: Category): Observable < Category > {
            return this.httpClient.post<Category>('http://localhost:9999/floor/save', floor);
        }

        update(floor: Category): Observable < Category > {
            return this.httpClient.post<Category>('http://localhost:9999/floor/update', floor);
        }

        delete (floor: Category): Observable < boolean > {
            return this.httpClient.post<boolean>('http://localhost:9999/floor/delete', floor)
        }

        search(floorSearchDto: CategorySearchDto): Observable < Page < Category >> {
            return this.httpClient.post<Page<Category>>('http://localhost:9999/floor/search', floorSearchDto);
        }


    }

}