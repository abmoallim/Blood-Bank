const donationURL = "http://localhost:3030/api/donation/"
const RecordURL = "http://localhost:3030/api/records/"
const HospitalURL = "http://localhost:3030/api/hospital/"

const DonorURL = "http://localhost:3030/api/donor/"
const RecipientlURL = "http://localhost:3030/api/recipient/"

const totalIn = "http://localhost:3030/api/donation/total/"
const TotalOut = "http://localhost:3030/api/records/total/"


$( document ).ready(function() {
    console.log( "ready!" );

    // GetNumberOfDonations()
    // GetNumberOfRecords()
    GetNumberOfHospitals()
    GetNumberOfDonors()
    GetNumberOfRecipients()


    GetTotal()

    
})

function GetTotal(){
   

   $.ajax({  
    url: totalIn,  
    type: 'GET',  
    dataType: 'json',  
    success: function (data ) {
        $("#bIn").val(data)
        
    }
})


$.ajax({  
    url: TotalOut,  
    type: 'GET',  
    dataType: 'json',  
    success: function (data ) {
        $("#bOut").val(data)
        cal()
        
    }

})


}

function cal(){
    
    // alert(  $("#bIn").val()- $("#bOut").val() )
    $("#TotalCC").html( $("#bIn").val()- $("#bOut").val())
    

}

function GetNumberOfDonors(){
    $("#NumOfDonors").empty()
    
    $.ajax({  
        url: DonorURL+"donors/",  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {
            $("#NumOfDonors").html(data)
        }
    })
}

function GetNumberOfRecipients(){
    $("#NumOfRecipients").empty()
    
    $.ajax({  
        url: RecipientlURL+"recipients/",  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {
            $("#NumOfRecipients").html(data)
        }
    })
}

function GetNumberOfDonations(){
    $("#NumDonations").empty()
    
    $.ajax({  
        url: donationURL+"donations/",  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {
            $("#NumDonations").html(data)
        }
    })
}

function GetNumberOfRecords(){
    $("#NumOfRecords").empty()
    
    $.ajax({  
        url: RecordURL+"records/",  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {
            $("#NumOfRecords").html(data)
        }
    })
}

function GetNumberOfHospitals(){
    $("#NumOfHospitals").empty()
    
    $.ajax({  
        url: HospitalURL+"hospitals/",  
        type: 'GET',  
        dataType: 'json',  
        success: function (data ) {
            $("#NumOfHospitals").html(data)
        }
    })
}


