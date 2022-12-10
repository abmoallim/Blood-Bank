$(function (){
   
    // showAllStates();
    showAllblood();
    showAllStates();
    showAllUser();
    showAllRole();

   

   
});

// showAlldonors();
// showAllUser();
// Create();






function Create(){

    $('#Donordata').on('submit', function(e){
        e.preventDefault();
        let SPost="http://localhost:8080/api/donors/"; 
        
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
    let bloodUrl="http://localhost:8080/api/blood/";
    Helper.GetterData(bloodUrl, function (data) {
      console.log(data);
      $('#DataBloodTypeTable').empty();
       data.forEach(element => {      
        $('#DataBloodTypeTable').append(`
        <tr>


            
                    <td>${element.id}</td>
                    <td>${element.bloodName}</td>
                    <td>${element.quantity}</td>


                    
                    <td> 
                    <button type="button" class="btn btn" onClick=" EditbloodType(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
                    <i class="bi bi-box-arrow-in-down-left"></i>
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn" onClick=" delete_data(${element.id})" data-bs-toggle="modal"  >
                       <i class="bi bi-trash"></i>
                   </button>
                   </td>

        </tr>`)
       })
    });
};   
 

 

// ............... Add New BloodType ..................
$('#bloodtype').on('submit', function(e){
     
    let BloodTypePost="http://localhost:8080/api/blood/"; 
    
    var BloodType= {
         
        bloodName:$('#bloodType').val(), 
        quantity:$('#quantity').val(), 


        
    }
        
        Helper.PosterData( BloodTypePost,BloodType, function(){
            alert(' Registered');
    });
}
)


//............... edit bloodType............

function EditbloodType(id){
    let bloodUrl=`http://localhost:8080/api/blood/${id}`;
    Helper.GetterData(bloodUrl, function (data) {
        console.log(data);

        $('#Editid').val(data.id);
         $('#EditbloodType').val(data.bloodName);
        $('#Editquantity').val(data.quantity);

     
      
       })
    
}; 




$('#EditbloodTyp').on('submit', function(e){
    var id=$('#Editid').val();
    let BloodTypePost="http://localhost:8080/api/blood/"; 
    
    var BloodType= {
         
        id:$('#Editid').val(),
        bloodName:$('#EditbloodType').val(), 
        quantity:$('#Editquantity').val(), 


        
    }
        
        Helper.PosterData( BloodTypePost,BloodType, function(){
            alert(' Update');
    });
}
)

////.......Remove////////////////

function delete_data(id){
	let deletSellerUrl =`http://localhost:8080/api/blood/${id}`;

    Helper. DeleterData(deletSellerUrl ,function(){
        alert('Remov');
        window.location.href = "BloodType.html";
})

}




 function showAllStates(){
    let StateUrl="http://localhost:8080/api/states/";
    Helper.GetterData(StateUrl, function (data) {
      console.log(data);
      $('#StatData').empty();
       data.forEach(element => {      
        $('#StatData').append(`
        <tr>

            
                    <td>${element.id}</td>
                    <td>${element.stateName}</td>
                    
                    <td> 
                    <button type="button" class="btn btn" onClick=" get_seller_data(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModal">
                    <i class="bi bi-box-arrow-in-down-left"></i>
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn" onClick=" delete_seller_data(${element.id})" data-bs-toggle="modal"  >
                       <i class="bi bi-trash"></i>
                   </button>
                   </td>

        </tr>`)
       })
    });
};                                                                              


 // ............... Add New States..................
$('#StateData').on('submit', function(e){
     
    let SPost="http://localhost:8080/api/states/"; 
    
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
    let donorsUrl="http://localhost:8080/api/donors/";
    Helper.GetterData(donorsUrl, function (data) {
      console.log(data);
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
//     let SPost="http://localhost:8080/api/donors/"; 
    
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
    let UserUrl="http://localhost:8080/api/users/";
    
    Helper.GetterData(UserUrl, function (data) {
      console.log(data);
      $('#Userdata').empty();
       data.forEach(element => {      
        $('#Userdata').append(`
        <tr>

            
                    <td>${element.id}</td>
                    <td>${element.email}</td>
                    <td>${element.userName}</td>
                    <td>${element.password}</td>
                    <td>${element.status}</td>
                    
                    <td>${element.role}</td>
                    

                    
                    <td> 
                    <button type="button" class="btn btn" onClick=" EditbloodType(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
                    <i class="bi bi-box-arrow-in-down-left"></i>
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn" onClick=" delete_data(${element.id})" data-bs-toggle="modal"  >
                       <i class="bi bi-trash"></i>
                   </button>
                   </td>

        </tr>`)
       })
    });
}; 

// ............... Add New User ..................
$('#userdata').on('submit', function(e){
    let RoleUrl="http://localhost:8080/api/role/";

     
    let Userdat="http://localhost:8080/api/users/"; 
    
    
    var Userdata= {
         
        email:$('#email').val(), 
        userName:$('#userName').val(), 
        password:$('#password').val(), 
        status:$('#status').val(),
        role:{id:$("#role_name").val()},

       
        // "id","role_name","role_id",
        
    }
        
        Helper.PosterData(Userdat,Userdata,function(){
            alert('Registered');
    });
     

}
)


function showAllRole(){
    let RoleUrl="http://localhost:8080/api/role/";
    Helper.GetterData(RoleUrl, function (data) {
      console.log(data);
      $('#RoleData').empty();
       data.forEach(element => {      
        $('#RoleData').append(`
        <tr>

            
                    <td>${element.id}</td>
                    <td>${element.role_name}</td>
                    
                    <td> 
                    <button type="button" class="btn btn" onClick=" EditbloodType(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
                    <i class="bi bi-box-arrow-in-down-left"></i>
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn" onClick=" delete_data(${element.id})" data-bs-toggle="modal"  >
                       <i class="bi bi-trash"></i>
                   </button>
                   </td>

        </tr>`)
       })
    });
};   


// ............... Add New Role.................
$('#RolData').on('submit', function(e){
     
    let RoleUrl="http://localhost:8080/api/role/"; 
    
    var role= {
        role_name:$('#role_name').val(), 
        
    }
        
        Helper.PosterData(RoleUrl, role, function(){
            alert(' Registered');
    });
}
)

