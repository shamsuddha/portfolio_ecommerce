import { prop, } from '@rxweb/reactive-form-validators';
import { AuditLog } from "./AuditLog";

export class SubCategory extends AuditLog {

  @prop() id: string | null = null;
  
  @prop() name: string | null = null;
  
  constructor(o?: Partial<SubCategory>) {
    super();
    Object.assign(this, o);
  }
}
