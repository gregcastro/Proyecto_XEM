$(document).ready(function() 
{
    $("#include_sidebar").load("sidebar.html");
    $("#include_head").load("head.html");

    //Listar los Rigs de un usuario (parametrizar el correo)
    var user_email = 'alfonsof@gmail.com';
    var domain = 'http://localhost:8081';
    $.ajax
        ({
            type:"GET" ,
            url: domain+'/rig/ReadByUser/'+user_email,
            dataType: "text",
            error: function (xhr, ajaxOptions, thrownError)
            {
                    console.log(xhr.status); console.log(thrownError); console.log(ajaxOptions);
                   
            },
            success: function(data)
            {    
                var rig_list = jQuery.parseJSON(data);
                console.log(rig_list);
                rig_list = rig_list.rig;

                // console.log(rig_list[0].user_email);
                // console.log(rig_list[0].rig_name);

                var number_of_rigs = rig_list.length;
                console.log('number of rigs = ' + number_of_rigs);
                var rig_info;
                var eth_info;

             for (var i = 0; i < number_of_rigs; i++) {

                    eth_info = rig_list[i].rig_gpu_info_eth;
                    eth_info = cleaningJsonArray(eth_info);

                    second_coin_info = rig_list[i].rig_gpu_info_second_coin;
                    second_coin_info = cleaningJsonArray(second_coin_info);

                    var N = eth_info.length;
                    var M = second_coin_info.length;

                    rig_info = '<tr id = '+ rig_list[i].rig_name +' > '+
                    '<td> '+rig_list[i].rig_name+' </td>'+
                    '<td> ';

                    for (var j = 0; j < N; j++) {
                        rig_info += eth_info[j].Model + '  ' + eth_info[j].Mhs + ' MH/s<br>';
                    }
                    rig_info += '</td>'+
                    '<td> '+rig_list[i].rig_gpu_second_coin+' </td>'+
                    '<td> ';

                    for (var j = 0; j < M; j++) {
                        rig_info += second_coin_info[j].Mhs + ' MH/s<br>';
                    }

                    rig_info += '</td>'+
                    '<td> ';
                    
                    
                    for (var j = 0; j < N; j++) {
                        rig_info += eth_info[j].Temp + '° ' + eth_info[j].Fan +'%<br>';
                    }

                    rig_info += '</td>'+
                    "<td> <a href='#'> <span class='glyphicon glyphicon-repeat'></span> </a> " +
                    " <a href='#'> <span class='glyphicon glyphicon-cog'></span> </a>" + 
                    " <a href='#'> <span class='glyphicon glyphicon-download-alt'></span> </a>" + 

                    "</td>' " +
                    '</tr>';

                $('#tbody').append(rig_info);
                }

                // eth_info = rig_list[0].rig_gpu_info_eth;
                // console.log(eth_info);
                // var o = cleaningJsonArray(eth_info);
                // console.log(o);
                // console.log(o[0].Model);



            }
        });

});


function cleaningJsonArray(eth_info) {
    s = eth_info.replace(/\\n/g, "\\n")  
               .replace(/\\'/g, "\\'")
               .replace(/\\"/g, '\\"')
               .replace(/\\&/g, "\\&")
               .replace(/\\r/g, "\\r")
               .replace(/\\t/g, "\\t")
               .replace(/\\b/g, "\\b")
               .replace(/\\f/g, "\\f");
                // remove non-printable and other non-valid JSON chars
                s = s.replace(/[\u0000-\u0019]+/g,""); 
                var o = JSON.parse(s);

    return o;
}



// <button type="button" class="btn btn-default btn-sm">
//   <span class="glyphicon glyphicon-repeat"></span> Repeat
// </button>