import { TesteUiPage } from './app.po';

describe('teste-ui App', () => {
  let page: TesteUiPage;

  beforeEach(() => {
    page = new TesteUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
