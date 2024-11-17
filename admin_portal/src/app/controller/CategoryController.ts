import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from "../dto/Page";
import { Category } from "../entity/Category";
import { CategorySearchDto } from "../dto/CategorySearchDto";

@Injectable({ providedIn: 'root' })
export class CategoryController {

    constructor(private httpClient: HttpClient) { }

    save(category: Category): Observable<Category> {
        
        return this.httpClient.post<Category>('http://localhost:9999/category/save', category);
    }

    update(category: Category): Observable<Category> {
        return this.httpClient.post<Category>('http://localhost:9999/category/update', category);
    }

    delete(category: Category): Observable<boolean> {
        return this.httpClient.post<boolean>('http://localhost:9999/category/delete', category)
    }

    search(categorySearchDto: CategorySearchDto): Observable<Page<Category>> {
        return this.httpClient.post<Page<Category>>('http://localhost:9999/category/search', categorySearchDto);
    }
}