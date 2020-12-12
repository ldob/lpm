import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private counter: number = 0;

  constructor() { }

  show(wait: number = 1000) {
    this.counter++;
    setTimeout(() => {

    }, wait);
  }

  hide() {
    this.counter--;
    if(this.counter < 1) {

    }
}
