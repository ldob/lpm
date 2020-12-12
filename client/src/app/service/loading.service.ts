import { Injectable } from '@angular/core';
import {Overlay, OverlayRef} from "@angular/cdk/overlay";
import {ComponentPortal} from "@angular/cdk/portal";
import {LoadingOverlayComponent} from "../loading-overlay/loading-overlay.component";

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private counter: number = 0;
  private overlayRef: OverlayRef | null;

  constructor(private overlay: Overlay) {
    this.overlayRef = null;
  }

  public show(wait: number = 500): void {

    this.counter++;
    setTimeout(() => {

      if(this.counter === 1) {
        Promise.resolve(null).then(() => {
          this.overlayRef = this.overlay.create({
            positionStrategy: this.overlay
              .position()
              .global()
              .centerHorizontally()
              .centerVertically(),
            hasBackdrop: true,
          });

          this.overlayRef.attach(new ComponentPortal(LoadingOverlayComponent));
        });
      }
    }, wait);
  }

  public hide(): void {

    this.counter--;

    if (this.counter < 1 && this.overlayRef != null) {
      this.overlayRef.detach();
      this.overlayRef = null;
    }
  }
}
