import {Component, Input, OnInit} from '@angular/core';
import {TvshowSearchResult} from '../../model/tvshow-search-result';
import {Rating} from '../../model/rating';
import {RatingService} from '../../service/rating.service';

@Component({
  selector: 'app-search-result-detail',
  templateUrl: './search-result-detail.component.html',
  styleUrls: ['./search-result-detail.component.css']
})
export class SearchResultDetailComponent implements OnInit {

  @Input() tvshowSearchResult: TvshowSearchResult;
  rating: Rating;

  constructor(private ratingService: RatingService) {
  }

  ngOnInit() {
    this.ratingService.getTvshowDoubanRating(this.tvshowSearchResult.name, this.tvshowSearchResult.englishName)
      .then((rating: any) => this.rating = rating);
  }

}
