const posts = document.querySelectorAll(".post");
function highlightHashtags(text) {
  return text.replace(
    /#[a-zA-Z0-9_]+/g,
    '<span style="color: blue;">$&</span>'
  );
}
posts.forEach((post) => {
  const content = post.querySelector('.content');
  console.log(content.textContent);
  const textContent =  content.textContent;
  content.innerHTML = `<p>
  ${highlightHashtags(textContent)}
  </p>`
  const likeContainer = post.querySelector(".like-container");
  const likePost = post.querySelector(".like-post");
  const likeCount = post.querySelector(".like-count");
  const stateCount = post.querySelector(".state-count");
  let likeStatus = false;
  let likeValue = parseInt(likeCount.textContent);
  likeContainer.addEventListener("click", () => {
    likeStatus = !likeStatus;
    if (likeStatus) {
      likePost.src = "./twiiter base line images/widgets/wid-3-liked.png";
      likePost.classList.add('unlike-post');
      likeValue++;
    } else {
      likePost.src = "./twiiter base line images/widgets/wid-3.png";
      likeValue--;
      likePost.classList.remove('unlike-post');
    }
    likeCount.textContent = likeValue;
    likeCount.style.display = likeValue === 0 ? "none" : "block";
    stateCount.textContent = likeValue;
    stateCount.style.display = likeValue === 0 ? "none" : "block";
  });

  likeCount.style.display = likeValue === 0 ? "none" : "block";
  stateCount.textContent = likeValue;
  stateCount.style.display = likeValue === 0 ? "none" : "block";
});

document.addEventListener("DOMContentLoaded", () => {
  const profile = document.querySelector("#profile");
  console.log(profile);
  const navigation = document.querySelector("#navigation");
  console.log(navigation);

  profile.addEventListener("click", (e) => {
    console.log("inside event");
    console.log(navigation.style.display + "me");
    navigation.style.display = "block";
  });

  const profile_exit = document.querySelector("#nav-exit");
  profile_exit.addEventListener("click", (e) => {
    console.log("inside event");
    console.log(navigation.style.display);
    navigation.style.display = "noun";
  });

  const postButton = document.querySelector("#desktop-post-button");
  const postInput = document.querySelector("#desktop-post-input");
  const postsContainer = document.querySelector("#desktop-posts");

  postButton.addEventListener("click", () => {
    const postText = postInput.value.trim();
    if (postText !== "") {
      const postTemplate = `
      <div class="new-post">
            <div class="post-img">
              <img
                src="./twiiter base line images/Profile/profile pic.png"
                alt=""
              />
            </div>
            <div class="title-wrapper">
              <div class="name-space">
                <span>Nitesh Gupta <span id="tag">@nit_hck · 1s</span></span>
                <p>···</p>
              </div>
              <div class="content">
                <p>
                ${highlightHashtags(postText)}
                </p>
              </div>
              <div class="options">
                <img
                  src="./twiiter base line images/widgets/wid-1.png"
                  alt=""
                />
                <img
                  src="./twiiter base line images/widgets/wid-2.png"
                  alt=""
                />
                <div class="like-container">
                  <img
                    class="like-post"
                    src="./twiiter base line images/widgets/wid-3.png"
                    alt=""
                  />
                  <p class="like-count">0</p>
                </div>
                <div class="state-container">
                  <img
                    class="state"
                    src="./twiiter base line images/widgets/wid-4.png"
                    alt=""
                  />
                  <p class="state-count">0</p>
                </div>
                <div class="upload">
                  <img
                    src="./twiiter base line images/widgets/wid-5.png"
                    alt=""
                  />
                  <img
                    src="./twiiter base line images/widgets/wid-6.png"
                    alt=""
                  />
                </div>
              </div>
            </div>
          </div>
      `;
      postsContainer.insertAdjacentHTML("beforeend", postTemplate);
      postInput.value = ""; // Clear input after posting
      console.log(newPosts);
    }
  });
});

const newPosts = document.querySelectorAll(".new-post");
console.log(newPosts);
newPosts.forEach((post) => {
  console.log(post.innerHTML);
  const likeContainer = post.querySelector(".like-container");
  const likePost = post.querySelector(".like-post");
  const likeCount = post.querySelector(".like-count");
  const stateCount = post.querySelector(".state-count");
  let likeStatus = false;
  let likeValue = parseInt(likeCount.textContent);
  likeContainer.addEventListener("click", () => {
    likeStatus = !likeStatus;
    if (likeStatus) {
      likePost.src = "./twiiter base line images/widgets/wid-3-liked.png";
      likeValue++;
    } else {
      likePost.src = "./twiiter base line images/widgets/wid-3.png";
      likeValue--;
    }
    likeCount.textContent = likeValue;
    likeCount.style.display = likeValue === 0 ? "none" : "block";
    stateCount.textContent = likeValue;
    stateCount.style.display = likeValue === 0 ? "none" : "block";
  });

  likeCount.style.display = likeValue === 0 ? "none" : "block";
  stateCount.textContent = likeValue;
  stateCount.style.display = likeValue === 0 ? "none" : "block";
});
