const express = require("express");
const router = express.Router();
const { getAllPosts, getPostById, createPost } = require("../models/post");

// get all posts
router.get("/", (req, res) => {
  try {
    res.json(getAllPosts());
  } catch (err) {
    res.json({
      error: err,
    });
  }
});

router.get("/:id", (req, res) => {
  const id = req.params.id;
  try {
    res.json(getPostById(id));
  } catch (err) {
    res.json({
      error: err.message,
    });
  }
});

router.post("/", (req, res) => {
  const post = req.body.post;
  try {
    res.status(201).json({
      post: createPost(post),
    });
  } catch (err) {
    console.log(err.message);
    res.json({ error: err.message });
  }
});

module.exports = router;
