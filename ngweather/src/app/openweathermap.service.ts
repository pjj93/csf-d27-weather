import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Weather } from './models';

@Injectable({
  providedIn: 'root'
})
export class OpenweathermapService {

  constructor(private http: HttpClient) { }

  getWeather(city:string): Promise<Weather> {
    return lastValueFrom(this.http.get<Weather>('/api/weather/' + city))
  }
}
