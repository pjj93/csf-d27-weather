import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OpenweathermapService {

  url = "http://localhost:8080/api/weather/"
  constructor(private http: HttpClient) { }

  getWeather(city:string) {
    this.url = this.url + city
    return this.http.get(this.url)
  }
}
