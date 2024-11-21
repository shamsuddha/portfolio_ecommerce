import { HttpClient, HttpHeaders, HttpRequest } from "@angular/common/http";
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

    fileUpload(file: File): Observable<any> {
        const formData: FormData = new FormData();
        formData.append('file', file);
        formData.append('additionalInfo', 'additionalInfo');

        const headers = new HttpHeaders()
            .append('req_type', 'file_upload');

        const req = new HttpRequest('POST',
            'http://localhost:9999/file-upload/room-image-upload', formData,
            { headers, reportProgress: true, responseType: 'json' });
        return this.httpClient.request(req)
        //.pipe(catchError(this.formatErrors))
    }
}