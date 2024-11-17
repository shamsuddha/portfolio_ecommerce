import { prop, propArray } from "@rxweb/reactive-form-validators";
import { AuditLog } from "./AuditLog";

export class Category extends AuditLog {

  @prop() id: number | null = null;
  @prop() name: string | null = null;

  constructor(o?: Partial<Category>) {
    super();
    Object.assign(this, o);
  }
}