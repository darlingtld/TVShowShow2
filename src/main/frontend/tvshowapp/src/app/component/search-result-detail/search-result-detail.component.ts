import {Component, OnInit} from '@angular/core';
import {TVShow} from '../../model/TVShow';

@Component({
  selector: 'app-search-result-detail',
  templateUrl: './search-result-detail.component.html',
  styleUrls: ['./search-result-detail.component.css']
})
export class SearchResultDetailComponent implements OnInit {

  private tvshow: TVShow;

  constructor() {
  }

  ngOnInit() {
  }

}
