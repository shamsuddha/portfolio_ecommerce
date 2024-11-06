import { Observable, of } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Employee } from "../entity/Employee";
import { EmployeeSearchDto } from "../dto/EmployeeSearchDto";


@Injectable({ providedIn: 'root' })
export class EmployeeController {

  employeeList: Array<Employee> = [];

  constructor(private httpClient: HttpClient,) {
  }

  save(employee: Employee): Observable<Employee> {
    //return this.httpClient.post<Employee>('http://localhost:8080/employee/save', employee);
    employee.id = this.employeeList.length;
    this.employeeList = [...this.employeeList, employee]
    return of(employee);
  }

  update(employee: Employee): Observable<Employee> {
    return this.httpClient.put<Employee>('http://localhost:8080/employee/update', employee);
  }

  delete(employee: Employee): Observable<boolean> {
    //console.log(employee);
    return this.httpClient.delete<boolean>('http://localhost:8080/employee/delete', { body: employee })
    //.subscribe((e)=>{ });
  }

  search(employeeSearchDto: EmployeeSearchDto): Observable<Array<Employee>> {
    return this.httpClient.post<Array<Employee>>('http://localhost:8080/employee/search', employeeSearchDto);
  }

}
