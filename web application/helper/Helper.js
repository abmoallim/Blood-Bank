

let Helper = { 
    
    



    LoginPost : function (url,success,error){
        $.ajax({
            method:"POST",
            url:url,
            dataType:"json",
            success:(data)=>{
                success(data)
            },error :(resp)=>{
                error(resp)
            }
        })
    },

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

    // PUT / UPDATE

    PutterData: function PutterData(Url,parameters,success,error){
        $.ajax({
            url:Url,
            method: "PUT",
            contentType: "application/json; charset=utf-8",  
            data: JSON.stringify(parameters),
            success:function (data){
                success(data);
                return data;
            },error : function(er){
                return er;
            }

        })
    },

    // DELETE OR REMOVE

    DeleterData: function DeleterData(Url,success,error){
        $.ajax({
            url:Url,
            method: "DELETE",
            cache: false,
            success:function (data){
                success(data);
                return data;
            },error : function(er){
                return er;
            }

        })
    }


   

}

