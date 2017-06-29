import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder} from '@angular/forms';
import {SearchService} from '../../service/search.service';
import {TvshowSearchResult} from '../../model/tvshow-search-result';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  searchForm: FormGroup;
  isLoading: boolean;
  tvshowSearchResultList: TvshowSearchResult[];

  constructor(private formBuilder: FormBuilder, private searchService: SearchService) {
    this.isLoading = false;
  }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      text: ''
    });
  }

  onSubmit(form: FormGroup) {
    this.isLoading = true;
    console.log('Valid?', form.valid); // true or false
    console.log('Search Text', form.value.text);
    // this.tvshowSearchResultList = this.searchService.search(form.value.text);
    this.searchService.search(form.value.text).then((result) => {
      this.tvshowSearchResultList = result;
      console.log(this.tvshowSearchResultList);
      this.isLoading = false;
    });
  }
}
