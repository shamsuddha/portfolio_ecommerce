import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from "../dto/Page";

@Injectable({ providedIn: 'root' })
export class SubCategoryController {

    constructor(private httpClient: HttpClient) { }

    save(subCategory: SubCategory): Observable<SubCategory> {
        
        return this.httpClient.post<SubCategory>('http://localhost:9999/sub-category/save', subCategory);
    }

    update(subCategory: SubCategory): Observable<SubCategory> {
        return this.httpClient.post<SubCategory>('http://localhost:9999/sub-category/update', subCategory);
    }

    delete(subCategory: SubCategory): Observable<boolean> {
        return this.httpClient.post<boolean>('http://localhost:9999/sub-category/delete', subCategory)
    }

    search(subCategorySearchDto: SubCategorySearchDto): Observable<Page<SubCategory>> {
        return this.httpClient.post<Page<SubCategory>>('http://localhost:9999/sub-category/search', subCategorySearchDto);
    }
}