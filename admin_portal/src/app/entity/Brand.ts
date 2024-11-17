import { prop, } from '@rxweb/reactive-form-validators';
import { AuditLog } from "./AuditLog";

export class Brand extends AuditLog {

  @prop() id: string | null = null;
  
  @prop() name: string | null = null;
  

  constructor(o?: Partial<Brand>) {
    super();
    Object.assign(this, o);
  }
}
