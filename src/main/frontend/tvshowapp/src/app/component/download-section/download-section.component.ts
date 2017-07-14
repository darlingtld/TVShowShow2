import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Downloadlink} from '../../model/downloadlink';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {ShowService} from '../../service/show.service';
import {Tvshow} from '../../model/tvshow';
import {TvshowSearchResult} from '../../model/tvshow-search-result';

@Component({
  selector: 'app-download-section',
  templateUrl: './download-section.component.html',
  styleUrls: ['./download-section.component.css']
})
export class DownloadSectionComponent implements OnInit {

  tvshowSearchResult: TvshowSearchResult;
  downloadlinkList: Observable<Downloadlink[]>;
  detailUrl: string;
  isLoading: boolean;

  constructor(private showService: ShowService, private route: ActivatedRoute) {
    this.isLoading = true;
  }

  ngOnInit() {
    this.downloadlinkList = this.route.queryParamMap
      .do(() => this.isLoading = true)
      .map((paramMap: ParamMap) => {
        this.detailUrl = paramMap.get('detailUrl');
        return new Tvshow(this.detailUrl);
      })
      .switchMap((tvshow: Tvshow) => this.showService.getDownloadLinks(tvshow))
      .do(() => this.isLoading = false);

    this.route.queryParamMap
      .map((paramMap: ParamMap) => paramMap.get('detailUrl'))
      .switchMap((detailUrl: string) => this.showService.getTvshowSearchResultByDetailUrl(detailUrl))
      .subscribe((result: TvshowSearchResult) => this.tvshowSearchResult = result);
  }

}
