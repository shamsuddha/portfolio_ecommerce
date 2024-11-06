import {prop,} from '@rxweb/reactive-form-validators';
import {AuditLog} from "./AuditLog";


export class Role extends AuditLog{

  @prop() id: string | null = null;
  @prop() name: string | null = null;

  constructor(o?: Partial<Role>) {
    super();
    Object.assign(this, o);
  }
}
