import {Component, Input, OnInit} from '@angular/core';
import {TvshowSearchResult} from '../../model/tvshow-search-result';
import {SearchService} from '../../service/search.service';
import {Observable} from 'rxjs/Observable';
import {ActivatedRoute, ParamMap} from '@angular/router';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Component({
  selector: 'app-search-result-table',
  templateUrl: './search-result-table.component.html',
  styleUrls: ['./search-result-table.component.css']
})
export class SearchResultTableComponent implements OnInit {

  searchResultList: Observable<TvshowSearchResult[]>;

  constructor(private searchService: SearchService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.searchResultList = this.route.queryParamMap
      .map((paramMap: ParamMap) => paramMap.get('term'))
      .switchMap((term: string) => this.searchService.search(term));
  }
}
