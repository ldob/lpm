import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  code: number | undefined;
  message: string;

  mapping: Map<number, string> = new Map<number, string>();

  constructor(private activatedRoute: ActivatedRoute) {
    this.message = "???";

    this.mapping.set(400, 'Element nicht gefunden');
    this.mapping.set(401, 'Zugriff verweigert');
    this.mapping.set(403, 'Zugriff verweigert');
    this.mapping.set(404, 'Element nicht gefunden');
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => {
        this.code = parseInt(params['code'], 10);
        if(this.code && this.mapping.has(this.code)) {
          //TODO
          // @ts-ignore
          this.message = this.mapping.get(this.code);
        }
        else {
          this.message = 'Unbekannter Fehler: ' + this.code;
        }

      }
    );
  }

}
