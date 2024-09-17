const uuid = require("uuid");
const moment = require("moment");
const {getUserById} = require('./user');

let posts = [];

const getAllPosts = () => {
  return posts;
};

const getPostById = (id) => {
    const post = posts.find((post) => post.id === id);
    if(!post) throw new Error("Tweet not found.");
    return post;
};

const createPost = (postData) => {
  if (!postData?.tweet) {
    throw new Error("Tweet data is missing.");
  }

  if (!postData?.user_id) {
    throw new Error("user id is missing.");
  }else if(!getUserById(postData.user_id)){
    throw new Error("user id not found.");
  }

  const post = {
    id: uuid.v4(),
    user_id: postData.user_id,
    tweet: postData.tweet,
    like_count: 0,
    created_at: moment().toISOString(),
  };
  posts.push(post);
  return post;
};

module.exports = { getAllPosts, getPostById, createPost };
