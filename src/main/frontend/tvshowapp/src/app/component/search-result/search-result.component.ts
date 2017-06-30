import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {TvshowSearchResult} from '../../model/tvshow-search-result';
import {SearchService} from '../../service/search.service';
import {ActivatedRoute, Params} from '@angular/router';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/do';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  isLoading: boolean;
  term: string;
  tvshowSearchResultList: Observable<TvshowSearchResult[]>;

  constructor(private searchService: SearchService, private route: ActivatedRoute) {
    this.isLoading = false;
  }

  ngOnInit() {
    this.isLoading = true;
    this.tvshowSearchResultList = this.route.params
      .do(() => this.isLoading = true)
      .switchMap((params: Params) => {
        this.term = params['term'];
        return this.searchService.search(this.term);
      })
      .do(() => this.isLoading = false);
  }

}
