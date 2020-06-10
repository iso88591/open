package grg.app.open.net.bean

data class IndexArticle(
    val curPage: Int? = null,
    val datas: List<Data>? = null,
    val offset: Int? = null,
    val over: Boolean? = null,
    val pageCount: Int? = null,
    val size: Int? = null,
    val total: Int? = null
)

data class Data(
    val apkLink: String? = null,
    val audit: Int? = null,
    val author: String? = null,
    val canEdit: Boolean? = null,
    val chapterId: Int? = null,
    val chapterName: String? = null,
    val collect: Boolean? = null,
    val courseId: Int? = null,
    val desc: String? = null,
    val descMd: String? = null,
    val envelopePic: String? = null,
    val fresh: Boolean? = null,
    val id: Int? = null,
    val link: String? = null,
    val niceDate: String? = null,
    val niceShareDate: String? = null,
    val origin: String? = null,
    val prefix: String? = null,
    val projectLink: String? = null,
    val publishTime: Long? = null,
    val selfVisible: Int? = null,
    val shareDate: Long? = null,
    val shareUser: String? = null,
    val superChapterId: Int? = null,
    val superChapterName: String? = null,
    val tags: List<Tag>? = null,
    val title: String? = null,
    val type: Int? = null,
    val userId: Int? = null,
    val visible: Int? = null,
    val zan: Int? = null
)

data class Tag(
    val name: String? = null,
    val url: String? = null
)