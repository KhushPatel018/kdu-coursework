const express = require("express");
const router = express.Router();
const { getPosts, addPost, getPostById } = require("../models/post");

// GET /api/posts
router.get("/", (req, res) => {
  try {
    const pageNumber = parseInt(req.query.pageNumber) || 1;
    const pageSize = parseInt(req.query.pageSize) || 5;

    const posts = getPosts(pageNumber, pageSize);
    res.json(posts);
  } catch (err) {
    if (err.message === "Page not found") {
      return res.status(404).json({ message: "Page not found" });
    }
    console.error(err);
    res.status(500).json({ message: "Server Error" });
  }
});

// GET /api/posts/:id
router.get("/:id", (req, res) => {
  try {
    const postId = parseInt(req.params.id);
    const post = getPostById(postId);

    if (!post) {
      return res.status(404).json({ message: "Post not found" });
    }

    res.json(post);
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: "Server Error" });
  }
});

// POST /api/posts
router.post("/", (req, res) => {
  try {
    const { createdBy,profileUrl, content, imageUrl, videoUrl } = req.body;

    if (!createdBy || !content) {
      return res
        .status(400)
        .json({ message: "Please provide username and content" });
    }

    const newPost = addPost({ createdBy, profileUrl,content, imageUrl, videoUrl });
    res.status(201).json(newPost);
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: "Server Error" });
  }
});

module.exports = router;
