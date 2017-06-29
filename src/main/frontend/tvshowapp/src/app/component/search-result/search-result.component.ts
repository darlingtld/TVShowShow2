import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {TvshowSearchResult} from '../../model/tvshow-search-result';
import {SearchService} from '../../service/search.service';
import {ActivatedRoute, Params} from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  term: string;
  tvshowSearchResultList: Observable<TvshowSearchResult[]>;

  constructor(private searchService: SearchService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    console.log('SearchResultComponent is initialized');
    this.tvshowSearchResultList = this.route.params
      .switchMap((params: Params) => {
        this.term = params['term'];
        console.log(this.term);
        return this.searchService.search(this.term);
      });
  }

}
