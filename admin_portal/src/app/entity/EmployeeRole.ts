import {prop,} from '@rxweb/reactive-form-validators';
import {AuditLog} from "./AuditLog";

export class EmployeeRole extends AuditLog {

  @prop() id: number | null = null;
  @prop() roleId: string | null = null;
  @prop() roleName: string | null = null;
  @prop() employeeId: string | null = null;
  @prop() employeeName: string | null = null;

  constructor(o?: Partial<EmployeeRole>) {
    Object.assign(this, o);
  }
}


