import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  cities = ["Singapore", "Tokyo", "London", "Canberra"]

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onClick(city: string) {
    this.router.navigate(['/weather', city])
  }
}
