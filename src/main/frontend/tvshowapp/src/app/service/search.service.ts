import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {SearchResult} from '../model/search-result';

@Injectable()
export class SearchService {
  private searchUrl: string;

  constructor(private http: Http) {
    this.searchUrl = 'search';
  }

  search(category: string, term: string): Observable<SearchResult[]> {
    return this.http.post(`${this.searchUrl}/${category}`, {term: term})
      .map(response => response.json() as SearchResult[])
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
