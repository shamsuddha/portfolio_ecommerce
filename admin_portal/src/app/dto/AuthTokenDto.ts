import { prop, } from '@rxweb/reactive-form-validators';


export class AuthTokenDto  {

  @prop() accessToken: String | null = null;

  constructor(o?: Partial<AuthTokenDto>) {
    Object.assign(this, o);
  }
}