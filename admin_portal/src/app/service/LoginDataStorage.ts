import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class LoginDataStorage {

  constructor() { }

  setItem(key: string, data: String): void {
    localStorage.setItem(key, JSON.stringify(data));
  }

  // getItem(key: string): UserInfo | null {
  //   const item: string | null = localStorage.getItem(key);
  //   return item ? new UserInfo(JSON.parse(item)) : null;
  // }

  // removeItem(key: string): void {
  //   localStorage.removeItem(key);
  // }

  // clear(): void {
  //   localStorage.clear();
  // }

}
