package com.wanou.wanandroid.bean;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/12/3.
 */
public class CollectArticleBean {

    /**
     * curPage : 1
     * datas : [{"author":"鸿洋","chapterId":361,"chapterName":"课程推荐","courseId":13,"desc":"","envelopePic":"","id":34318,"link":"http://www.wanandroid.com/blog/show/2436","niceDate":"6分钟前","origin":"","originId":7568,"publishTime":1543822842000,"title":"Android 进阶课程推荐 | tinker 作者","userId":12763,"visible":0,"zan":0},{"author":"浪淘沙xud","chapterId":60,"chapterName":"Android Studio相关","courseId":13,"desc":"","envelopePic":"","id":34312,"link":"https://www.jianshu.com/p/88e32ef66ef2","niceDate":"18分钟前","origin":"","originId":3373,"publishTime":1543822135000,"title":"Android 技能图谱学习路线","userId":12763,"visible":0,"zan":0},{"author":"dengyuhan","chapterId":382,"chapterName":"音视频&amp;相机","courseId":13,"desc":"可以替代MediaMetadataRetriever的兼容方案\r\n可以获取图片、视频、音频文件的媒体信息、视频缩略图","envelopePic":"http://wanandroid.com/resources/image/pc/default_project_img.jpg","id":34311,"link":"http://www.wanandroid.com/blog/show/2441","niceDate":"18分钟前","origin":"","originId":7594,"publishTime":1543822130000,"title":"MediaMetadataRetrieverCompat - 多媒体元数据兼容方案","userId":12763,"visible":0,"zan":0},{"author":"songmao123","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"一款数据基于Wan Android API，采用Kotlin+MVP+Dagger2+Rxjava架构的Material Design风格玩安卓客户端。","envelopePic":"http://www.wanandroid.com/blogimgs/4c47aec3-1740-4ad9-9a37-ee99a1e742de.png","id":34310,"link":"http://www.wanandroid.com/blog/show/2429","niceDate":"19分钟前","origin":"","originId":7555,"publishTime":1543822061000,"title":"Kotlin+MVP+RxJava+Dagger2版玩安卓客户端","userId":12763,"visible":0,"zan":0},{"author":"xuehuayous","chapterId":314,"chapterName":"RV列表动效","courseId":13,"desc":"一种优雅的方式来使用RecyclerView，把你从复杂的多类型多样式中解放出来！","envelopePic":"http://wanandroid.com/blogimgs/1d6ed12c-1507-4826-878a-3737dff82daa.png","id":34309,"link":"http://www.wanandroid.com/blog/show/2439","niceDate":"20分钟前","origin":"","originId":7592,"publishTime":1543822016000,"title":"一种优雅的方式来使用RecyclerView","userId":12763,"visible":0,"zan":0},{"author":"鸿洋","chapterId":408,"chapterName":"鸿洋","courseId":13,"desc":"","envelopePic":"","id":34304,"link":"https://mp.weixin.qq.com/s/bNFcDEPuR4-RAMQAyI9T6w","niceDate":"46分钟前","origin":"","originId":7598,"publishTime":1543820442000,"title":"Android关于Color你所知道的和不知道的一切","userId":12763,"visible":0,"zan":0},{"author":"张旭童","chapterId":100,"chapterName":"RecyclerView","courseId":13,"desc":"","envelopePic":"","id":34303,"link":"https://blog.csdn.net/zxt0601/article/details/52562770","niceDate":"47分钟前","origin":"","originId":7601,"publishTime":1543820414000,"title":"【Android】RecyclerView的好伴侣：详解DiffUtil","userId":12763,"visible":0,"zan":0},{"author":"小编","chapterId":352,"chapterName":"资讯","courseId":13,"desc":"","envelopePic":"","id":32237,"link":"http://www.wanandroid.com/blog/show/2","niceDate":"2018-11-16","origin":"","originId":2864,"publishTime":1542364761000,"title":"玩Android API","userId":12763,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 8
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

    public static class DatasBean {
        /**
         * author : 鸿洋
         * chapterId : 361
         * chapterName : 课程推荐
         * courseId : 13
         * desc :
         * envelopePic :
         * id : 34318
         * link : http://www.wanandroid.com/blog/show/2436
         * niceDate : 6分钟前
         * origin :
         * originId : 7568
         * publishTime : 1543822842000
         * title : Android 进阶课程推荐 | tinker 作者
         * userId : 12763
         * visible : 0
         * zan : 0
         */

        private String author;
        private int chapterId;
        private String chapterName;
        private int courseId;
        private String desc;
        private String envelopePic;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private int originId;
        private long publishTime;
        private String title;
        private int userId;
        private int visible;
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
