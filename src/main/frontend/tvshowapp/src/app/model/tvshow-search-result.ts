import {SearchResult} from './search-result';

export class TvshowSearchResult extends SearchResult {
  tvSource: string;
  season: number;
  episode: number;

  constructor(name: string, description: string, englishName: string, tvSource: string, year: number, status: string,
              category: string, season: number, episode: number, detailUrl: string, imgUrl: string) {
    super(name, description, englishName, year, status, category, detailUrl, imgUrl);
    this.tvSource = tvSource;
    this.season = season;
    this.episode = episode;
  }
}
