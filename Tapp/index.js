

var express = require('express');
var app  = express();
var mongoose = require('mongoose');
var morgan = require('morgan');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var config = require('./config');
var todoRoutes = require('./routes/todos');
var userRoutes = require('./routes/users');



mongoose.connect(config.database);



app.use(express.static(__dirname + '/public'));
app.use(morgan('dev'));
app.use(bodyParser.urlencoded({'extended':'true'}));
app.use(bodyParser.json());
app.use(bodyParser.json({ type: 'application/vnd.api+json' }));
app.use(methodOverride());



app.use('/user', userRoutes);
app.use('/todos',todoRoutes );





app.get('*', function(req, res) {
    res.sendfile('./public/index2.html');
});


app.listen(process.env.PORT || 7777);
console.log("App listening on port 7777");