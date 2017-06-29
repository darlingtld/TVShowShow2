import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {TvshowSearchResult} from '../model/tvshow-search-result';

@Injectable()
export class SearchService {
  private searchUrl: string;

  constructor(private http: Http) {
    this.searchUrl = 'search';
  }

  search(term: string): Promise<TvshowSearchResult[]> {
    return this.http.post(this.searchUrl, {term: term}).toPromise()
      .then(response => response.json() as TvshowSearchResult[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
