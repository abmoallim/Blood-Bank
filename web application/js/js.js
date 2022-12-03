$(function(){
    ShowAllCandidate();
    //Show_All_Candidate_In_List();
})

function  ShowAllblo (){
    let CandidateUrl="";
    Helper.GetterData(CandidateUrl, function (data) {
      console.log(data);
      $('#DataCandidateTable').empty();
       data.forEach(element => {      
        $('#DataCandidateTable').append(`
        <tr>

       
               
                    <td>${element.id}</td>
                    <td>${element.bloodName}</td>
                    
                 <td>
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target=".EditModal"><i class="bi bi-pencil-square"></i></button>
                </td>

                <td>
                    <a href="#!" class="btn btn-danger"><i class="bi bi-trash"></i></a>
                </td>

        </tr>`)
       });
    });
}


$('#SaveCandidata').on('submit', function(e){
     e.preventDefault();
    let CandidataPost="https://localhost:7045/api/Candidata";

    let formData = new FormData();
     
        Name = $('#Name').val(),
       
        
        formData.append('bloodName', Name);
       
        
    helper.Poster(CandidataPost,formData,function(data){
        $('#SaveCandidata')[0].reset();
        $('.nadar').modal("hide");
       ShowAllCandidate();
    })
})

