import {Component, Input, OnInit} from '@angular/core';
import {TvshowSearchResult} from '../../model/tvshow-search-result';

@Component({
  selector: 'app-search-result-table',
  templateUrl: './search-result-table.component.html',
  styleUrls: ['./search-result-table.component.css']
})
export class SearchResultTableComponent implements OnInit {

  @Input() tvshowSearchResultList: TvshowSearchResult[];

  constructor() { }

  ngOnInit() {
  }

}
