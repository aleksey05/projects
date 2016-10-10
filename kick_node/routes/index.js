var express = require('express');
var router = express.Router();

var connection = require('./connection.js');


/* GET home page. */
router.get('/', function(req, res, next) {

  connection.getConnection(function(error, con){
    if(error){
      console.log("no connection");
    }else{
      con.query("select * from categories", function(errors, rows, fields){
   con.release();
        res.render('index', { title: 'Kickstarter', rows:rows});
});
    }
  });
});


module.exports = router;
