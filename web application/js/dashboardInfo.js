const donationURL = "http://localhost:3030/api/donation/"


$( document ).ready(function() {
    console.log( "ready!" );
    GetNumberOfDonations()

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
