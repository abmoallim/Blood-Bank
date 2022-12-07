$(function (){
   
    showAllStates();
   
});

// showAlldonors();
// showAllUser();
// Create();
$(function (){
    showAlldonors();
    // Create();
   
});


$(function (){
   
    showAllblood();
   
});

$(function (){
   
    showAllUser();
   
});


function Create(){

    $('#Donordata').on('submit', function(e){
        e.preventDefault();
        
        var doners= {
            name : $('#name').val(), 
            phone : $('#phone').val(), 
            brithDate : $('#brithDate').val(), 
            address : $('#address').val(), 
            weight : $('#Weight').val(), 
            stateName:$("#Name").val(),
            bloodName:$("#BloodType").val(),
            userName:$("#User").val()
    
        }
            
            Helper.PosterData(SPost, doners, function(){
                alert(' Registered');
        });
    }
    )

};

// ...............Read All BloodType..................


function showAllblood(){
    Helper.GetterData(BloodUrl, function (data) {
      $('#DataBloodTypeTable').empty();
        $('#DataBloodTypeTable').append(`
        <tr>
            

        </tr>`)
       })
    });
};  
 

 

// ............... Add New BloodType ..................
$('#bloodtype').on('submit', function(e){
     
    
    var BloodType= {
         
        bloodName:$('#bloodType').val(), 
        quantity:$('#quantity').val(), 


        
    }
        
        Helper.PosterData( BloodTypePost,BloodType, function(){
            alert(' Registered');
    });
}
)



 // ...............Read All States..................
function showAllStates(){
    Helper.GetterData(StateUrl, function (data) {
      console.log(data);
      $('#StatData').empty();
       data.forEach(element => {      
        $('#StatData').append(`
        <tr>

            
                    <td>${element.id}</td>
                    <td>${element.stateName}</td>
                    
                    <td> 
                    <button type="button" class="btn btn-success" onClick=" get_seller_data(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModal">
                         Edit
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn btn-danger" onClick=" delete_seller_data(${element.id})" data-bs-toggle="modal"  >
                         Delete
                   </button>
                   </td>

        </tr>`)
       })
    });
};                                                                              


 // ............... Add New States..................
$('#StateData').on('submit', function(e){
     
    
    var States= {
        stateName : $('#Name').val(), 
        
    }
        
        Helper.PosterData(SPost, States, function(){
            alert(' Registered');
    });
}
)

// ...............Read All donor..................
function showAlldonors(){
    Helper.GetterData(donorsUrl, function (data) {
      $('#donordata').empty();
       data.forEach(element => {      
        $('#donordata').append(`
        <tr>
                    <td>${element.id}</td>
                    <td>${element.name}</td>
                    <td>${element.phone}</td>
                    <td>${element.brithDate}</td>
                    <td>${element.address}</td>
                    <td>${element.weight}</td>
                    <td>${element.bloodName}</td>
                    <td>${element.stateName}</td>
                    <td>${element.userName}</td>

                    
                    <td> 
                    <button type="button" class="class="feather feather-edit" onClick=" get_seller_data(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModal">
                     Edit
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn btn-danger" onClick=" delete_seller_data(${element.id})" data-bs-toggle="modal"  >
                         Delete
                   </button>
                   </td>

        </tr>`)
       });
    });
}

// ............... Add New States..................
// $('#Donordata').on('submit', function(e){
//     e.preventDefault();
    
//     var doners= {
//         name : $('#name').val(), 
//         phone : $('#phone').val(), 
//         brithDate : $('#brithDate').val(), 
//         address : $('#address').val(), 
//         weight : $('#Weight').val(), 
//         state_id:$("#Name").val(),
//         blood_id:$("#BloodType").val(),
//         user_id:$("#User").val()

//     }
        
//         Helper.PosterData(SPost, doners, function(){
//             alert(' Registered');
//     });
// }
// )



 // ...............Read All User..................
 
 function showAllUser(){
    Helper.GetterData(UserUrl, function (data) {
      $('#StatData').empty();
       data.forEach(element => {      
        $('#StatData').append(`
        <tr>
                    <td>${element.id}</td>
                    <td>${element.email}</td>
                    <td>${element.userName}</td>
                    <td>${element.password}</td>
                    <td>${element.status}</td>
                    
                    <td> 
                    <button type="button" class="btn btn-success" onClick=" get_seller_data(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModal">
                         Edit
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn btn-danger" onClick=" delete_seller_data(${element.id})" data-bs-toggle="modal"  >
                         Delete
                   </button>
                   </td>

        </tr>`)
       })
    });
}; 

// ............... Add New User ..................
$('#Userdata').on('submit', function(e){
     
    
    var Userdata= {
         
        email:$('#email').val(), 
        userName:$('#userName').val(), 
        password:$('#password').val(), 
        status:$('#Status').val(), 


        
    }
        
        Helper.PosterData( Userdat,Userdata, function(){
            alert(' Registered');
    });
}
)