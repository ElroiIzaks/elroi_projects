__author__ = "Elroi Izaks"
__email__ = "elroiizaks@gmail.com"
__credits__ = "Yehonatan Jacob"
__version__ = "2.0"

from bs4 import BeautifulSoup
import requests
import json
import argparse
from tool_bar import toolBar

WIKI_URL = "https://en.wikipedia.org"
NUM_OF_PAGES_TO_SCRAPE = 3


def is_disambiguation_page(page: requests.get) -> bool:
    if BeautifulSoup(page.text, 'html.parser').find(
            class_="metadata plainlinks dmbox dmbox-disambig"):
        return True
    return False


def get_page_name(page: requests.get) -> str:
    name = BeautifulSoup(page.text, 'html.parser').find(
        class_="firstHeading mw-first-heading").text
    return name


def get_page_summary(page: requests.get) -> str:
    summary = (BeautifulSoup(page.text, 'html.parser').find(
        class_="mw-body-content mw-content-ltr")).find(
        class_="mw-parser-output").find_all(
        'p')
    return next((i.text for i in summary if len(i.text) > 150),
                "A non cllasical wiki page. No summary is avelible")


if __name__ == '__main__':

    jsonFile = open("data.json", "w")
    results = []

    parser = argparse.ArgumentParser()
    parser.add_argument(
        '--word', '-w', help="word to search in wiki", required=True)
    args = parser.parse_args()

    url = WIKI_URL+"/w/index.php?search="+args.word + \
        "&title=Special:Search&profile=advanced&fulltext=1&ns0=1"
    searching_page = requests.get(url)

    try:
        soup = BeautifulSoup(searching_page.text, 'html.parser').find(
            class_="mw-search-results-container")
        items = soup.find_all(
            "div", attrs={"class": "mw-search-result-heading"})
    except:
        raise Exception("no result for searching \"", args.word, "\"")

    toolbar = toolBar(NUM_OF_PAGES_TO_SCRAPE * 20)
    for i in range(NUM_OF_PAGES_TO_SCRAPE):
        for item in items:

            url = WIKI_URL + (item.find('a')).get('href')
            page = requests.get(url)

            if is_disambiguation_page(page):
                continue

            result = {
                "name": get_page_name(page),
                "description": get_page_summary(page)
            }
            results.append(result)
            toolbar.progerss()

        # go to the next page
        try:
            searching_page = requests.get(WIKI_URL + (BeautifulSoup(
                searching_page.text, 'html.parser').find(
                class_="mw-nextlink")).get(
                'href'))
            soup = BeautifulSoup(searching_page.text, 'html.parser').find(
                class_="mw-search-results-container")
            items = soup.find_all(
                "div", attrs={"class": "mw-search-result-heading"})
        except:
            break

    toolbar.complete_tool_bar()
    jsonString = json.dumps(results, indent=4)
    jsonFile.write(jsonString)
    jsonFile.close()
