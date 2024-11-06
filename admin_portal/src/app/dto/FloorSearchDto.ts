import { prop, propArray, } from '@rxweb/reactive-form-validators';
import { SearchDto } from './SearchDto';

export class FloorSearchDto extends SearchDto {

  @propArray() idList: Array<String> = [];

  constructor(o?: Partial<FloorSearchDto>) {
    super();
    Object.assign(this, o);
  }
}