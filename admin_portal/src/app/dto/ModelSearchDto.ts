import { prop, propArray } from '@rxweb/reactive-form-validators';
import { SearchDto } from './SearchDto';

export class ModelSearchDto extends SearchDto {

    @propArray() idList: Array<String> = [];
    @prop() name: string | null = null;

    constructor(o?: Partial<ModelSearchDto>) {
        super();
        Object.assign(this, o);
    }
}