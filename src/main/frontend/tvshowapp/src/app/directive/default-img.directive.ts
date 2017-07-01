import {Directive, ElementRef, HostListener, Input} from '@angular/core';


@Directive({
  selector: '[appDefaultImg]'
})
export class DefaultImgDirective {

  @Input('appDefaultImg') defaultImgSrc: string;

  constructor(private el: ElementRef) {
  }

  @HostListener('error') onLoadImgError() {
    this.useDefaultImg(this.defaultImgSrc);
  }

  private useDefaultImg(defaultImgSrc: string) {
    this.el.nativeElement.src = 'assets/' + defaultImgSrc;
  }

}
