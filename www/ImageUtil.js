var exec = require('cordova/exec');

exports.toBinary = function(arg0, success, error) {
    exec(success, error, "ImageUtil", "toBinary", [arg0]);
};
