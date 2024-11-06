export class DiscountType{

  readonly key: string;
  readonly value: string;

  constructor(key: string, value: string) {
      this.key = key;
      this.value = value;
  }

  public static Percentage = new DiscountType("Percentage", "Percentage");
  public static Consolidate = new DiscountType("Consolidate", "Consolidate");
 
  static getAllEnumList(): Array<DiscountType> {
    return [
      DiscountType.Percentage,
      DiscountType.Consolidate,
    ];
  }

}