const uuid = require('uuid');

let users = [
    {
        "id" : uuid.v4(),
        "username" : "khush patel",
        "password" : "password",
        "profile" : "random_url",
        "twitter_tag" : "@khush-patel18",
        "bio" : "hello i am cristiano ronaldo"
    }
];

const getAllUsers = () => {
    return users;
};

const getUserById = (id) => {
    return users.find(user => user.id === id);
};

const addUser = (userData) => {
    const user = {
        "id" : uuid.v4(),
        "username" : userData.username,
        "password" : userData.password,
        "profile" : userData.profile,
        "twitter_tag" : userData.twitter_tag,
        "bio" : userData.bio
    };
    users.push(user);
    return user;
};

module.exports = { addUser, getAllUsers, getUserById };
