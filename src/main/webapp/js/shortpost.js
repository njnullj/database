/**
 * 
 */
$(document).ready(function(){
	$(window).scroll(function() {
  		
  		if(no_more) {
  			//$('.footer').css('display', 'block').html('没有更多了');
			return false;
		}
  		
  		if($(window).scrollTop() + $(window).height() == $(document).height()) {
  			page_num++;	//next page
  			var url = $('#next').attr('href').replace(new RegExp("page/\\d"), 'page/'+page_num);
  			$('#next').attr('href', url);
  			$.ajax({
  				//url: basePath + '/page/' + page_num,
  				url: url,
  				type: 'GET' ,
  				dataType: 'html'
  			})
  			.success(function(data){
  				if($.trim(data).length == 0){
  					no_more = true;
  					$('.footer').css('display', 'block').html('没有更多了');
  					return false;
  				} 
  				$('#feeds').append(data);
  			});
  		}
  	});
	
	$('.comment.outline.icon').live('click', function(){
  		
  		var comments_attach = $(this).parents('.content').find('.comments-attach');
  		if($(comments_attach).text() != null && $(comments_attach).text() != ''){
  			$(comments_attach).slideToggle();
  			return false;
  		}
  		
  		var object_type = $(this).parents('.event').attr('type');
  		var object_id = $(this).parents('.event').attr('object_id');
  		var that = $(this);
  		
		$.ajax({
			url: '/getShortPost',
			type: 'POST'
		})
		.success(function(data){
			console.log(data);
		});
  		
  		
  	});
	
});