package com.guangbei.bosinone.webmagictest;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

/**
 * Created by xugang on 2017/3/1.
 */
public class MonitorExample {
    public static void main(String[] args) throws Exception {

        Spider githubSpider = Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft");

        SpiderMonitor.instance().register(githubSpider);
        githubSpider.start();
    }
}
