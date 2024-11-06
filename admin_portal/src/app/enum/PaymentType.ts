export class PaymentType{

  readonly key: string;
  readonly value: string;

  constructor(key: string, value: string) {
      this.key = key;
      this.value = value;
  }

  public static Advance = new PaymentType("Advance", "Advance");
  public static Paid = new PaymentType("Paid", "Paid");
  public static Return = new PaymentType("Return", "Return");
 
  static getAllEnumList(): Array<PaymentType> {
    return [
      PaymentType.Advance,
      PaymentType.Paid,
      PaymentType.Return,
    ];
  }
}