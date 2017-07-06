import {Component, Input, OnInit} from '@angular/core';
import {Downloadlink} from '../../model/downloadlink';

@Component({
  selector: 'app-download-link',
  templateUrl: './download-link.component.html',
  styleUrls: ['./download-link.component.css']
})
export class DownloadLinkComponent implements OnInit {
  @Input() downloadLink: Downloadlink;

  constructor() {
  }

  ngOnInit() {
  }

}
