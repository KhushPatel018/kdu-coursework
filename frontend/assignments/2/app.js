// Function to fetch user data
async function fetchUserData(email) {
  try {
    const response = await fetch(`http://localhost:3000/api/user/${email}`);
    if (!response.ok) {
      throw new Error("Failed to fetch user details");
    }
    return await response.json();
  } catch (error) {
    console.log(error);
  }
}

// Function to update DOM elements with user data
function updateDOMWithData(data) {
  const navProfile = document.getElementById("nav-profile");
  const navName = document.getElementById("nav-name");
  const nameTag = document.getElementById("nav-tag");
  const boxImage = document.getElementById("profile-tweetbox");
  
  navProfile.src = `${data.user.profile_url}`;
  boxImage.src = `${data.user.profile_url}`;
  navName.innerText = `${data.user.user_name}`;
  nameTag.innerText = `@${data.user.user_name}`;
}
let userData;
document.addEventListener("DOMContentLoaded", async () => {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const email = urlParams.get("email");

  let data = await fetchUserData(email);
  console.log(data);
  userData= data.user;
  setTimeout(() => {
    updateDOMWithData(data);
    renderPosts();
  }, 1000);
});


// media upload
const mediaInput = document.querySelector("#media-input");
const mediaInputDisplay = document.querySelector(".media-input-display");
const addMedia = () => {
  mediaInput.click();
  mediaInput.addEventListener("change", (e) => {
    const file = e.target.files[0];
    const fileReader = new FileReader();
    let image = document.createElement("img");
    let video = document.createElement("video");
    video.controls = true;
    fileReader.onload = (e) => {
      if (e.target.result.substring(0, 10) === "data:image") {
        image.src = e.target.result;
        mediaInputDisplay.innerHTML = "";
        mediaInputDisplay.appendChild(image);
      } else if (e.target.result.substring(0, 10) === "data:video") {
        video.src = e.target.result;
        mediaInputDisplay.innerHTML = "";
        mediaInputDisplay.appendChild(video);
      }
    };
    fileReader.readAsDataURL(file);
  });
};

function highlightHashtags(text) {
  return text.replace(/#\w+/g, '<span style="color: blue;">$&</span>');
}

// add local user's post through post box
document.getElementById("post-button").addEventListener("click", () => {
  const content = postInput.value.trim();
  const imageUrl = getMediaUrl("IMG");
  const videoUrl = getMediaUrl("VIDEO");

  if (!content && !imageUrl && !videoUrl) {
    alert("Please enter some content or attach an image/video.");
    return;
  }

  const postData = {
    createdBy: userData.user_name,
    content: content,
    profileUrl: userData.profile_url,
    imageUrl: imageUrl,
    videoUrl: videoUrl,
  };

  createPostOnServer(postData)
    .then((responseData) => {
      console.log("Post created successfully:", responseData);
      // Refresh posts after successfully adding a new one
      renderPosts();
    })
    .catch((error) => {
      console.error("Error creating post:", error);
    });

  // Clear input fields after posting
  postInput.value = "";
  mediaInputDisplay.innerHTML = "";
  window.location.reload();
});

function getMediaUrl(tagName) {
  const firstChild = mediaInputDisplay.firstChild;
  return firstChild && firstChild.nodeName === tagName ? firstChild.src : "";
}

function createPostOnServer(postData) {
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(postData),
  };

  return fetch("http://localhost:3000/api/post", options).then((response) => {
    if (!response.ok) {
      throw new Error("Failed to create post");
    }
    return response.json();
  });
}


// activate the post button
const postInput = document.getElementById("post-input");
postInput.addEventListener("input", () => {
  const postButtton = document.getElementById("post-button");
  if (postInput.value !== "") {
    postButtton.style.backgroundColor = "#1a8cd8";
    postButtton.style.color = "#e7e9ea";
  } else {
    postButtton.style.backgroundColor = "#0f4e78";
    postButtton.style.color = "#797d7f";
  }
});


// can be modified
let currentPageNumber = 1;

function renderPosts() {
  // Fetch posts from the backend using paginated API
  fetchPosts(currentPageNumber, 5)
    .then((posts) => {
      // Render fetched posts
      posts.forEach((postData) => {
        const postContainer = createPost(postData);
        document.querySelector(".posts").append(postContainer);
      });
      currentPageNumber++; // Increment page number for next fetch
      widgetUpdates();
    })
    .catch((error) => {
      console.error("Error fetching posts:", error);
    });
}

// Function to fetch posts from the backend using paginated API
function fetchPosts(pageNumber, pageSize) {
  return fetch(
    `http://localhost:3000/api/post?pageNumber=${pageNumber}&pageSize=${pageSize}`
  ).then((response) => {
    if (!response.ok) {
      throw new Error("Failed to fetch posts");
    }
    return response.json();
  });
}

// Add event listener for scroll to load more posts
window.addEventListener("scroll", () => {
  const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
  if (scrollTop + clientHeight >= scrollHeight - 5) {
    renderPosts();
  }
});

// general post creation
function createPost(postData) {
  // Create main post container
  const postContainer = document.createElement("div");
  postContainer.classList.add("post");
  postContainer.id = `post-id-${postData.id}`;
  // Create profile section
  const profileDiv = document.createElement("div");
  profileDiv.classList.add("profile");
  const profileImg = document.createElement("img");
  profileImg.src = `${postData.profileUrl}`;
  profileImg.alt = "";
  profileImg.id = "profile-post";
  profileDiv.appendChild(profileImg);
  postContainer.appendChild(profileDiv);

  // Create post-box section
  const postBoxDiv = document.createElement("div");
  postBoxDiv.classList.add("post-box");
  const dotsDiv = document.createElement("div");
  dotsDiv.classList.add("dots");
  dotsDiv.innerText = "...";
  const userDetailsDiv = document.createElement("div");
  userDetailsDiv.classList.add("user-details");
  const nameSpan = document.createElement("span");
  nameSpan.classList.add("name");
  nameSpan.innerText = `${postData.createdBy}`;
  const tagSpan = document.createElement("span");
  tagSpan.classList.add("tag");
  tagSpan.innerText = `@${postData.createdBy}`;
  const timeSpan = document.createElement("span");
  timeSpan.classList.add("time");
  timeSpan.innerText = "1s";
  userDetailsDiv.append(nameSpan);
  userDetailsDiv.append(tagSpan);
  userDetailsDiv.append(timeSpan);
  const contentDiv = document.createElement("div");
  contentDiv.classList.add("content");
  contentDiv.innerHTML = highlightHashtags(postData.content);
  postBoxDiv.append(dotsDiv);
  postBoxDiv.append(userDetailsDiv);
  postBoxDiv.append(contentDiv);

  // Add image-content section
  if (postData.imageUrl || postData.imageUrl !== "") {
    const imageContentDiv = document.createElement("div");
    imageContentDiv.classList.add("image-content");
    const image = document.createElement("img");
    image.src = postData.imageUrl;
    image.alt = "image";
    imageContentDiv.append(image);
    postBoxDiv.append(imageContentDiv);
  }

  // Add video-content section
  if (postData.videoUrl || postData.videoUrl !== "") {
    const videoContentDiv = document.createElement("div");
    videoContentDiv.classList.add("video-content");
    const video = document.createElement("video");
    video.controls = true;
    video.src = postData.videoUrl;
    videoContentDiv.append(video);
    postBoxDiv.append(videoContentDiv);
  }

  // Add widget-wrapper section
  const widgetWrapperDiv = document.createElement("div");
  widgetWrapperDiv.classList.add("widget-wrapper");
  const widgetsDiv = document.createElement("div");
  widgetsDiv.classList.add("widgets");

  const widContainer1 = createWidgetContainer(
    "./twiiter base line images/widgets/wid-1.png",
    "Messages",
    0
  );
  const widContainer2 = createWidgetContainer(
    "./twiiter base line images/widgets/wid-2.png",
    "retweet",
    0
  );
  const widContainer3 = createWidgetContainer(
    "./twiiter base line images/widgets/wid-3.png",
    "like",
    0,
    "like",
    "like-container",
    "like-value"
  );
  const widContainer4 = createWidgetContainer(
    "./twiiter base line images/widgets/wid-4.png",
    "stats",
    0,
    "stat",
    "stat-container",
    "stat-value"
  );
  widgetsDiv.appendChild(widContainer1);
  widgetsDiv.appendChild(widContainer2);
  widgetsDiv.appendChild(widContainer3);
  widgetsDiv.appendChild(widContainer4);
  const comboDiv = document.createElement("div");
  comboDiv.classList.add("combo");
  const bookmarkImg = document.createElement("img");
  bookmarkImg.src = "./twiiter base line images/widgets/wid-5.png";
  bookmarkImg.alt = "bookmark";
  const uploadImg = document.createElement("img");
  uploadImg.src = "./twiiter base line images/widgets/wid-6.png";
  uploadImg.alt = "upload";
  comboDiv.appendChild(bookmarkImg);
  comboDiv.appendChild(uploadImg);
  widgetWrapperDiv.append(widgetsDiv);
  widgetWrapperDiv.append(comboDiv);
  postBoxDiv.append(widgetWrapperDiv);

  postContainer.append(postBoxDiv);
  return postContainer;
}

function createWidgetContainer(
  imgSrc,
  altText,
  value,
  id,
  extraClass,
  valueClass
) {
  const containerDiv = document.createElement("div");
  containerDiv.classList.add("wid-container");
  if (extraClass) {
    containerDiv.classList.add(extraClass);
  }
  const img = document.createElement("img");
  img.src = imgSrc;
  img.alt = altText;
  const p = document.createElement("p");
  p.classList.add("value");
  if (valueClass) {
    p.classList.add(valueClass);
  }
  p.textContent = value;
  if (id) {
    img.id = id;
  }
  containerDiv.appendChild(img);
  containerDiv.appendChild(p);
  return containerDiv;
}

function widgetUpdates() {
  const newPostContainer = document.querySelector(".posts");
  console.log(newPostContainer.childNodes);
  newPostContainer.childNodes.forEach((post) => {
    console.log(post);
    const content = post.querySelector(".content");
    console.log(content.textContent);
    const textContent = content.textContent;
    content.innerHTML = highlightHashtags(textContent);

    // like button
    const likeContainer = post.querySelector(".like-container");
    const likePost = post.querySelector("#like");
    const likeCount = post.querySelector(".like-value");
    const stateCount = post.querySelector(".stat-value");
    const values = post.querySelectorAll(".value");

    // logic of event listner
    let likeStatus = false;
    let likeValue = parseInt(likeCount.textContent);
    likeContainer.addEventListener("click", () => {
      likeStatus = !likeStatus;
      if (likeStatus) {
        likePost.src = "./twiiter base line images/widgets/wid-3-liked.png";
        likePost.classList.add("unlike-post");
        likeValue++;
      } else {
        likePost.src = "./twiiter base line images/widgets/wid-3.png";
        likeValue--;
        likePost.classList.remove("unlike-post");
      }
      // display settings
      likeCount.textContent = likeValue;
      likeCount.style.display = likeValue === 0 ? "none" : "block";
      stateCount.textContent = likeValue;
      stateCount.style.display = likeValue === 0 ? "none" : "block";
    });

    // for intial setup
    likeCount.style.display = likeValue === 0 ? "none" : "block";
    stateCount.textContent = likeValue;
    stateCount.style.display = likeValue === 0 ? "none" : "block";

    // to display only when count is not zero
    values.forEach((value) => {
      value.style.display = value.textContent === "0" ? "none" : "block";
    });
  });
}



// chat operations
const socket = io('http://localhost:3000');

    socket.on('connect', () => {
      const userData = { /* User data */ }; // Provide user data when connecting
      socket.emit('joinChat', userData);
    });

    socket.on('updateUserList', (users) => {
      const userList = document.getElementById('userList');
      userList.innerHTML = ''; // Clear existing user list
      populateOnlineUsers(users);
    });

    socket.on('receiveMessage', (messageData) => {
      populateMessege(messageData);
    });

    function sendMessage() {
      const receiverInput = document.getElementById('receiverInput');
      const messageInput = document.getElementById('messageInput');
      const receiver = receiverInput.value.trim();
      const message = messageInput.value.trim();
      if (receiver !== '' && message !== '') {
        socket.emit('sendMessage', { receiver, message });
        messageInput.value = ''; 
      }
    }

function populateOnlineUsers(users){
  console.log(users);
}
function populateMessege(messageData){
  console.log(messageData)
}