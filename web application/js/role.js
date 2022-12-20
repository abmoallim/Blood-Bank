const uri = "http://localhost:3030/api/role/"

$( document ).ready(function() {
    console.log( "ready!" );



    $("#RolData").on('submit', function(){

        AddNew()

    })

})



function AddNew(){

    let role = {

        
        role_name: $("#role_name").val(),
       
    }

    

    $.ajax({  
        url: uri,  
        type: 'POST',
        contentType :'application/json',
        data: JSON.stringify(role),  
     
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
