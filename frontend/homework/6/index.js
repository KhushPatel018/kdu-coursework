const express = require("express");
const app = express();
const cors = require("cors");
app.use(express.json());
app.use(cors());
const posts = require("./routes/posts");
const users = require("./routes/users");
app.use("/api/posts", posts);
app.use("/api/users", users);

const PORT = process.env.PORT || 8080;

// Start the server
app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});
