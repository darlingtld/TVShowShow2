import {Component, OnInit} from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import {ShowService} from '../../service/show.service';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private showService: ShowService) {
  }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      text: ''
    });
  }

  onSubmit(form: FormGroup) {
    console.log('Valid?', form.valid); // true or false
    console.log('Search Text', form.value.text);
    console.log(this.showService.getShows());
  }


}
