/**
 * Created by lingda on 27/06/2017.
 */
export class Tvshow {
  id: number;
  name: string;
  englishName: string;
  season: number;
  episode: number;
  detailUrl: string;


  constructor(detailUrl: string) {
    this.detailUrl = detailUrl;
  }
}
