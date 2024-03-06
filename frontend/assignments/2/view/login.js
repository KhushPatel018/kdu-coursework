const username = document.getElementById("username");
const password = document.getElementById("password");
const login = document.getElementById("login");

login.addEventListener("click", () => {
  let user = {
    email : username.value,
    password: password.value,
  };
  let options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(user),
  };

  let fetchRes = fetch("http://localhost:3000/api/user/login", options);
  fetchRes
    .then((res) => res.json())
    .then((d) => {
      console.log(d);
      if (d.auth === true) {
        console.log(d.user.user_email_id,d.user.profile_url);
        window.location = `./home.html?email=${d.user.user_email_id}`;
      } else {
        showError();
      }
    })
    .catch((e) => console.log(e));
});

function showError() {
  const error = document.getElementById("error");
  error.style.display = "block";
}
