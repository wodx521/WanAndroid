package com.wanou.wanandroid.bean;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/20.
 */
public class TabListBean {

    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"yangmingchuan","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"根据 http://www.wanandroid.com 提供api ，编写 包含 Material Design + MVP + Rxjava2 + Retrofit + Glide项目","envelopePic":"http://www.wanandroid.com/blogimgs/9844d308-4e98-45c9-8ca3-284bd2c9eaab.png","fresh":false,"id":7494,"link":"http://www.wanandroid.com/blog/show/2417","niceDate":"2018-11-10","origin":"","projectLink":"https://github.com/yangmingchuan/WanAndroidMaster","publishTime":1541843371000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"WanAndroid  该项目主要学习 mvp 和 rx+ retrofit 一套","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"rcj60560","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"使用大大提供的api自己写的练手 ：)","envelopePic":"http://wanandroid.com/blogimgs/a490b5ed-4118-4e6b-997f-1d892ce610fc.png","fresh":false,"id":7473,"link":"http://www.wanandroid.com/blog/show/2416","niceDate":"2018-11-06","origin":"","projectLink":"https://github.com/rcj60560/wanandroid","publishTime":1541507673000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"wanandroid 练手开源项目","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"MRYangY","chapterId":401,"chapterName":"二维码","collect":false,"courseId":13,"desc":"一款仿微信扫一扫界面，基于zxing实现的扫码库。","envelopePic":"http://wanandroid.com/blogimgs/0554900d-e606-47e3-bd37-3558f18523e8.png","fresh":false,"id":7472,"link":"http://www.wanandroid.com/blog/show/2415","niceDate":"2018-11-06","origin":"","projectLink":"https://github.com/MRYangY/YZxing","publishTime":1541507591000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=401"}],"title":"Android 基于Zxing的扫码功能实现","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Tomzem","chapterId":358,"chapterName":"项目基础功能","collect":false,"courseId":13,"desc":"Android多级选择控件，单级单选，单级多选，多级单选，多级多选","envelopePic":"http://wanandroid.com/blogimgs/cde55f38-9608-4616-a6d5-3453ca0b8de4.png","fresh":false,"id":7471,"link":"http://www.wanandroid.com/blog/show/2414","niceDate":"2018-11-06","origin":"","projectLink":"https://github.com/Tomzem/MultiLevelSelect","publishTime":1541507562000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=358"}],"title":"Android多级选择控件，单级单选，单级多选，多级单选，多级多选","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Tomzem","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"利用RecyclerView实现时间轴，支持自定义布局","envelopePic":"http://wanandroid.com/blogimgs/695493be-73e5-4e4b-90b3-728ca2cc2eb4.png","fresh":false,"id":7470,"link":"http://www.wanandroid.com/blog/show/2413","niceDate":"2018-11-06","origin":"","projectLink":"https://github.com/Tomzem/AndroidTimeAxis","publishTime":1541507531000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Andorid时间轴控件，支持自定义布局","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"hyzhan43","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"继上次用 kotlin 编写了 一款简单 豆瓣电影 app 后。体验到了kotlin 的魅力。加上这段时间学习了 MVP 模式、MVVM模式，心痒痒，就像做个 app 来练练手，正当犹豫要选择哪一种来练手的时候，无意中看见另一种的模式艺术图片应用 T-MVVM~ 感觉说的挺有道理的。好奇心驱使我去试一下这种模式，说干就干。","envelopePic":"http://www.wanandroid.com/blogimgs/070d9f4a-2ceb-457a-bb12-f7d55b5cf900.png","fresh":false,"id":7441,"link":"http://www.wanandroid.com/blog/show/2411","niceDate":"2018-10-30","origin":"","projectLink":"https://github.com/hyzhan43/PlayAndroid","publishTime":1540908634000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"用心打造&mdash;&mdash;Kotlin 版玩Android","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 4
     * over : false
     * pageCount : 38
     * size : 6
     * total : 225
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

}
