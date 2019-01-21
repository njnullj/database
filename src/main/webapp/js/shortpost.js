/**
 * 
 */
$(document).ready(function(){
	var no_more = false;
	
	$.ajax({
			url: basePath+'/getShortPosts',
			type: 'POST',
			dataType: 'json',
			data:{}
		})
		.success(function(data) {
				$.each(data, function(index, content) {
					var t =  $("<div class='event empty row' style='display:block'>" +
							"<div class='label span2'><img src=''></div>" +
							"<div class='content span6'><div class='summary'><a href=''>"+ index +"</a> 说<div class='date'>"+ content['date'] +"</div></div>" +
							"<div class='extra'>"+ content['post_content'] +"</div><div class='meta'><div class='actions'><a class='comment'><i class='comment outline icon'></i><span>" + content['comment_count'] + "</span></a><a class='like'><i class='heart icon' object_type='4' object_id=''></i><span>" + content['like_count'] + "</span></a></div></div></div></div>" +
							"</div> ");
					
					t.appendTo('#feeds');
	            });	
			
		});
	
//	$(window).scroll(function() {
//  		
//  		if(no_more) {
//  			$('.footer').css('display', 'block').html('没有更多了');
//			return false;
//		}
//  		
//  		if($(window).scrollTop() + $(window).height() == $(document).height()) {
//  			page_num++;	//next page
//  			var url = $('#next').attr('href').replace(new RegExp("page/\\d"), 'page/'+page_num);
//  			$('#next').attr('href', url);
//  			$.ajax({
//  				url: basePath+'/getShortPosts',
//  				type: 'POST',
//  				dataType: 'json',
//  				data:{}
//  			})
//  			.success(function(data) {
//  				if(data.length == 0){
//  					no_more = true;
//  					$('.footer').css('display', 'block').html('没有更多了');
//  					return false;
//  				}
//  				else{
//  					$.each(data, function(index, content) {
//  						var t =  $("<div class='event empty row' style='display:block'>" +
//  								"<div class='label span2'><img src=''></div>" +
//  								"<div class='content span6'><div class='summary'><a href=''>"+ index +"</a> 说<div class='date'>"+ content['date'] +"</div>test</div>" +
//  								"<div class='extra'>"+ content['post_content'] +"</div><div class='meta'><div class='actions'><a class='comment'><i class='comment outline icon'></i><span>0</span></a><a class='like'><i class='heart icon' object_type='4' object_id=''></i><span>0</span></a></div></div></div></div>" +
//  								"</div> ");
//  						t.appendTo('#feeds');
//  		            });	
//  				}
//  				
//  			});
//  		}
//  	});
	
	
});