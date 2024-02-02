import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Input} from "./input";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private apiServerUrl = 'http://localhost:8080';
  constructor(private http:HttpClient) { }

  public getInputs():Observable<Input[]>{
    return this.http.get<Input[]>(`${this.apiServerUrl}/input/all`);
  }

  public addInput(input: Input):Observable<Input>{
    return this.http.post<Input>(`${this.apiServerUrl}/input/add`, input);
  }

  public calculateEmi(input: Input):Observable<number>{
    return this.http.post<number>(`${this.apiServerUrl}/input/calculate-emi`,input );
  }
}
