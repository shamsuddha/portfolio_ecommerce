import { prop }
  from '@rxweb/reactive-form-validators';

export class LoginInfoDto {

  @prop() username: string | null = null;
  @prop() password: string | null = null;

  constructor(o?: Partial<LoginInfoDto>) {
    Object.assign(this, o);
  }
}
