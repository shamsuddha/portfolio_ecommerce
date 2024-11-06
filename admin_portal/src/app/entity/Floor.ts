import {prop, propArray,} from '@rxweb/reactive-form-validators';
import {AuditLog} from "./AuditLog";



export class Floor extends AuditLog{

  @prop() id: string | null = null;
  @prop() name: string | null = null;

  // @propArray(Room, {createBlank: false})
  // roomList: Array<Room> = [];

  constructor(o?: Partial<Floor>) {
    super();
    Object.assign(this, o);
  }
}
