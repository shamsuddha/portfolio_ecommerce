import { prop } from "@rxweb/reactive-form-validators";
import { AuditLog } from "./AuditLog";

export class Unit extends AuditLog {

	@prop() id: number | null = null;
	@prop() name: string | null = null;
	@prop() code: string | null = null;

	//  // @Many to one
	//  @prop() floor: Floor | null = null;
	//  @prop() floorId: number | null = null;

	//  //@One to many 
	//  @propArray(Room, {createBlank: false})
	//  roomList: Array<Room> = [];

	constructor(o?: Partial<Unit>) {
		super();
		Object.assign(this, o);
	}
}