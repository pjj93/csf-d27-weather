import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Weather } from '../models';
import { OpenweathermapService } from '../openweathermap.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  weather!: Weather
  city = ''
  fields = ''

  constructor(private weatherService: OpenweathermapService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.city = this.activatedRoute.snapshot.params['city']
    this.fields = this.activatedRoute.snapshot.queryParams['fields']
    console.info(this.city)
    this.weatherService.getWeather(this.city)
      .then(w => this.weather = w)
  }


}
