var mongoose = require('mongoose');

var Todo = mongoose.model('Todo', {
    text: String,
    user_id: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    }});


module.exports = Todo;

