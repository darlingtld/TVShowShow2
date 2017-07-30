export class SearchResult {
  name: string;
  description: string;
  englishName: string;
  year: number;
  status: string;
  category: string;
  detailUrl: string;
  imgUrl: string;

  constructor(name: string, description: string, englishName: string, year: number, status: string,
              category: string, detailUrl: string, imgUrl: string) {
    this.name = name;
    this.description = description;
    this.englishName = englishName;
    this.year = year;
    this.status = status;
    this.category = category;
    this.detailUrl = detailUrl;
    this.imgUrl = imgUrl;
  }
}
