import { SearchDto } from "./SearchDto";

export class EmployeeSearchDto extends SearchDto {

  idList: Array<number> = [];

  constructor(o?: Partial<EmployeeSearchDto>) {
    super();
    Object.assign(this, o);
  }
}
