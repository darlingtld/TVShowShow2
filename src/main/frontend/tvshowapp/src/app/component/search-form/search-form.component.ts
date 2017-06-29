import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder} from '@angular/forms';
import {SearchService} from '../../service/search.service';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private searchService: SearchService) {
  }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      text: ''
    });
  }

  onSubmit(form: FormGroup) {
    console.log('Valid?', form.valid); // true or false
    console.log('Search Text', form.value.text);
    console.log(this.searchService.search(form.value.text));
  }


}
