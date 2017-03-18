/**
 * Created by Aleksey on 30.01.2017.
 */

var jwt = require('jsonwebtoken');
var express = require('express');
var router = express.Router();
var User  = require('../public/models/user');
var config = require('../config');
const secret = config.secret;
var decoder = require('../utils/decoder');



router.post('/authenticate', function(req, res) {
    var ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress || req.socket.remoteAddress || req.connection.socket.remoteAddress;
    User.findOne({
        name: req.body.username
    }, function(err, user) {
        if (err) throw err;
        if (!user) {
            res.json({ success: false, message: 'Authentication failed. Please check Name.' });
        } else if (user) {
            if (user.password != req.body.password) {
                res.json({ success: false, message: 'Authentication failed. Wrong password.' });
            } else {
                var token = jwt.sign(user, secret, {
                    expiresIn : 60*60*24// expires in 24 hours
                });
                User.findByIdAndUpdate(user._id , {$push: {"activeIp": {ip: ip, active: true}}},
                    {safe: true, upsert: true, new : true}, function(err, data) {
                        if (err) {
                            res.send(err);
                        } else {
                            res.json({
                        success: true,
                        message: 'You have successfully logged in and can add and remove your own todos!',
                        token: token
                    });
                        }
                    });
            }
        }
    });
});



router.post('/register', function(req, res){
    User.create({
        name:req.body.username,
        password:req.body.password,
        admin: false
    }, function(err, user){
        if(err){
            res.send(err);
        }else{
            res.json({message:"You have successfully registered, now you can log in"});
        }
    })
});



    router.post('/logout', function(req, res) {
        var ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress || req.socket.remoteAddress || req.connection.socket.remoteAddress;
        var user_id = decoder.decodeUseId((req.headers['authorization']));
        User.update({'_id':user_id},{ $pull: { 'activeIp': { ip: ip } } }, function(err, data){
            if(err){
                res.send(err);
            }
            res.json({message:"You have successfully logged out"});
        });
    });







    module.exports = router;
































// ======================= only jwt token check =========================================/////

// router.use(function(req, res, next) {
//     var token = req.body.token || req.query.token || req.headers['authorization'];
//     if (token) {
//         jwt.verify(token, secret, function(err, decoded) {
//             if (err) {
//                 return res.json({ success: false, message: 'Failed to authenticate token.' });
//             } else {
//                 req.decoded = decoded;
//                 console.log(decoded);
//                 next();
//             }
//         });
//
//     } else {
//
//         return res.status(403).send({
//             success: false,
//             message: 'No token provided.'
//         });
//
//     }
// });


//----------------------------------------AUTH WITHOUT IP -----//
// router.post('/authenticate', function(req, res) {
//
//     User.findOne({
//         name: req.body.username
//     }, function(err, user) {
//         if (err) throw err;
//         if (!user) {
//             res.json({ success: false, message: 'Authentication failed. Please check Name.' });
//         } else if (user) {
//             if (user.password != req.body.password) {
//                 res.json({ success: false, message: 'Authentication failed. Wrong password.' });
//             } else {
//                 var token = jwt.sign(user, secret, {
//                     expiresIn : 60*60*24// expires in 24 hours
//                 });
//                 User.findByIdAndUpdate(user._id, { active:true }, function(err, user){
//                     if(err){
//                         res.send(err);
//                     }
//                     res.json({
//                         success: true,
//                         message: 'You have successfully logged in and can add and remove your own todos!',
//                         token: token
//                     });
//                 });
//
//             }
//         }
//     });
// });

