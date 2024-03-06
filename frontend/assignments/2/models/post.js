let posts = [];
posts.push(
  createPost(
    1,
    "khush",
    "./twiiter base line images/Profile/p1.jpg",
    "Watch this video",
    "",
    "./twiiter base line images/videoes/pexels-rüveyda-20144591 (2160p).mp4"
  )
);
posts.push(
  createPost(
    5,
    "khush",
    "./twiiter base line images/Profile/p1.jpg",
    "Lets do it guys",
    "",
    ""
  )
);
posts.push(
  createPost(
    2,
    "jaideep",
    "./twiiter base line images/Profile/p2.jpg",
    "Watch this amazing video",
    "",
    "./twiiter base line images/pictures/neom-bA32w6lebJg-unsplash.jpg"
  )
);
posts.push(
  createPost(
    3,
    "raj",
    "./twiiter base line images/Profile/p3.jpg",
    "Watch this new look",
    "./twiiter base line images/pictures/lucas-canino-RbAFhuLhQ-s-unsplash.jpg",
    ""
  )
);
posts.push(
  createPost(
    6,
    "khush",
    "./twiiter base line images/Profile/p1.jpg",
    "Yet another post by me for test #booommmmmmm",
    "",
    ""
  )
);
posts.push(
  createPost(
    4,
    "khush",
    "./twiiter base line images/Profile/p1.jpg",
    "Watch this image",
    "./twiiter base line images/pictures/neom-bA32w6lebJg-unsplash.jpg",
    ""
  )
);
function createPost(id, createdBy, profileUrl, content, imageUrl, videoUrl) {
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
    throw new Error("Page not found");
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
