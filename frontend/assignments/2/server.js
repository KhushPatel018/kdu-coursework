const express = require("express");
const app = express();
const PORT = process.env.PORT || 3000;
const cors = require('cors')
app.use(express.json());
app.use(cors());
const users = require('./controller/users');
const posts = require('./controller/posts');
app.use("/api/user", users);
app.use("/api/post",posts)


const http = require('http');
const socketIO = require('socket.io');

const server = http.createServer(app);
const io = socketIO(server);

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
    const { sender, receiver, message } = messageData;
    const receiverSocketId = Array.from(onlineUsers).find(([id, user]) => user.username === receiver)?.[0];
    if (receiverSocketId) {
      io.to(receiverSocketId).emit('receiveMessage', { sender, message });
    }
    io.to(socket.id).emit('receiveMessage', { sender, message }); // Send message back to sender as well
  });

  // Handle when a user disconnects
  socket.on('disconnect', () => {
    onlineUsers.delete(socket.id); 
    io.emit('updateUserList', Array.from(onlineUsers.values()));
    console.log('User disconnected');
  });
});

app.listen(PORT, () => console.log("app is listening on port " + PORT));


