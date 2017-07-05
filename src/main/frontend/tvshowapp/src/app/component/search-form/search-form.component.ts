import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      text: ['', [Validators.required, Validators.minLength(1)]],
    });
    this.route.params.subscribe(params => {
      if (params['term']) {
        this.searchForm.setValue({text: params['term']});
      }
    });
  }

  onSubmit(form: FormGroup) {
    this.gotoSearchResult(form.value.text);
  }

  gotoSearchResult(term: string) {
    const link = ['/search', {term: term}];
    this.router.navigate(link);
  }
}
