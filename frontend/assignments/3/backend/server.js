const express = require("express");
const app = express();
const http = require("http");
const socketio = require("socket.io");
const server = http.createServer(app);
const moment = require("moment");
const io = socketio(server, {
  cors: {
    origin: "http://localhost:5173",
    methods: ["GET", "POST"],
  },
});

let transactions = {};

io.on("connection", (socket) => {
  // send joining message
  socket.price = generateStockPrice();
  socket.emit('join',generateUniqueName());
  socket.on("price", (price) => {
    socket.emit("priceResponse", generateStockPrice(price));
  });
  socket.on('join-room', (room) => {
    if (!transactions[room]) {
      transactions[room] = [];
    }
    socket.join(room);
    socket.emit('initial-transactions', transactions[room]);
  });

  socket.on('leave-room', (room) => {
    socket.leave(room);
  });

  socket.on('new-transaction', (transaction) => {
    transactions[transaction.stock_name].push(transaction);
    io.to(transaction.stock_name).emit('new-transaction', transaction);
  });

  socket.on('disconnect', () => {
    console.log('User disconnected');
  });
});

function generateStockPrice(price) {
  let min = 0.0;
  let max = 500.0;

  let randomNum = Math.random() * (max - min) + min;

  return {
    price: randomNum.toString().substring(0, 6),
    hike: ((randomNum - price) / price) * 100,
  };
}

const PORT = process.env.PORT || 4000;

// starting the server
server.listen(PORT, () => console.log(`Server running on port ${PORT}`));

let count = -1;
let names = ["Khush", "Kirtan", "Kishan", "Kholi", "Karan"];
function generateUniqueName() {
  count++;
  return names[count % 5];
}
