const express = require("express");
const app = express();
const http = require("http");
const socketio = require("socket.io");
const server = http.createServer(app);
const moment = require("moment");
const cors = require("cors");
app.use(express.json());
const stocks = require("./controller/stockes");
app.use("/api/stocks", stocks);

const io = socketio(server, {
  cors: {
    origin: "*",
    methods: ["GET", "POST"],
  },
});

app.use(express.static("view"));

// on new user connection
io.on("connection", (socket) => {
  // send joining message
  socket.price = generateStockPrice();
  socket.on("price", (price) => {
    socket.emit("priceResponse", generateStockPrice(price));
  });
  // on disconnect from user
  socket.on("disconnect", () => {
    console.log(`${socket.id} disconnected`);
  });
});

const PORT = process.env.PORT || 5000;

// starting the server
server.listen(PORT, () => console.log(`Server running on port ${PORT}`));

function generateStockPrice(price) {
  let min = 100.0;
  let max = 500.0;

  let randomNum = Math.random() * (max - min) + min;

  return {
    price: randomNum.toString().substring(0, 6),
    hike: ((randomNum - price) / price) * 100,
  };
}
