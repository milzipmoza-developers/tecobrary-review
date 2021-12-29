package dev.milzipmoza.review.naver.search.api

data class NaverSearchBookItemDto(
        val title: String = "",
        val link: String = "",
        val image: String = "",
        val author: String = "",
        val publisher: String = "",
        val pubdate: String = "",
        val isbn: String = "",
        val description: String = ""
) {
    override fun toString(): String {
        return "NaverSearchBookItemDto(\ntitle='$title',\n link='$link',\n image='$image',\n author='$author',\n publisher='$publisher',\n pubdate='$pubdate',\n isbn='$isbn',\n description='$description'\n)"
    }
}
