/**
 * 获取好友的好友数据
 */
$(document).ready(function(){
	var no_more = false;
	
	$.ajax({
			url: basePath+'/getfollowings',
			type: 'POST',
			dataType: 'json',
			data:{}
		})
		.success(function(data) {
				$.each(data, function(index, content) {
					var title = $("<div class='fontstyle' style='display:block; width:100%'>" + index + "</div>");
					title.appendTo('#ui_cards');
					for(var i = 0; i < content.length; i++){
						var t =  $("<div class='ui card' style='width:31%; display:block'>" +
								"<div class='ui small centered circular  image'><a href='' /><img src='" + content[i].avatar + "'/></a></div>" +
								"<div class='content'><a class='header centered' href='' />" + content[i].name + "</a></div></div> ");
						t.appendTo('#ui_cards');
					}
	            });	
			
		});
});