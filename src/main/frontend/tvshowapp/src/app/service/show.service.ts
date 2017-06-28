import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {TVShow} from '../model/TVShow';

@Injectable()
export class ShowService {
  private showUrl = 'show';

  constructor(private http: Http) {
  }

  getShows(): Promise<TVShow[]> {
    return this.http.get(this.showUrl).toPromise()
      .then(response => response.json().data as TVShow[])
      .catch(this.handleError);
  }

  search(term: string): Promise<TVShow[]> {
    return this.http.post(this.showUrl, {}).toPromise()
      .then(response => response.json().data as TVShow[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
