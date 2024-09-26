const express = require("express");
const app = express();
const http = require("http");
const socketio = require("socket.io");
const server = http.createServer(app);
const moment = require("moment");
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
  socket.username = generateUniqueName();
  io.except(socket.id).emit(
    "join",
    formatMessage("System", `${socket.username} has joined.`)
  );
  console.log("new connection...");
  // on incomming message
  socket.on("message", (message) => {
    console.log(`message recieved : ${message}`);
    const formattedMessage = formatMessage(socket.username, message);
    io.emit("message", formattedMessage);
  });

  // on disconnect from user
  socket.on("disconnect", () => {
    console.log(`${socket.username} leaves the room`);
    io.emit("message", formatMessage("System", `${socket.username} has left.`));
  });
});

const PORT = process.env.PORT || 4000;

// starting the server
server.listen(PORT, () => console.log(`Server running on port ${PORT}`));

// for good presentation
function formatMessage(username, message) {
  return {
    username: username,
    text: message,
    timestamp: moment().format("h:mm a"),
  };
}

let count = -1;
let names = ["Khush", "Kirtan", "Kishan", "Kholi", "Karan"];
function generateUniqueName() {
  count++;
  return names[count % 5];
}
