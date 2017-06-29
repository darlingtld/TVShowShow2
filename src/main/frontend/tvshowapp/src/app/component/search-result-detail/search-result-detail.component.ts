import {Component, Input, OnInit} from '@angular/core';
import {TvshowSearchResult} from '../../model/tvshow-search-result';

@Component({
  selector: 'app-search-result-detail',
  templateUrl: './search-result-detail.component.html',
  styleUrls: ['./search-result-detail.component.css']
})
export class SearchResultDetailComponent implements OnInit {

  @Input() tvshowSearchResult: TvshowSearchResult;

  constructor() {
  }

  ngOnInit() {
  }

}
