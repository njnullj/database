basePath = '/neo4j';
img_base_url= 'http://img5.duitang.com/uploads/item/201410/05/20141005082835_2RTzn.thumb.700_0.jpeg';


function escape(v) {
    var  entry = { "'": "&apos;", '"': '&quot;', '<': '&lt;', '>': '&gt;' };
    v = v.replace(/(['")-><&\\\/\.])/g, function ($0) { return entry[$0] || $0; });
    return v;
}