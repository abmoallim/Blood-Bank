

let Helper = { 

    GetterData: function ShowData(Url,success,error){
        $.ajax({
            url:Url,
            method: "GET",
            cache: false,
            success:function (data){
                success(data);
                return data;
            },error : function(er){
                return er;
            }
        })
    },


    // POST
    PosterData: function PostData(Url,parameters,success,error){

        console.log(parameters)
        $.ajax({
            url:Url,
            method: "POST",
            contentType: "application/json; charset=utf-8",  
            data: JSON.stringify(parameters),
            cache: false,
            success:function (data){
                success(data);
                return data;
            },error : function(er){
                return er;
            }

        })
    },
}
    