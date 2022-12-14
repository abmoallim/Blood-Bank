const donationURL = "http://localhost:3030/api/donation/"
const RecordURL = "http://localhost:3030/api/records/"
const HospitalURL = "http://localhost:3030/api/hospital/"


$( document ).ready(function() {
    console.log( "ready!" );
    GetNumberOfDonations()
    GetNumberOfRecords()
    GetNumberOfHospitals()

})

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


