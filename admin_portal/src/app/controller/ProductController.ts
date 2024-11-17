import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from "../dto/Page";
import { Product } from "../entity/Product";
import { ProductSearchDto } from "../dto/ProductSearchDto";

@Injectable({ providedIn: 'root' })
export class ProductController {

    constructor(private httpClient: HttpClient) { }

    save(product: Product): Observable<Product> {
        
        return this.httpClient.post<Product>('http://localhost:9999/product/save', product);
    }

    update(product: Product): Observable<Product> {
        return this.httpClient.post<Product>('http://localhost:9999/product/update', product);
    }

    delete(product: Product): Observable<boolean> {
        return this.httpClient.post<boolean>('http://localhost:9999/product/delete', product)
    }

    search(productSearchDto: ProductSearchDto): Observable<Page<Product>> {
        return this.httpClient.post<Page<Product>>('http://localhost:9999/product/search', productSearchDto);
    }
}