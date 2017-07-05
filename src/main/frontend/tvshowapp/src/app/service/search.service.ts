import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {TvshowSearchResult} from '../model/tvshow-search-result';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class SearchService {
  private searchUrl: string;

  constructor(private http: Http) {
    this.searchUrl = 'search';
  }

  search(term: string): Observable<TvshowSearchResult[]> {
    return this.http.post(this.searchUrl, {term: term})
      .map(response => response.json() as TvshowSearchResult[])
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
