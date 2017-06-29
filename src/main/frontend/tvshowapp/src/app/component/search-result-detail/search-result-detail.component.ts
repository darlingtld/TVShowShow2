import {Component, OnInit} from '@angular/core';
import {Tvshow} from '../../model/tvshow';

@Component({
  selector: 'app-search-result-detail',
  templateUrl: './search-result-detail.component.html',
  styleUrls: ['./search-result-detail.component.css']
})
export class SearchResultDetailComponent implements OnInit {

  private tvshow: Tvshow;

  constructor() {
  }

  ngOnInit() {
  }

}
