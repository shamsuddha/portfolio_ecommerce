import { prop, propArray, } from '@rxweb/reactive-form-validators';
import { SearchDto } from './SearchDto';

export class BedSearchDto extends SearchDto {

  @propArray() idList: Array<String> = [];

  constructor(o?: Partial<BedSearchDto>) {
    super();
    Object.assign(this, o);
  }
}