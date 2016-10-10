var express = require('express');
var router = express.Router();
var connection = require('./connection.js');



router.get('/', function(req, res, next) {
    var payments;
    var projectId = req.query.id;
    var projectInfo;

    connection.getConnection(function(error, con){
        if(error){
            console.log("no connection");
        }else{
              getPayments(con, projectId, function(rows){
                    payments = rows;
            });
              getProjectInfo(con, projectId, function(rows){
                  projectInfo = rows;
                  con.release();
                  res.render('project', { title: projectInfo[0].title, rows:projectInfo, payments:payments});
              });

            // con.query("select projects.*, questions.question from projects inner join questions on" +
            //     " projects.projectId = questions.projectId where" +
            //     " projects.projectId="+ projectId, function(errors, rows, fields){
            //     con.release();
            //     res.render('project', { title: rows[0].title, rows:rows, payments:payments});
            // });
        }
    });
});

router.post('/add/question',function(req, res, next){

    connection.getConnection(function(error, con){
        if(error){
            console.log("no connection");
        }else{
            var projectId = req.body.projectId;
            var question = req.body.question;
            var query="insert into questions (question, projectId) values ( " + " \"" + question + "\""+", " + projectId +" );"
            console.log(query);
            con.query(query, function(errors, rows, fields){
                if(errors){
                    console.log(errors);
                }
                con.release();
                res.redirect('/project?id=' + projectId);
            });
        }
    });



    router.post('/payment', function(req, res, next) {
        var payment;
        var projectId;

        connection.getConnection(function(error, con){
            if(error){
                console.log("no connection");
            }else{
                projectId = req.body.projectId;
                payment = req.body.payment;
                var query="insert into payments (amount, projectId) values ( " +  payment +", " + projectId +" );"
                con.query(query, function(errors, rows, fields){
                    con.release();
                    res.redirect('/project?id='+ projectId);
                });
            }
        });
    });




});

function getPayments(con, projectId, fn){
    con.query("SELECT sum(amount) as sum FROM payments where projectId="+ projectId, function(errors, rows, fields) {
        if(errors){
            console.log("Errors: " + errors);
        }else{
            fn(rows);
        }
    });
}

function getProjectInfo(con, projectId, fn) {
    con.query("select projects.*, questions.question from projects inner join questions on" +
        " projects.projectId = questions.projectId where" +
        " projects.projectId="+ projectId, function(errors, rows, fields){
        if(errors){
            console.log(errors);
        }else {
            fn(rows);
        }
    });
}
module.exports = router;