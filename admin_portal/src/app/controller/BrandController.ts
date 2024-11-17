import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from "../dto/Page";
import { Brand } from "../entity/Brand";
import { BrandSearchDto } from "../dto/BrandSearchDto";

@Injectable({ providedIn: 'root' })
export class BrandController {

    constructor(private httpClient: HttpClient) { }

    save(brand: Brand): Observable<Brand> {
        
        return this.httpClient.post<Brand>('http://localhost:9999/brand/save', brand);
    }

    update(brand: Brand): Observable<Brand> {
        return this.httpClient.post<Brand>('http://localhost:9999/brand/update', brand);
    }

    delete(brand: Brand): Observable<boolean> {
        return this.httpClient.post<boolean>('http://localhost:9999/brand/delete', brand)
    }

    search(brandSearchDto: BrandSearchDto): Observable<Page<Brand>> {
        return this.httpClient.post<Page<Brand>>('http://localhost:9999/brand/search', brandSearchDto);
    }
}