// get users
let uri="http://localhost:360/api/donors/"

let stateUrl="http://localhost:360/api/states/"
let bloodTyeUrl="http://localhost:360/api/blood/"
let userUrl="http://localhost:360/api/users/donor/2"

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

    $("#addDonorForm").on('submit', function(){

        AddNew()

    })
});

function getAddingIfo(){

    $("#Dblood").empty()
    $("#Dstate").empty()
    $("#Duser").empty()
     // blood
     $.ajax({  
        url: bloodTyeUrl,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            //console.log(data);
            $("#Dblood").append(`
            <option value="" selected disabled>no blood type is selected</option>
                `)
            $.each(data, function(idx , item){
                // console.log(item);
                $("#Dblood").append(`
                <option value="${item.id}" > ${item.bloodName} </option>
                `)
              

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });
    // state
    $.ajax({  
        url: stateUrl,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            //console.log(data);
            $("#Dstate").append(`
            <option value="" selected disabled>no state is selected</option>
                `)
            $.each(data, function(idx , item){
                // console.log(item);
                $("#Dstate").append(`
                <option value="${item.id}" > ${item.stateName} </option>
                `)
              

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });

     // user
     $.ajax({  
        url: userUrl,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            //console.log(data);
            $("#Duser").append(`
            <option value="" selected disabled>no user is selected</option>
                `)
            $.each(data, function(idx , item){
                // console.log(item);
                $("#Duser").append(`
                <option value="${item.id}" > ${item.userName} </option>
                `)
              

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });

}



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
                // console.log(item);
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

  function AddNew(){
        
        let newDonor ={
            name: $("#Dname").val(),
            phone: $("#Dphone").val(),
            brithDate: $("#DbrithDate").val(),
            address: $("#Daddress").val(),
            bloodType: {
                id: $("#Dblood").val(),
            },
            state: {
                id: $("#Dstate").val(),
            },
            weight: $("#DWeight").val(),
        }
        $.ajax({  
            url: uri,  
            type: 'POST',
            contentType :'application/json',
            data: JSON.stringify(newDonor),  
         
            success: function (data ) {
              console.log("Suuceccfully added");
              // console.log(data);
           
              addUserID(data.id)
              
                  
            },  
            error: function (request , msg , error) {  
                console.log('Can not post');
                GetDonors(); 
            }  
        }); 
    

  }

  function addUserID(newID){

    $.ajax({  
        url: uri+newID,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {
         
          console.log(data);
       
          
          
              
        },  
        error: function (request , msg , error) {  
            console.log('Can not post');
            GetDonors(); 
        }  
    }); 

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