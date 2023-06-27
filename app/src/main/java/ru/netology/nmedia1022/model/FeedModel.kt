
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.dto.PostList

data class FeedModel(
    val posts: List<Post> = emptyList(),
    val postslist: List<PostList> = emptyList(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val empty: Boolean = false,
    val refreshing: Boolean = false,
)
