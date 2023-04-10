import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {ThermostatDto} from "./thermostat-dto.model";
@Injectable({
  providedIn: 'root'
})
export class ThermostatService {
  private apiUrl = 'http://localhost:8080/api/v1/thermostats';

  constructor(private http: HttpClient) { }

  getThermostats(): Observable<ThermostatDto[]> {
    return this.http.get<ThermostatDto[]>(this.apiUrl);
  }
}
