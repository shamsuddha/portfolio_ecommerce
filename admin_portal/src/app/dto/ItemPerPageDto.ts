import { prop, propArray } from '@rxweb/reactive-form-validators';
import { SearchDto } from './SearchDto';

export class ItemPerPageDto {

    @prop() size: number | null = null;

    constructor(o?: Partial<ItemPerPageDto>) {
        Object.assign(this, o);
    }
}