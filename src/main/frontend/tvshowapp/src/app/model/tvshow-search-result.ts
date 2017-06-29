export class TvshowSearchResult {
  name: string;
  description: string;
  englishName: string;
  tvSource: string;
  year: number;
  status: string;
  category: string;
  season: number;
  episode: number;
  detailUrl: string;
  imgUrl: string;

  constructor(name: string, description: string, englishName: string, tvSource: string, year: number, status: string,
              category: string, season: number, episode: number, detailUrl: string, imgUrl: string) {
    this.name = name;
    this.description = description;
    this.englishName = englishName;
    this.tvSource = tvSource;
    this.year = year;
    this.status = status;
    this.category = category;
    this.season = season;
    this.episode = episode;
    this.detailUrl = detailUrl;
    this.imgUrl = imgUrl;
  }
}
