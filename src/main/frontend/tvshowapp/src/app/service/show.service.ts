import {Injectable} from '@angular/core';
import {Http, URLSearchParams} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {Tvshow} from '../model/tvshow';
import {Observable} from 'rxjs/Observable';
import {Downloadlink} from '../model/downloadlink';
import {TvshowSearchResult} from '../model/tvshow-search-result';

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
    const search = new URLSearchParams();
    search.set('detailUrl', tvshow.detailUrl);
    return this.http.get(downloadLinkUrl, {search})
      .map(response => response.json() as Downloadlink[])
  }

  getTvshowSearchResultByDetailUrl(detailUrl: string): Promise<TvshowSearchResult> {
    const showDetailUrl = `${this.showUrl}/tvshow`;
    const search = new URLSearchParams();
    search.set('detailUrl', detailUrl);
    return this.http.get(showDetailUrl, {search}).toPromise()
      .then(response => response.json() as TvshowSearchResult)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }


}
