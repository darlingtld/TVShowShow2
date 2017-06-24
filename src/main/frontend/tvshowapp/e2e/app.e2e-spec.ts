import { TvshowappPage } from './app.po';

describe('tvshowapp App', () => {
  let page: TvshowappPage;

  beforeEach(() => {
    page = new TvshowappPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
