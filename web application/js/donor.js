// get users
let uri="http://localhost:360/api/donors/"

$( document ).ready(function() {
    console.log( "ready!" );
    GetDonors();
    $(".btn-cl").on('click', function(){
        $("#add-donor").modal('hide');
        $("#update-hospital-modal").modal('hide');
    })
    $("#UpdateHospital").on('click', function(){
      editThis()
    })
    
     $("#btn-add-new").on('click', function(){
         $("#Add-Donor-modal").modal('show');
    })

    $("#add-hospital").on('click', function(){

        addhopspital()

    })
});

function updateHospital(id){
    $("#update-hospital-modal").modal('show');

    $.ajax({  
        url: uri+`${id}`,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            console.log(data);
           $("#H-id").val(data.id)
           $("#H-name").val(data.name)
           $("#H-location").val(data.location)
           $("#H-status").val(data.status)

        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });


}

 function GetDonors(){
    
    $("#donordata").empty();
  //  $("#Add-modal-Product").modal("hide");

    var $list = $("#donordata");
    
      $.ajax({  
        url: uri,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            //console.log(data);
            $.each(data, function(idx , item){
                console.log(item);
                var $tr = $("<tr></tr>");
                $tr.append("<td>"+item.id +"</td>");
                $tr.append("<td>"+item.name+"</td>");
                $tr.append("<td>"+item.phone +"</td>");
                $tr.append("<td>"+item.brithDate +"</td>");
                $tr.append("<td>"+item.address +"</td>");
                $tr.append("<td>"+item.weight +"</td>");
                $tr.append("<td>"+item.state.stateName+"</td>");
                $tr.append("<td>"+item.bloodType.bloodName+"</td>");
               

                $tr.append(` <td>
                <button type="button" class="btn btn-warning" onclick="updateHospital(${item.id})">Update</button>
                
                                        
            </td>`);
            
             
                
             
            

                $list.append($tr);

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });
  };


  
  function editThis(){
    let hospital ={
        id: $("#H-id").val(),
        name:$("#H-name").val(),
        location:$("#H-location").val(),
        status: $("#H-status").val(),
        
    }
    $.ajax({  
        url: uri,  
        type: 'POST',
        contentType :'application/json',
        data: JSON.stringify(hospital),  
     
        success: function (data ) {
          console.log("Suuceccfully Updated");
          // console.log(data);
          $("#update-hospital-modal").modal('hide');
          GetDonors(); 
              
        },  
        error: function (request , msg , error) {  
            console.log('Can not post');
            GetDonors(); 
        }  
    });
     

 

  }


  // add user

  function addhopspital(){
       let name=$("#new-name").val()
    if (name !== "") {
        
        let hospital ={
            name:$("#new-name").val(),
            location:$("#new-location").val(),
            status: $("#new-status").val(),
            
        }
        $.ajax({  
            url: uri,  
            type: 'POST',
            contentType :'application/json',
            data: JSON.stringify(hospital),  
         
            success: function (data ) {
              console.log("Suuceccfully added");
              // console.log(data);
              $("#Add-Donor-modal").modal('hide');
              window.location.reload()
              GetDonors(); 
              
                  
            },  
            error: function (request , msg , error) {  
                console.log('Can not post');
                GetDonors(); 
            }  
        }); 
    }else{
        
        alert("no value given")
    }
  
     

 

  }

  function deletehospital(id){
    if (confirm("are you sure you want to delete this Hospital") == true) {
        deletethis(id)
    
    } else {
      
    }      
  }

  function deletethis(id){

    $.ajax({  
        url: uri+`${id}`,  
        type: 'DELETE',
     
        success: function (data ) {
            
          console.log("Suuceccfully deleted");
          // console.log(data);
          //window.location.reload()
          GetDonors(); 
          
              
        },  
        error: function (request , msg , error) {  
            console.log('Can not post');
            GetDonors(); 
        }  
    }); 


  }