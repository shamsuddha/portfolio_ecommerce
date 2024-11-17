import { prop, propArray } from '@rxweb/reactive-form-validators';
import { SearchDto } from './SearchDto';

export class CategorySearchDto extends SearchDto {

    @propArray() idList: Array<String> = [];
    @prop() name: string | null = null;

    constructor(o?: Partial<CategorySearchDto>) {
        super();
        Object.assign(this, o);
    }
}