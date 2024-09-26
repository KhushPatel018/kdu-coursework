const fs = require("fs").promises;
const os = require("os");
const express = require("express");
const http = require('http');
const app = express();
const server = http.createServer(app);
// Start the server
server.listen(8080, () => {
  console.log("app is running in port 8080");
});

// a. Os:
const createJson = async () => {
  const jsonObj = {
    hostName: os.hostname(),
    operatingSystem: os.platform(),
    architecture: os.arch(),
    osRelease: os.release(),
    uptime: os.uptime(),
    numberOfCPUCores: os.cpus().length,
    totalMemory: os.totalmem(),
    freeMemory: os.freemem(),
    currentWorkingDirectory: process.cwd(),
  };
  console.log("A: OS RESULT OBJECT: \n", JSON.stringify(jsonObj, null, 2));
  try {
    await fs.writeFile("./test.json", JSON.stringify(jsonObj, null, 2));
    console.log("written to file");
  } catch (err) {
    console.log(err);
  }
};

const parseData = (data) => {
  const jsonString = JSON.stringify(data, null, 2);
  return `
    Hello, my name is Khush Patel!
    Here is my system information:
    ${jsonString}.`;
};

createJson();

app.get("/", async (req, res) => {
  console.log("get request called");
  try {
    const data = await fs.readFile("./test.json", "utf-8");
    res.status(200).send(parseData(data));
  } catch (err) {
    res.status(500).send("failed to receive file with " + err);
  }
});

// b. Path:
const path = require("path");

const extractFileInfo = (filePath) => {
  return {
    extension: path.extname(filePath),
    baseName: path.basename(filePath),
    directory: path.dirname(filePath),
  };
};

const filePath = "/home/hp/code/FrontEnd/NodeJs/handsOn/test.json";
console.log("B. PATH RESULT OF extractFileInfo: \n", JSON.stringify(extractFileInfo(filePath), null, 2));

const filePaths = [
  "./dir1/dir2/file1.txt",
  "./dir1/dir3/file2.txt",
  "./dir1/dir3/file3.md",
  "./dir4/file4.jpg",
  "./dir4/file5.pdf",
];

const processFilePaths = (filePaths) => {
  const result = [];
  filePaths.forEach((filePath) => {
    result.push(extractFileInfo(filePath));
  });
  return result;
};

console.log("B. PATH RESULT OF processFilePaths: \n", JSON.stringify(processFilePaths(filePaths), null, 2));
