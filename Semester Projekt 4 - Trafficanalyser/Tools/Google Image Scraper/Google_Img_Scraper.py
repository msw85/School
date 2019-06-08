""" Simple scraper for google image search used to gather images for
a dataset used in a 4. semester CS project at UCN Aalborg. """

#Imports
import os
import sys
import logging
import time

import re
import argparse
import json
import itertools
import uuid

from urllib.request import Request, urlopen
from bs4 import BeautifulSoup

import selenium
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options

http_request_header = {'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36"}

def main():
    #Print TUI and take user input for request etc.
    print('')
    print('### Welcome to Google Image Scraper! ###')
    print('')
    print('Scraper Setup:')
    print('What do you need images of?')
    query = input('(For best result, be precise): ')

    script_dir = os.getcwd() #get dir script is run from
    save_to = '%s/%s/' % (script_dir, query) #add directory based on query

    print('How many do you need?')
    img_amount = int(input('(Google max is about 700): '))

    print('Any exact Width & Height?')
    img_size = input('(Ex. 1000x1000, or leave blank): ')

    start_scraping(query, save_to, img_amount, img_size)

def start_scraping(query, save_to, img_amount, img_size):
    query = '+'.join(query.split()) #format user query in case of more than one word
    print('')
    print('Fetching image links...')
    images = fetch_image_links(query, img_amount, img_size)
    print('')
    print('Downloading images from links...')
    download_and_save_imgs(images, save_to, img_amount)
    print('')
    print('Daddy, I\'m done!')
    print('')

def fetch_image_links(query, img_amount, img_size):
    #Google url is formatted
    url = 'https://www.google.com/search?q=%s&source=lnms&tbm=isch' % query
    if img_size:
        width, height = img_size.split('x')
        url = url + '&tbs=isz:ex,iszw:%s,iszh:%s' % (width, height)

    #Set up the webdriver with headless Chrome.
    print('')
    print('Setting up headless Chrome...')
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    browser = webdriver.Chrome(options=chrome_options)
    #Open url in browser
    browser.get(url)
    #Get everything within <html> tags
    html = browser.find_element_by_tag_name('html')

    """ Hacky solution incomming because of Google limitations on results pr. page.
    Sleeps are to avoid possible bot detection systems,
    ranges are wird because, hacky solution.
    This also makes for a long wait, but when experimenting with ranges no pattern for number of results were discovered """
    if (img_amount <= 100):
        print('')
        print("Please wait...")
        for i in range(50):
            html.send_keys(Keys.PAGE_DOWN)
            time.sleep(0.3)
    else:
        print('')
        print("Requested Image quantity above 100.")
        print("Fetching all html, please wait...")
        for i in range(500):
            html.send_keys(Keys.PAGE_DOWN)
            time.sleep(0.3)

        try:
            browser.find_element_by_id("smb").click()
            print("Clicked 'Show More Button'.")
            for i in range(500):
                html.send_keys(Keys.PAGE_DOWN)
                time.sleep(0.3)
        except Exception:
            for i in range(500):
                html.send_keys(Keys.PAGE_DOWN)
                time.sleep(0.3)

        print("Fetched all html...")
    
    #Save the whole html in a variable and close the headless Chrome.
    url_full_page = browser.page_source

    browser.close()

    print('')
    print('Making soup from html...')
    #Parse the html from google search through BS4.
    soup = BeautifulSoup(url_full_page, 'html.parser')

    print('')
    print('Picking image urls from the soup...')
    img_links = img_from_soup(soup)

    return itertools.islice(img_links, img_amount)

def img_from_soup(soup):
    #Extract tag objects matching the given argument
    img_elements = soup.find_all("div", {"class":"rg_meta"})
    #Deserialize from resultset, save in generator so that it can be used in next loop.
    metadata_dicts = (json.loads(e.text) for e in img_elements)
    #Get and store key,value pairs in a dict facion, iterating over metadata_dicts
    extracted_links = ((d["ou"], d["ity"]) for d in metadata_dicts)
    return extracted_links

def download_and_save_imgs(images, save_to, img_amount):
    #Go over all the image urls saved in images.
    for i, (url, image_type) in enumerate(images):
        try:
            #get image from url.
            print('HTML request #%d/%d: %s' % (i, img_amount, url))
            image = urlopen(Request(url, headers=http_request_header)).read()
            
            #Get extension in the image, if no type just make it jpg.
            file_ext = image_type if image_type else 'jpg'
            #Generate a random UUID and use it as filename .
            file_name = uuid.uuid4().hex + '.' + file_ext
            #Make a path from the script directory and filename
            full_path = os.path.join(save_to, file_name) #might be a relative path depending on user input.

            #If dir/file does not exist, create it.
            if not os.path.exists(os.path.dirname(full_path)):
                try:
                    os.makedirs(os.path.dirname(full_path))
                except Exception as e:
                    print(e)
            #Write the downloaded image to file, from stream
            with open(full_path, 'wb+') as img_file:
                img_file.write(image)
        except Exception as e:
            print(e)


if __name__ == '__main__':
    main()