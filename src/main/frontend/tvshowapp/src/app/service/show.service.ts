import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Tvshow} from '../model/tvshow';

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

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }


}
