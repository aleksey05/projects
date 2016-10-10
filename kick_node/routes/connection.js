var mysql = require('mysql');

var connection = mysql.createPool({
    connectionLimit:50,
    hostname:"localhost",
    user:"root",
    password:"12345",
    database:"kickstarter"
});

 module.exports = connection;