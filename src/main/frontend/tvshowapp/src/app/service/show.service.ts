import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Tvshow} from '../model/tvshow';
import {Observable} from "rxjs/Observable";
import {Downloadlink} from "../model/downloadlink";

@Injectable()
export class ShowService {
  private showUrl = 'show';

  constructor(private http: Http) {
  }

  getShows(): Promise<Tvshow[]> {
    return this.http.get(this.showUrl).toPromise()
      .then(response => response.json().data as Tvshow[])
      .catch(this.handleError);
  }

  getDownloadLinks(tvshow: Tvshow): Observable<Downloadlink[]> {
    const downloadLinkUrl = `${this.showUrl}/downloadlinks`;
    return this.http.post(downloadLinkUrl, {...tvshow})
      .map(response => response.json() as Downloadlink[])
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }


}
