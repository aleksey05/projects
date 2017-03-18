/**
 * Created by Aleksey on 30.01.2017.
 */


var express = require('express');
var router = express.Router();
var Todo = require('../public/models/todo');
var User  = require('../public/models/user');
var decoder = require('../utils/decoder');
var jwt = require('jsonwebtoken');
var config = require('../config');
const secret = config.secret;



router.use(function(req, res, next) {
    var ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress || req.socket.remoteAddress || req.connection.socket.remoteAddress;
    var token = req.body.token || req.query.token || req.headers['authorization'];
    if(token) {
        jwt.verify(token, secret, function (err, decoded) {
            if (err) {
                return res.json({success: false, message: 'Failed to authenticate token.'});
            } else {
                var user_id = decoder.decodeUseId((req.headers['authorization']));
                User.findOne({'_id': user_id}, function (err, user) {
                    if (err) {
                        return res.json({success: false, message: 'Failed to authenticate User'});
                    } else if (!findIp(user.activeIp, ip)) {
                        return res.json({success: false, message: 'user inactive, please login.'});
                    }
                    else {
                        req.decoded = decoded;
                        next();
                    }
                });

            }
        });
    }else{
        return res.status(403).send({
            success: false,
            message: 'No token provided.'
        });
    }
});

function findIp(arr, ip){
    for(var i = 0; i < arr.length; i++) {
        if (arr[i].ip == ip && arr[i].active == true) {
           return true;
        }
    }
    return false;
}


router.delete('/:todo_id/', function(req, res) {
    var user_id = decoder.decodeUseId(req.headers['authorization']);
    console.log(req.headers.toString());
    Todo.remove({
        _id : req.params.todo_id
    }, function(err, todo) {
        if (err)
            res.send(err);
        Todo.find({ user_id:user_id}, function(err, data) {
            if (err)
                res.send(err)
            res.json(data);
        });
    });
});





    router.get('/', function(req, res){
    var user_id = decoder.decodeUseId(req.headers['authorization']);
    Todo.find({user_id:user_id},function(err, data) {
        if (err)
            res.send(err)
        res.json(data);
    });

});


router.post('/', function(req, res) {
    var user_id = helper.decodeUseId(req.headers['authorization']);
    Todo.create({
        text : req.body.text,
        user_id: user_id,
        done : false
    }, function(err, todo) {
        if (err)
            res.send(err);
        Todo.find({ user_id:user_id}, function(err, todos) {
            if (err)
                res.send(err)
            res.json(todos);
        });
    });

});


module.exports = router;












// Todo.find({user_id:user_id}).populate('user_id').exec(function(err, data){
//     if(err) res.send(err);
//     console.log(data[0].user_id.name);
//     res.json(data);
// })

// Contact.findByIdAndUpdate(
//     info._id,
//     {$push: {"messages": {title: title, msg: msg}}},
//     {safe: true, upsert: true, new : true},
//     function(err, data) {
//         console.log(err);
//     }
// );