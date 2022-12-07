const donerURL = "http://localhost:360/api/donors/";
const stateURL = "http://localhost:360/api/states/";
const bloodURL = "http://localhost:360/api/blood/";
const recipientURL = "http://localhost:360/api/donors/";
const roleURL = "http://localhost:360/api/donors/";
const hospitalURL = "http://localhost:360/api/donors/";
const userURL = "http://localhost:360/api/donors/";
const DonationURL = "http://localhost:360/api/donors/";
const recordURL = "http://localhost:360/api/donors/";
const testURL = "http://localhost:360/api/donors/";



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
        let SPost=donerURL; 
        
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
    let BloodUrl=bloodURL;
    Helper.GetterData(BloodUrl, function (data) {
    //   console.log(data);
      $('#DataBloodTypeTable').empty();
       data.forEach(element => { 
        
        $('#DataBloodTypeTable').append(`
        <tr>
            <td>${element.id}</td>
            <td>${element.bloodName}</td>
            
            <td> 
            <button type="button" class="btn btn-info" onClick=" get_blood(${element.id})" data-bs-toggle="modal"  data-bs-target="#exampleModal1">
                    Edit
            </button>
            </td>
                   

        </tr>`)
       })
    });
};  
 
function get_blood(id){
    Helper.GetterData(bloodURL+id, function (data) {
        console.log(data.bloodName);
        
        $("#up-bloodType").val(data.bloodName)
       
        // $("#up-bloodTypeID").val(data.id)
    })

}

 

// ............... Add New BloodType ..................
$('#bloodtype').on('submit', function(e){
     
    let BloodTypePost="http://localhost:360/api/blood/"; 
    
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
    let StateUrl=stateURL;
    Helper.GetterData(StateUrl, function (data) {
    //   console.log(data);
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
     
    let SPost=stateURL; 
    
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
    let donorsUrl=donerURL;
    Helper.GetterData(donorsUrl, function (data) {
    //   console.log(data);
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
//     let SPost="http://localhost:360/api/donors/"; 
    
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
    let UserUrl=userURL;
    Helper.GetterData(UserUrl, function (data) {
    //   console.log(data);
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
     
    let Userdat="http://localhost:360/api/users/"; 
    
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