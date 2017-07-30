import {Component, Input, OnInit} from '@angular/core';
import {Rating} from '../../model/rating';
import {RatingService} from '../../service/rating.service';
import {SearchResult} from '../../model/search-result';

@Component({
  selector: 'app-search-result-detail',
  templateUrl: './search-result-detail.component.html',
  styleUrls: ['./search-result-detail.component.css']
})
export class SearchResultDetailComponent implements OnInit {

  @Input() showSearchResult: SearchResult;
  rating: Rating;

  constructor(private ratingService: RatingService) {
  }

  ngOnInit() {
    this.ratingService.getTvshowDoubanRating(this.showSearchResult.name, this.showSearchResult.englishName)
      .then((rating: any) => this.rating = rating);
  }

}
