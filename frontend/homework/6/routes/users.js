const express = require("express");
const router = express.Router();
const { addUser, getAllUsers, getUserById } = require("../models/user");
const { createPost, getAllPosts, getPostById } = require("../models/post");

// Get all users
router.get("/", (req, res) => {
  try {
    const users = getAllUsers();
    res.json(users);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Get user by ID
router.get("/:id", (req, res) => {
  const id = req.params.id;
  try {
    const user = getUserById(id);
    if (!user) {
      res.status(404).json({ error: "User not found" });
      return;
    }
    res.json(user);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Create a new user
router.post("/", (req, res) => {
  const userData = req.body;
  try {
    const user = addUser(userData);
    res.status(201).json({ user });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

// Get all posts of a user
router.get("/:userId/posts", (req, res) => {
  const userId = req.params.userId;
  try {
    const user = getUserById(userId);
    if (!user) {
      res.status(404).json({ error: "User not found" });
      return;
    }
    const userPosts = getAllPosts().filter((post) => post.user_id === userId);
    res.json(userPosts);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Get a specific post of a user
router.get("/:userId/posts/:postId", (req, res) => {
  const userId = req.params.userId;
  const postId = req.params.postId;
  try {
    const user = getUserById(userId);
    if (!user) {
      res.status(404).json({ error: "User not found" });
      return;
    }
    const post = getPostById(postId);
    if (!post || post.user_id !== userId) {
      res.status(404).json({ error: "Post not found for this user" });
      return;
    }
    res.json(post);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

module.exports = router;
