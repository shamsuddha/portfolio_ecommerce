import { prop, } from '@rxweb/reactive-form-validators';
import { AuditLog } from "./AuditLog";

export class Bed extends AuditLog {

  @prop() id: string | null = null;
  @prop() name: string | null = null;

  constructor(o?: Partial<Bed>) {
    super();
    Object.assign(this, o);
  }
}
