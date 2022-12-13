let UserUrl="http://localhost:3030/api/user/"; 



$(function (){
   

    showAllblood();
    showAllStates();
    showAllUser();
    showAllRole();
    showAllHospital();
    
    showAllRolesSelect();
   

   
});




// ...............Read All BloodType..................


function showAllblood(){
    let bloodUrl="http://localhost:3030/api/blood/";
    Helper.GetterData(bloodUrl, function (data) {
      console.log(data);
      $('#DataBloodTypeTable').empty();
       data.forEach(element => { 
        
        $('#DataBloodTypeTable').append(`
        <tr>
            
            
                    <td>${element.id}</td>
                    <td>${element.bloodName}</td>
                    


                    
                    <td> 
                    <button type="button" class="btn btn" onClick="EditbloodType(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
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
     
    let BloodTypePost="http://localhost:3030/api/blood/"; 
    
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
    let loodUrl=`http://localhost:3030/api/blood/${id}`;
    Helper.GetterData(loodUrl, function (data) {
        // console.log(data);

        $('#Editid').val(data.id);
        $('#EditbloodType').val(data.bloodName);
        
     
      
       })
      
}; 




$('#EditbloodTyp').on('submit', function(e){
    var id=$('#Editid').val();
    let BloodTypePost="http://localhost:3030/api/blood/"; 
    
    var BloodType= {
         
        id:$('#Editid').val(),
        bloodName:$('#EditbloodType').val()
       

        
    }
        
        Helper.PosterData( BloodTypePost,BloodType, function(){
            alert(' Update');
    });
}
)

////.......Remove////////////////

function delete_data(id){
	let deletSellerUrl =`http://localhost:3030/api/blood/${id}`;

    Helper. DeleterData(deletSellerUrl ,function(){
        alert('Remov');
        window.location.href = "BloodType.html";
})

}




 function showAllStates(){
    let StateUrl="http://localhost:3030/api/state/";
    Helper.GetterData(StateUrl, function (data) {
    //   console.log(data);
      $('#StatData').empty();
       data.forEach(element => {      
        $('#StatData').append(`
        <tr>

            
                    <td>${element.id}</td>
                    <td>${element.stateName}</td>
                    
                    <td> 
                    <button type="button" class="btn " onClick="EditStates(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
                    <i class="bi bi-box-arrow-in-down-left"></i>
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn " onClick=" delete_data_State(${element.id})" data-bs-toggle="modal"  >
                       <i class="bi bi-trash"></i>
                   </button>
                   </td>


        </tr>`)
       })
    });
};                                                                              


 // ............... Add New States..................
$('#StateData').on('submit', function(e){
     
    let SPost="http://localhost:3030/api/state/";
    
    var States= {
        stateName : $('#Name').val(), 
        
    }
        
        Helper.PosterData(SPost, States, function(){
            alert(' Registered');
    });
}
)

////// EditState//////////////

function EditStates(id){
    let StateUrl=`http://localhost:3030/api/state/${id}`;
    Helper.GetterData(StateUrl, function (data) {
    //   console.log(data);
     

            
              $('#editId').val(data.id); 
              $('#EditName').val(data.stateName);   
                    

    
    
    })
};   

$('#EditStateData').on('submit', function(e){
  var  id=$('#editId').val();
    let SPost="http://localhost:3030/api/state/";
    
    var States= {
       id:$('#editId').val(),
       stateName:$('#EditName').val()
        
    }
        
        Helper.PosterData(SPost, States, function(){
            alert(' Registered');
    });
}
)


function delete_data_State(id){
	let deletUrl =`http://localhost:3030/api/state/${id}`;

    Helper. DeleterData(deletUrl ,function(){
        alert('Remov');
        window.location.href = "states.html";
})

}














// ............showAllUser............

 function showAllUser(){
   
    
    Helper.GetterData(UserUrl, function (data) {
      console.log(data);
      $('#Userdata').empty();
       data.forEach(element => {      
        $('#Userdata').append(`
        <tr>
                    <td>${element.id}</td>
                    <td>${element.email}</td>
                    <td>${element.username}</td>
                    <td>${element.password}</td>
                    <td>${element.status}</td>
                    
                    <td>${element.role.role_name}</td>
                    

                    
                    <td> 
                    <button type="button" class="btn btn" onClick=" (${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
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


const RoleSelct=[];

function showAllRolesSelect(){
    let RoleUr="http://localhost:3030/api/role/";
    Helper.GetterData(RoleUr, function (data) {
     
        data.map((item) => {
            $('#role_name').append('<option value='+item.id+'>'+item.role_name+'</option>');
            RoleSelct.push(item);
          });

        
       
    });
};   

// ............... Add New User ..................


    $('#userdataa').on('submit', function(e){
        
    
        let RoleUr="http://localhost:3030/api/role/";

        let Userdat="http://localhost:3030/api/user/"; 
        
        
        var Userdata= {
             
            email:$('#email').val(), 
            username:$('#userName').val(), 
            password:$('#password').val(), 
            status:$('#status').val(),
            role_id:$("#role_name ").val()
    
           
            // "id","role_name","role_id",
            
        }
            
            Helper.PosterData(Userdat,Userdata,RoleUr,function(){
                alert('Registered');
        });
         
    
    }
    );





function showAllRole(){
    let RoleUrl="http://localhost:3030/api/role/";
    Helper.GetterData(RoleUrl, function (data) {
      console.log(data);
      $('#RoleData').empty();
       data.forEach(element => {      
        $('#RoleData').append(`
        <tr>

            
                    <td>${element.id}</td>
                    <td>${element.role_name}</td>
                    
                    <td> 
                    <button type="button" class="btn btn" onClick="EditRole(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
                    <i class="bi bi-box-arrow-in-down-left"></i>
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn" onClick=" delete_data_Role(${element.id})" data-bs-toggle="modal"  >
                       <i class="bi bi-trash"></i>
                   </button>
                   </td>

        </tr>`)
       })
    });
};   


// ............... Add New Role.................
$('#RolData').on('submit', function(e){
     
    let RoleUrl="http://localhost:3030/api/role/"; 
    
    var role= {
        role_name:$('#role_name').val(), 
        
    }
        
        Helper.PosterData(RoleUrl, role, function(){
            alert(' Registered');
    });
}
)




function EditRole(id){
    let RoleUrl=`http://localhost:3030/api/role/${id}`;
    Helper.GetterData(RoleUrl, function (data) {
      console.log(data);
     

            
              $('#editId').val(data.id); 
              $('#Editname').val(data.role_name);   
                    

    
    
    })
};   

$('#EditRoleData').on('submit', function(e){
  var  id=$('#editId').val();
    let edit="http://localhost:3030/api/role/";
    
    var Role= {
       id:$('#editId').val(),
       role_name:$('#Editname').val()
        
    }
        
        Helper.PosterData(edit,Role, function(){
            alert(' Update');
    });
}
)


function delete_data_Role(id){
	let deletUrl =`http://localhost:3030/api/role/${id}`;

    Helper. DeleterData(deletUrl ,function(){
        alert('Remov');
        window.location.href = "role.html";
})

}















//...................Hospital..............//


function showAllHospital(){
    let HospitalUrl="http://localhost:3030/api/hospital/";
    Helper.GetterData(HospitalUrl, function (data) {
      console.log(data);
      $('#HospitalData').empty();
       data.forEach(element => {      
        $('#HospitalData').append(`
        <tr>

            
                    <td>${element.id}</td>
                    <td>${element.name}</td>
                    <td>${element.location}</td>
                    <td>${element.status}</td>
                    
                    <td> 
                    <button type="button" class="btn btn" onClick="EditHospital(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModa">
                    <i class="bi bi-box-arrow-in-down-left"></i>
                   </button>
                   </td>

                   <td>
                       <button type="button" class="btn" onClick=" delete_data_hospital(${element.id})" data-bs-toggle="modal"  >
                       <i class="bi bi-trash"></i>
                   </button>
                   </td>

        </tr>`)
       })
    });
};   


// ............... Add New Role.................
$('#HospitalsData').on('submit', function(e){
     
    let HospitalUrl="http://localhost:3030/api/hospital/";
    
    var role= {
        
        
        name:$('#name').val(), 
        location:$('#location').val(), 
        status:$('#status').val(), 
        
    }
        
        Helper.PosterData(HospitalUrl, role, function(){
            alert(' Registered');
    });
}
)




function EditHospital(id){
    let dataurl=`http://localhost:3030/api/hospital/${id}`;
    Helper.GetterData(dataurl, function (data) {
      console.log(data);
     

            
              $('#editId').val(data.id); 
              $('#Editname').val(data.name);  
              $('#Editlocation').val(data.location);  
              $('#Editstatus').val(data.status);   
                    

    
    
    })
};   

$('#EditHospital').on('submit', function(e){
  var  id=$('#editId').val();
    let edit="http://localhost:3030/api/hospital/";
    
    var data= {
       id:$('#editId').val(),
       name:$('#Editname').val(),
       location:$('#Editlocation').val(),
       status:$('#Editstatus').val()
    
        
    }
        
        Helper.PosterData(edit,data, function(){
            alert(' Update');
    });
}
)


function delete_data_hospital(id){
	let dataurl=`http://localhost:3030/api/hospital/${id}`;

    Helper. DeleterData(dataurl ,function(){
        alert('Remov');
        window.location.href = "hospitals.html";
})

}