// get users
let uri="http://localhost:3030/api/recipient/"

let stateUrl="http://localhost:3030/api/state/"
let bloodTyeUrl="http://localhost:3030/api/blood/"
// let userUrl="http://localhost:3030/api/users/donor/2"

$( document ).ready(function() {
    console.log( "ready!" );
    
    GetDonors();

   

    $(".cls").on('click', function(){
        $("#add-donor").modal('hide');
        $("#editDonorModal").modal('hide');
    })
    $("#editDonorForm").on('submit', function(){
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

    

}

function getEdintingInfo(){

    $("#up-Dblood").empty()
    $("#up-Dstate").empty()
   
     // blood
     $.ajax({  
        url: bloodTyeUrl,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            //console.log(data);
            $("#up-Dblood").append(`
            <option value="" selected disabled>no blood type is selected</option>
                `)
            $.each(data, function(idx , item){
                // console.log(item);
                $("#up-Dblood").append(`
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
            $("#up-Dstate").append(`
            <option value="" selected disabled>no state is selected</option>
                `)
            $.each(data, function(idx , item){
                // console.log(item);
                $("#up-Dstate").append(`
                <option value="${item.id}" > ${item.stateName} </option>
                `)
              

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });

    

}





function updateDonor(id){
    getEdintingInfo()
    $("#editDonorModal").modal('show');

    $.ajax({  
        url: uri+`${id}`,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            console.log(data);
           $("#up-DID").val(data.id)
           $("#up-Dname").val(data.name)
           $("#up-Dphone").val(data.phone)
           $("#up-DbrithDate").val(data.brithDate)
           $("#up-DWeight").val(data.weight)
           $("#up-Dusername").val(data.username)
           $("#up-Dpassword").val(data.password)
          
       

        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });


}

 function GetDonors(){
    
    $("#donordata").empty();
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


                var DateToStr = (item.brithDate)
                var bDate = new Date(DateToStr)

                $tr.append("<td>"+getAge(bDate) +"</td>");

            

                $tr.append("<td>"+item.bloodType.bloodName+"</td>");
               
                $tr.append("<td>"+item.state.stateName+"</td>");
                if(item.status == "Active"){
                  $tr.append(`<td class="  badge bg-success mt-3" >${item.status}  </td>    `);
                }
                else if(item.status == "Pendding"){
                    $tr.append(`<td class=" badge bg-warning mt-3 " >${item.status} </td>`);
                }
                else{
                  $tr.append(`<td class=" badge bg-danger mt-3 " >${item.status} </td>`);

                }
               

                $tr.append(` <td> 
                <button type="button" class="btn btn-info"  onclick="updateDonor(${item.id})">Update</button>
                
                                        
            </td>`);
            
             
                
             
            

                $list.append($tr);

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });
  };

  function getAge(birthDate) {
    var now = new Date();
  
    function isLeap(year) {
      return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
  
    // days since the birthdate    
    var days = Math.floor((now.getTime() - birthDate.getTime())/1000/60/60/24);
    var age = 0;
    // iterate the years
    for (var y = birthDate.getFullYear(); y <= now.getFullYear(); y++){
      var daysInYear = isLeap(y) ? 366 : 365;
      if (days >= daysInYear){
        days -= daysInYear;
        age++;
        // increment the age only if there are available enough days for the year.
      }
    }
    return age;
  }



  
  function editThis(){
    let Donor ={
        id:$("#up-DID").val(),
        name: $("#up-Dname").val(),
        phone: $("#up-Dphone").val(),
        brithDate: $("#up-DbrithDate").val(),
        // address: $("#Daddress").val(),
        bloodType: {
            id: $("#up-Dblood").val(),
        },
        state: {
            id: $("#up-Dstate").val(),
        },
        weight: $("#up-DWeight").val()+" KG",
        username:$("#up-Dusername").val(),
        password:$("#up-Dpassword").val(),
        status:$("#up-Dstatus").val(),
    }
    $.ajax({  
        url: uri,  
        type: 'POST',
        contentType :'application/json',
        data: JSON.stringify(Donor),  
     
        success: function (data ) {
          console.log("Suuceccfully Updated");
          // console.log(data);
          $("#editDonorModal").modal('hide');
          window.location.reload();
        //   GetDonors(); 
              
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
            // address: $("#Daddress").val(),
            bloodType: {
                id: $("#Dblood").val(),
            },
            state: {
                id: $("#Dstate").val(),
            },
            weight: $("#DWeight").val()+" KG",
            username:$("#Dusername").val(),
            password:$("#Dpassword").val(),
            status:"Active"
        }
        $.ajax({  
            url: uri,  
            type: 'POST',
            contentType :'application/json',
            data: JSON.stringify(newDonor),  
         
            success: function (data ) {
              console.log("Suuceccfully added");
              // console.log(data);
              window.location.reload();
           
              
              
                  
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