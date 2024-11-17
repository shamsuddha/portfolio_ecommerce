import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from "../dto/Page";
import { Model } from "../entity/Model";
import { ModelSearchDto } from "../dto/ModelSearchDto";

@Injectable({ providedIn: 'root' })
export class ModelController {

    constructor(private httpClient: HttpClient) { }

    save(model: Model): Observable<Model> {

        return this.httpClient.post<Model>('http://localhost:9999/model/save', model);
    }

    update(model: Model): Observable<Model> {
        return this.httpClient.post<Model>('http://localhost:9999/model/update', model);
    }

    delete(model: Model): Observable<boolean> {
        return this.httpClient.post<boolean>('http://localhost:9999/model/delete', model)
    }

    search(modelSearchDto: ModelSearchDto): Observable<Page<Model>> {
        return this.httpClient.post<Page<Model>>('http://localhost:9999/model/search', modelSearchDto);
    }
}