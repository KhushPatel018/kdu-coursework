const container = document.querySelector(".container");
const messageBox = container.querySelector(".message-box");
const inputBox = container.querySelector("#message-text");
const send = document.querySelector("#sent");
const socket = io("http://localhost:4000");

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

send.addEventListener("input", () => {
  if (inputBox.value.trim() === "") {
    send.disabled = true;
    send.style.cursor = "default";
  } else {
    send.disabled = false;
    send.style.cursor = "pointer";
  }
});

// joining message
socket.on("join", (message) => {
  outputMessage(message);
});

// on message from the server
socket.on("message", (message) => {
  outputMessage(message);
});

// on disconnect
socket.on("disconnect", () => {
  console.log("Disconnected from chat server.");
});

// put the message to DOM
function outputMessage(message) {
  console.log(message);
  const messageDiv = document.createElement("div");
  messageDiv.classList.add("message");

  // created image
  const image = document.createElement("img");
  image.setAttribute("src", "./profile.png");
  image.classList.add("profile");
  messageDiv.appendChild(image);

  //body
  const messageBodyDiv = document.createElement("div");
  messageBodyDiv.classList.add("message-body");
  const messageHeader = document.createElement("div");
  messageHeader.classList.add("message-header");
  messageDiv.appendChild(messageBodyDiv);
  messageBodyDiv.appendChild(messageHeader);

  // header name
  const nameSpan = document.createElement("span");
  nameSpan.classList.add("name");
  nameSpan.innerText = message.username;
  messageHeader.appendChild(nameSpan);
  const timeSpan = document.createElement("span");
  timeSpan.classList.add("time");
  timeSpan.innerText = message.timestamp;
  nameSpan.appendChild(timeSpan);

  //content
  const messageContentDiv = document.createElement("div");
  messageContentDiv.classList.add("message-content");
  messageContentDiv.innerText = message.text;
  messageBodyDiv.appendChild(messageContentDiv);
  messageBox.appendChild(messageDiv);
  messageBox.scrollTop = messageBox.scrollHeight;
}
