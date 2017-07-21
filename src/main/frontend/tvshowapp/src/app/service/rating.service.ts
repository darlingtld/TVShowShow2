import {Injectable} from '@angular/core';
import {Http, URLSearchParams} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Rating} from '../model/rating';

@Injectable()
export class RatingService {

  private ratingUrl = 'rating';

  constructor(private http: Http) {
  }

  getTvshowDoubanRating(name: string, englishName: string): Promise<Rating> {
    const showRatingUrl = `${this.ratingUrl}/douban`;
    const search = new URLSearchParams();
    search.set('name', name);
    search.set('englishName', englishName);
    return this.http.get(showRatingUrl, {search}).toPromise()
      .then(response => response.json() as Rating)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
