$(document).ready(function() 
{
	var domain = 'http://localhost:8081';

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});


	


    $("#boton_prueba").click(function(){
        
    	
    	var datos = {
	        user_email: "bsd@gmail.com",
	        user_password: "123456"
	    };

	    var myJSON = JSON.stringify(datos);

		$.ajax
        ({
            type:"POST" ,
            url: domain+'/user/',
            data: datos,
            dataType: "jsonp",
            error: function (xhr, ajaxOptions, thrownError)
            {
                    console.log(xhr.status); console.log(thrownError); console.log(ajaxOptions);
                   
            },
            success: function(data)
            {    
               console.log(data);

            }
        });



  //   	$.ajax
  //   	({
		//   type: "POST",
		//   url: domain+'/user',
		//   data: datos,
		//   success: success,
		//   dataType: dataType
		// });




     //    $.post
     //    (domain+'/user',
	    // {
	    //     user_email: "bsd@gmail.com",
	    //     user_password: "123456"
	    // },
	    // function(data, status){
	    // 	console.log(data);
	    //     alert("Data: " + data + "\nStatus: " + status);
	    // });

    });






});
