$(document).ready(function() 
{
	$("#btn_list_rigs").click(function(){
	    $.ajax
        ({
            type:"GET" ,
            url: '/rig',
            dataType: "text",
            error: function (xhr, ajaxOptions, thrownError)
            {
                    console.log(xhr.status);
                    console.log(thrownError);
                    console.log(ajaxOptions);
                   
            },
            success: function(data)
            {    
                console.log('Exitoso!');
                // console.log(data);

                var rig_list = jQuery.parseJSON(data);
                console.log(rig_list);

                // console.log(rig_list[1][2]);

                var number_of_rigs = rig_list.length;
                // console.log(number_of_rigs);
                var rig_info;
                for (var i = 0; i < number_of_rigs; i++) {
                	rig_info = '<tr id = '+ rig_list[i][1] +' > '+
                	'<td> '+rig_list[i][3]+' </td>'+
                	'<td> '+rig_list[i][10]+' </td>'+
                	'<td> '+rig_list[i][11]+' </td>'+
                	'<td> '+rig_list[i][12]+' </td>'+
                	'</tr>';

            	$('#tbody').append(rig_info);
                }

                 // console.log(rig_list[0][3]);
                

                // var rig_info = '<tr id='+ +'>' 

                // $('#tbody').append(rig_info);
                



                // cant_secciones = parseInt(cant_secciones);
                // $('#seccion').find('option').remove().end();

                // for (var i=65; i<65+cant_secciones; i++) {
                //     $('#seccion').append($('<option>', {
                //         value: String.fromCharCode(i),
                //         text: String.fromCharCode(i)
                //     }));

                // }
                // if(seccion != null) $('#seccion').val(seccion);
                // else $('#seccion').val('A');
            }
        });
	});

});
