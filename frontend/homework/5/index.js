let fs = require("fs").promises;
let os = require("os");
let express = require("express");
let app = express();

app.listen(8081, () => {
  console.log("app is running in port 8080");
});

//a. Os :
const createJson = () => {
  let jsonObj = {
    hostName: os.hostname(),
    operatingSystem: os.platform(),
    Architecture: os.arch(),
    OSRelease: os.release(),
    uptime: os.uptime(),
    numberOfCPUCores: os.cpus().length,
    totalMemory: os.totalmem(),
    freeMemory: os.freemem(),
    CurrentWorkingDirectory: process.cwd(),
  };
  console.log("A : OS RESULT OBJECT : \n",JSON.stringify(jsonObj, null, 2));
  fs.writeFile("./test.json", JSON.stringify(jsonObj, null, 2))
    .then(() => console.log("written to file"))
    .catch((err) => console.log(err));
};

const parseData = (data) => {
  let jsonString = JSON.stringify(data, null, 2);
  return `
    Hello, my name is Khush Patel!
    Here is my system information:
    ${jsonString}.`;
};
createJson();

app.get("/", (req, res) => {
  console.log("get request called");
  fs.readFile("./test.json", "utf-8")
    .then((data) => res.status(200).send(parseData(data)))
    .catch((err) => {
      res.status(500).send("failed to recieve file with " + err);
    });
});

// b. Path :

const path = require("path");

function extractFileInfo(filePath) {
  return {
    extension: path.extname(filePath),
    baseName: path.basename(filePath),
    directory: path.dirname(filePath),
  };
}

const filePath = "/home/hp/code/FrontEnd/NodeJs/handsOn/test.json";
console.log("B. PATH RESULT OF extractFileInfo : \n",JSON.stringify(extractFileInfo(filePath), null, 2));

const filePaths = [
  "./dir1/dir2/file1.txt",
  "./dir1/dir3/file2.txt",
  "./dir1/dir3/file3.md",
  "./dir4/file4.jpg",
  "./dir4/file5.pdf",
];

function processFilePaths(filePaths) {
  let result = Array();
  filePaths.forEach((filePath) => {
    result.push(extractFileInfo(filePath));
  });
  return result;
}

console.log( "B. PATH RESULT OF processFilePaths : \n",JSON.stringify(processFilePaths(filePaths),null,2));
