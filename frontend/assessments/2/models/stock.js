
const transections = [];


function addTrans(transection){
    transection.push(transection);
    return transection;
}

function getAll(){
    return transections;
}

module.exports = {addTrans,getAll};