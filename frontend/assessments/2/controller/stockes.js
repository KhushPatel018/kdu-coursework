const express = require("express");
const router = express.Router();
const { addTrans, getAll } = require("../models/stock");

// initial data
router.get("/", (req, res) => {
  const basePrice = 100;
  const company = "Zomato";
  const logo =
    "https://mir-s3-cdn-cf.behance.net/projects/404/af1307136664867.Y3JvcCw1NzUzLDQ1MDAsNDIsMA.jpg";
  console.log("init data requested");
  res.json({ basePrice, company, logo });
});

// transection storage
router.post("/transection", (req, res) => {
  const transection = req.body.transection;
  console.log("transection recieved : ", transection);
  addTrans(transection);
  res.status(201).json(transection);
});

router.get("/transection", (req, res) => {
  res.json({
    transections: getAll(),
  });
});

module.exports = router;
