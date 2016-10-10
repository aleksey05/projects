
var express = require('express');
var router = express.Router();

var connection = require('./connection.js');


router.get('/', function(req, res, next) {

    connection.getConnection(function(error, con){
        if(error){
            console.log("no connection");
        }else{
              var catId = req.query.id;
            con.query("select * from projects where categoryId="+ catId, function(errors, rows, fields){
                con.release();
                res.render('category', { title: 'Category', rows:rows});
            });
        }
    });
});


module.exports = router;