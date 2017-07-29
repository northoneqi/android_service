/**
 * Load the library located at the same path with this file
 *
 * Will automatically load ext-all-debug.js if any of these conditions is true:
 * - Current hostname is localhost
 * - Current hostname is an IP v4 address
 * - Current protocol is "file:"
 *
 * Will load ext-all.js (minified) otherwise
 */
(function() {
    var scripts = document.getElementsByTagName('script'),
        localhostTests = [
            /^localhost$/
          //  /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(:\d{1,5})?\b/ // IP v4
        ],
        host = window.location.hostname,
        isDevelopment = null,
        queryString = window.location.search,
        test, path, i, ln, scriptSrc, match;

    for (i = 0, ln = scripts.length; i < ln; i++) {
        scriptSrc = scripts[i].src;

        match = scriptSrc.match(/bootstrap\.js$/);

        if (match) {
            path = scriptSrc.substring(0, scriptSrc.length - match[0].length);
            break;
        }
    }

    if (queryString.match('(\\?|&)debug') !== null) {
        isDevelopment = true;
    }
    else if (queryString.match('(\\?|&)nodebug') !== null) {
        isDevelopment = false;
    }

    if (isDevelopment === null) {
        for (i = 0, ln = localhostTests.length; i < ln; i++) {
            test = localhostTests[i];

            if (host.search(test) !== -1) {
                isDevelopment = true;
                break;
            }
        }
    }

    if (isDevelopment === null && window.location.protocol === 'file:') {
        isDevelopment = true;
    }
    document.write('<script type="text/javascript" charset="UTF-8" src="' + 
		path+'adapter/ext/ext-base' + (isDevelopment ? '-debug' : '') + '.js"></script>');
	document.write('<script type="text/javascript" charset="UTF-8" src="' + 
		path+'ext-all' + (isDevelopment ? '-debug' : '') + '.js"></script>');
	document.write('<script type="text/javascript" charset="UTF-8" src="' +
		path+'src/locale/ext-lang-zh_CN.js"></script>');
	document.write('<script type="text/javascript" charset="UTF-8" src="' +
		path+'src/data/JsonReader.js"></script>');
	document.write('<script type="text/javascript" charset="UTF-8" src="' +
		path+'GroupSummary.js"></script>');
	document.write('<script type="text/javascript" charset="UTF-8" src="' +
		path+'bluefat.js"></script>');
    document.write('<link type="text/css" rel="stylesheet" charset="UTF-8" href="' + 
         path+'resources/css/ext-all.css"></script>');
    document.write('<link type="text/css" rel="stylesheet" charset="UTF-8" href="' + 
         path+'../css/bluefat.css" />');
    document.write('<link type="text/css" rel="stylesheet" charset="UTF-8" href="' + 
         path+'../css/ext_icon.css">');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + 
		path+'ux/ux-all' + (isDevelopment ? '-debug' : '') + '.js"></script>');
    document.write('<link type="text/css" rel="stylesheet" charset="UTF-8" href="' + 
         path+'ux/css/ux-all.css">');
})();
