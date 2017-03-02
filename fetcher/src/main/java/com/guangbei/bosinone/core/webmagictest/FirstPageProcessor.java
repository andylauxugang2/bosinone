package com.guangbei.bosinone.core.webmagictest;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by xugang on 2017/3/1.
 */
public class FirstPageProcessor implements PageProcessor {
    // 抓取网站相关配置
    private Site site = Site.me().setTimeOut(10000).setRetryTimes(3).setSleepTime(1000).setCharset("UTF-8");

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 核心抽取逻辑
     */
    @Override
    public void process(Page page) {
        System.out.println("Requesting: " + page.getUrl());
        List<String> list = page.getHtml().xpath("//article[@class='well clearfix']").all();
        if (list.size() <= 0) {
            // 忽略这个页面
            page.setSkip(true);
        }

        for (String header : list) {
            Html tmp = Html.create(header);
//            System.out.println(tmp);
            System.out.println(tmp.xpath("//h1[@class='entry-title']/a/text()").toString().trim());
            System.out.println(tmp.xpath("//span[@class='fa fa-user']/a/text()").toString());
            System.out.println(tmp.xpath("//h1[@class='entry-title']/a/@href").toString());
            System.out.println(tmp.xpath("//div[@class='pull-left footer-tag']/a/text()").all().toString());
            System.out.println("------------------------------");
        }

        //从页面发现后续的url地址来抓取
//        page.addTargetRequests(page.getHtml().links().regex("(https://www.zifangsky.cn/page/\\d*)").all());

    }

    public static void main(String[] args) {
        Spider.create(new FirstPageProcessor())
                .addUrl("https://www.zifangsky.cn")
                .thread(5)
                .run();
    }
}
