const express = require("express");
const router = express.Router();
const {
  addUser,
  getUserByEmail,
  verifyPassword,
  getAllUsers,
} = require("../models/user");

router.post("/login", (req, res) => {
  const { email, password } = req.body;
  console.log(email,password);
  if (verifyPassword(email, password)) {
    res.status(200).json({ auth: true,user : getUserByEmail(email)});
  } else res.status(401).json({ auth: false });
});

router.get("/:email", (req, res) => {
  const email = req.params.email;
  res.json({
    user: getUserByEmail(email),
  });
});

module.exports =router;