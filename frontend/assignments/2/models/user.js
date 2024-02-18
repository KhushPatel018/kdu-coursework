const uuid = require("uuid");
const bcryptjs = require("bcryptjs");
const moment = require("moment");
const numSaltRounds = process.env.NODE_ENV === "test" ? 1 : 12;
let count = 0;
let profiles = [
  "./twiiter base line images/Profile/p1.jpg",
  "./twiiter base line images/Profile/p2.jpg",
  "./twiiter base line images/Profile/p3.jpg",
  "./twiiter base line images/Profile/p4.jpg",
];
let users = [
  {
    id: uuid.v4(),
    user_name: "khush",
    user_email_id: `khush@x.com`,
    password: bcryptjs.hashSync("password", numSaltRounds),
    profile_url: profiles[++count % profiles.length],
    created_at: moment().format("h:mm a"),
  },
  {
    id: uuid.v4(),
    user_name: "jaideep",
    user_email_id: `jaideep@x.com`,
    password: bcryptjs.hashSync("password", numSaltRounds),
    profile_url: profiles[++count % profiles.length],
    created_at: moment().format("h:mm a"),
  },
  {
    id: uuid.v4(),
    user_name: "raj",
    user_email_id: `raj@x.com`,
    password: bcryptjs.hashSync("password", numSaltRounds),
    profile_url: profiles[++count % profiles.length],
    created_at: moment().format("h:mm a"),
  },
];


const addUser = (username, password) => {
  const user = {
    id: uuid.v4(),
    user_name: username,
    user_email_id: `${username}@x.com`,
    password: bcryptjs.hash(password, numSaltRounds),
    profile_url: profiles[++count % profiles.length],
    created_at: moment.defaultFormatUtc(),
  };
  users.push(user);
  return user;
};

const getUserByEmail = (email) => {
  let user = users.find((user) => user.user_email_id === email);
  if (user) {
    return user;
  } else return false;
};

const verifyPassword = (email, pass) => {
  let user = getUserByEmail(email);
  if (!user) {
    return false;
  }
  return bcryptjs.compareSync(pass, user.password);
};

const getAllUsers = () => {
  return users;
};

module.exports = { addUser, getUserByEmail, verifyPassword, getAllUsers };
