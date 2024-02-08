
let replaceMap = {
  a: "4",
  e: "3",
  i: "1",
  o: "0",
  s: "5",
};

function replaceFunc(input) {
  return input.trim().replace(/[aeios]/g, (char) => replaceMap[char] || char);
}

let input1 = "javascript is cool  ";
let input2 = "programming is fun";
let input3 = "  become a coder";
console.log(
  "original strings : ",
  "1) " + input1,
  "2) " + input2,
  "3) " + input3
);
console.log(
  "modified string  : ",
  "1) " + replaceFunc(input1),
  "2) " + replaceFunc(input2),
  "3) " + replaceFunc(input3)
);
