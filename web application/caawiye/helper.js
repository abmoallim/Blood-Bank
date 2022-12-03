

let helper = {

    Get: function ShowData(Url,success,error){
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

    // from form
    
    Poster: function PostData(Url,parameters,success,error){
        $.ajax({
            url:Url,
            method: "POST",
            contentType: false, 
            data: parameters,
            processData: false,
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