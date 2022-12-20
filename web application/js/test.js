// get users
let uri="http://localhost:3030/api/result/"

let HospitalUrl="http://localhost:3030/api/hospital/"
let recipientURL="http://localhost:3030/api/recipient/"
// let userUrl="http://localhost:3030/api/users/donor/2"

$( document ).ready(function() {
    console.log( "ready!" );
    
    GetDonations();

   

    $(".cls").on('click', function(){
        $("#add-Donation").modal('hide');
        $("#editDonationModal").modal('hide');
    })
    $("#editDonationForm").on('submit', function(){
      editThis()
    })
    
     $("#btn-add-new").on('click', function(){
         $("#Add-Donation-modal").modal('show');
    })

    $("#addDonationForm").on('submit', function(){

        AddNew()

    })
});

function getAddingIfo(){

    $("#Dresult").empty()
    $("#Dhospial").empty()
   
     // blood
     $.ajax({  
        url: recipientURL,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            //console.log(data);
            $("#Dresult").append(`
            <option value="" selected disabled>select Recipient    </option>
                `)
            $.each(data, function(idx , item){
                // console.log(item);
                $("#Dresult").append(`
                <option value="${item.id}" > ${item.name }   </option>
                `)
              

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });
    // state
    $.ajax({  
        url: HospitalUrl,  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {  
            //console.log(data);
            $("#Dhospial").append(`
            <option value="" selected disabled>no hospital is selected</option>
                `)
            $.each(data, function(idx , item){
                // console.log(item);
                $("#Dhospial").append(`
                <option value="${item.id}" > ${item.name} </option>
                `)
              

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });

    

}






function updateDonation(id){
    getEdintingInfo()
    $("#editDonationModal").modal('show');

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
           $("#up-Dquantity").val(data.username)
           $("#up-Dpassword").val(data.password)
          
       

        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });


}

 function GetDonations(){
    
    $("#Donationdata").empty();
    var $list = $("#Donationdata");
   
    
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
                $tr.append("<td>"+item.donor.name+"</td>");
                $tr.append("<td>"+item.donor.bloodType.bloodName+"</td>");

                if(item.isHealthy){
                    $tr.append(`<td class="  badge bg-success mt-3">${item.isHealthy} </td>`);
                }else{
                    $tr.append(`<td class="badge bg-danger mt-3">${item.isHealthy} </td>`);
                }

                
                $tr.append("<td>"+item.hospital.name +"</td>");

                $tr.append("<td>"+item.date +"</td>");

            //     $tr.append(` <td> 
            //     <button type="button" class="btn btn-info"  onclick="updateDonation(${item.id})">Update</button>                       
            // </td>`);
            
                $list.append($tr);

            })  
        },  
        error: function (request , msg , error) {  
            console.log('Error in Operation');  
        }  
    });
  };

  
  function editThis(){
    let Donation ={
        id:$("#up-DID").val(),
        name: $("#up-Dname").val(),
        phone: $("#up-Dphone").val(),
        brithDate: $("#up-DbrithDate").val(),
        // address: $("#Daddress").val(),
        bloodType: {
            id: $("#up-Dresult").val(),
        },
        state: {
            id: $("#up-Dhospial").val(),
        },
        weight: $("#up-DWeight").val()+" KG",
        username:$("#up-Dquantity").val(),
        password:$("#up-Dpassword").val(),
        status:$("#up-Dstatus").val(),
    }
    $.ajax({  
        url: uri,  
        type: 'POST',
        contentType :'application/json',
        data: JSON.stringify(Donation),  
     
        success: function (data ) {
          console.log("Suuceccfully Updated");
          // console.log(data);
          $("#editDonationModal").modal('hide');
          window.location.reload();
        //   GetDonations(); 
              
        },  
        error: function (request , msg , error) {  
            console.log('Can not post');
            GetDonations(); 
        }  
    });

  }


  // add user

  function AddNew(){

        let Donations = {
	
            recipient: {
                id: $("#Dresult").val(),
            },
            hospital: {
                id: $("#Dhospial").val(),
            },
            quantity: $("#Dquantity").val(),
            date: $("#Ddate").val(),
        }

        

        $.ajax({  
            url: uri,  
            type: 'POST',
            contentType :'application/json',
            data: JSON.stringify(Donations),  
         
            success: function (data ) {
              console.log("Successfully added");
             
              window.location.reload();  
            },  
            error: function (request , msg , error) {  
                console.log('Can not post');
                
                GetDonations(); 
            }  
        }); 
    

  }





 