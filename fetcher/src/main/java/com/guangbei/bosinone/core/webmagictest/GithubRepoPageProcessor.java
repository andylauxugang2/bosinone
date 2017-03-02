package com.guangbei.bosinone.core.webmagictest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * write our very first crawler
 * take Github
 * Created by xugang on 2017/3/1.
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(GithubRepoPageProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        //This code uses a regular expression that matches all "https://github.com/code4craft/webmagic" such a link.
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());

        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());

        //<h1 class='entry-title public'><strong><a href='#'>webmagic ...
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());

        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        logger.info("test");
        Spider.create(new GithubRepoPageProcessor())
                // From "https://github.com/code4craft" began to grasp
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("/Users/xugang/webmagic"))
                        // Open 5 threads of Crawl
                .thread(5)
                        // Start Crawl
                .run();
    }
}
