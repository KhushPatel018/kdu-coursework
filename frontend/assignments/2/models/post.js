let posts = [];

function createPost(id, createdBy, profileUrl,content, imageUrl, videoUrl) {
  return {
    id,
    createdBy,
    profileUrl,
    content,
    imageUrl,
    videoUrl,
    createdAt: new Date(),
  };
}

function getPosts(pageNumber, pageSize) {
    const totalPosts = posts.length;
    const totalPages = Math.ceil(totalPosts / pageSize);
  
    if (pageNumber < 1 || pageNumber > totalPages) {
      throw new Error('Page not found');
    }
    const startIndex = (pageNumber - 1) * pageSize;
    const endIndex = Math.min(startIndex + pageSize, totalPosts);
    const paginatedPosts = posts.slice(startIndex, endIndex);
    return paginatedPosts;
  }
  

function addPost(postData) {
  const newPost = createPost(
    posts.length + 1,
    postData.createdBy,
    postData.profileUrl,
    postData.content,
    postData.imageUrl,
    postData.videoUrl
  );
  posts.unshift(newPost);
  return newPost;
}

function getPostById(postId) {
  const parsedId = parseInt(postId);
  if (isNaN(parsedId) || parsedId <= 0) {
    throw new Error("Invalid post ID");
  }

  const post = posts.find((post) => post.id === parsedId);
  if (!post) {
    throw new Error("Post not found");
  }

  return post;
}

module.exports = { getPosts, addPost, getPostById };
