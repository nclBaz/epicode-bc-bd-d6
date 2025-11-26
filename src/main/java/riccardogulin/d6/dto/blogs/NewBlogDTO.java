package riccardogulin.d6.dto.blogs;

import java.util.UUID;

public record NewBlogDTO(UUID authorId, String title, String content) {
}
