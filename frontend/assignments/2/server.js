const express = require("express");
const path = require('path');
const app = express();
const PORT = process.env.PORT || 3000;
const cors = require('cors')
const moment = require('moment');
app.use(express.json());
app.use(cors());
const users = require('./controller/users');
const posts = require('./controller/posts');
app.use("/api/user", users);
app.use("/api/post",posts)
app.use(express.static(path.join(__dirname,'view')));
console.log(__dirname);

const http = require('http');
const socketio = require('socket.io');

const server = http.createServer(app);
const io = socketio(server, {
  cors: {
    origin: "*",
    methods: ["GET", "POST"],
  },
});

const onlineUsers = new Map(); // in memory online users


io.on('connection', (socket) => {
  console.log('New user connected');

  // Handle when a user joins the chat
  socket.on('joinChat', (userData) => {
    onlineUsers.set(socket.id, userData); // Store user data in the map
    io.emit('updateUserList', Array.from(onlineUsers.values())); // Broadcast updated user list
  });

  // Handle when a user sends a message
  socket.on('sendMessage', (messageData) => {
    const sender = onlineUsers.get(socket.id);
    const { receiver, message } = messageData;
    const receiverSocketId = Array.from(onlineUsers).find(([id, user]) => user.username === receiver)?.[0];
    if (receiverSocketId) {
      io.to(receiverSocketId).emit('receiveMessage', { sender, message, time : moment().format('hh:mm a') , left : true });
    }
    io.to(socket.id).emit('receiveMessage', { sender, message, time : moment().format('hh:mm a'), left : false}); // Send message back to sender as well
  });

  // Handle when a user disconnects
  socket.on('disconnect', () => {
    onlineUsers.delete(socket.id); 
    io.emit('updateUserList', Array.from(onlineUsers.values()));
    console.log('User disconnected');
  });
});

server.listen(PORT, () => console.log("app is listening on port " + PORT));


