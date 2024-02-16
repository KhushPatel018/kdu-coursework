const socket = io("http://localhost:5000");

let initialPrice = 100.0;

// emit every 5 second
setInterval(() => {
  socket.emit("price", initialPrice);
  socket.on("priceResponse", (response) => {
    initialPrice = response.price;
    outputTransaction(response);
  });
}, 5000);

// put the message to DOM
function outputTransaction(message) {
  console.log(message);
  //   console.log(message);
  //   const messageDiv = document.createElement("div");
  //   messageDiv.classList.add("message");

  //   // created image
  //   const image = document.createElement("img");
  //   image.setAttribute("src", "./profile.png");
  //   image.classList.add("profile");
  //   messageDiv.appendChild(image);

  //   //body
  //   const messageBodyDiv = document.createElement("div");
  //   messageBodyDiv.classList.add("message-body");
  //   const messageHeader = document.createElement("div");
  //   messageHeader.classList.add("message-header");
  //   messageDiv.appendChild(messageBodyDiv);
  //   messageBodyDiv.appendChild(messageHeader);

  //   // header name
  //   const nameSpan = document.createElement("span");
  //   nameSpan.classList.add("name");
  //   nameSpan.innerText = message.username;
  //   messageHeader.appendChild(nameSpan);
  //   const timeSpan = document.createElement("span");
  //   timeSpan.classList.add("time");
  //   timeSpan.innerText = message.timestamp;
  //   nameSpan.appendChild(timeSpan);

  //   //content
  //   const messageContentDiv = document.createElement("div");
  //   messageContentDiv.classList.add("message-content");
  //   messageContentDiv.innerText = message.text;
  //   messageBodyDiv.appendChild(messageContentDiv);
  //   messageBox.appendChild(messageDiv);
  //   messageBox.scrollTop = messageBox.scrollHeight;
}

const handleClick = () => {
  console.log("clicked");
  let msg = inputBox.value;
  console.log(msg);
  msg = msg.trim();

  if (!msg) {
    return false;
  }
  socket.emit("message", msg);
  inputBox.value = "";
};
