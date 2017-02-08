/**
 * Created by Aleksey on 30.01.2017.
 */
module.exports = {

    decodeUseId: function(token){
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(new Buffer(base64, 'base64').toString('ascii'))._doc._id;
    }
}


