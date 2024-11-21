import { prop, propArray } from "@rxweb/reactive-form-validators";
import { AuditLog } from "./AuditLog";

export class Category extends AuditLog {

  @prop() id: string | null = null;
  @prop() name: string | null = null;
  @prop() slug: string | null = null;
  @prop() image_location: string | null = null;

  constructor(o?: Partial<Category>) {
    super();
    Object.assign(this, o);
  }
}